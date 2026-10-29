#!/usr/bin/env python3
"""Offline fail-closed tests of ExtraHeads's immutable manual release policy."""
import os
from pathlib import Path
import subprocess
import tempfile
import unittest
import zipfile

ROOT = Path(__file__).resolve().parents[1]
PUBLISH = ROOT / "scripts/publish_immutable_release.sh"
WORKFLOW = ROOT / ".github/workflows/build.yml"


class ImmutableReleaseTest(unittest.TestCase):
    def setUp(self):
        self.tmp = tempfile.TemporaryDirectory()
        self.addCleanup(self.tmp.cleanup)
        self.work = Path(self.tmp.name)
        (self.work / "dist").mkdir()
        self.jar = self.work / "dist" / "SF_ExtraHeads1.0.4.jar"
        self.rewrite_jar("name: ExtraHeads\nversion: '1.0.4'\n")
        bindir = self.work / "bin"
        bindir.mkdir()
        mock = bindir / "gh"
        mock.write_text(
            "#!/bin/sh\n"
            "printf '%s\\n' \"$*\" >> \"$GH_CALL_LOG\"\n"
            "if [ \"$1\" = release ] && [ \"$2\" = view ]; then\n"
            "  [ \"$GH_EXISTS\" = 1 ]; exit $?\n"
            "fi\n"
            "if [ \"$1\" = release ] && [ \"$2\" = create ]; then\n"
            "  [ \"$GH_CREATE_FAIL\" != 1 ]; exit $?\n"
            "fi\n"
            "exit 93\n", encoding="utf-8")
        mock.chmod(0o755)
        self.log = self.work / "gh.log"
        self.env = dict(os.environ)
        self.env.update({
            "PATH": str(bindir) + os.pathsep + os.environ["PATH"],
            "GH_CALL_LOG": str(self.log),
            "GH_EXISTS": "0",
            "GH_CREATE_FAIL": "0",
            "GITHUB_REPOSITORY": "wickidcow/SF_ExtraHeads",
            "GITHUB_SHA": "a" * 40,
            "GITHUB_REF": "refs/tags/v1.0.4",
            "GITHUB_EVENT_NAME": "workflow_dispatch",
            "RELEASE_VERSION": "1.0.4",
            "OUTPUT_NAME": "SF_ExtraHeads1.0.4.jar",
        })

    def rewrite_jar(self, descriptor):
        with zipfile.ZipFile(self.jar, "w") as z:
            z.writestr("plugin.yml", descriptor)

    def execute(self):
        return subprocess.run(
            ["bash", str(PUBLISH)], cwd=self.work, env=self.env,
            text=True, capture_output=True, check=False)

    def calls(self):
        return self.log.read_text(encoding="utf-8").splitlines() if self.log.exists() else []

    def test_workflow_does_not_publish_on_push_or_pr(self):
        workflow = WORKFLOW.read_text(encoding="utf-8")
        self.assertIn("permissions:\n  contents: read", workflow)
        self.assertIn(
            "if: github.event_name == 'workflow_dispatch' && startsWith(github.ref, 'refs/tags/v')",
            workflow)
        self.assertIn("run: bash scripts/publish_immutable_release.sh", workflow)
        self.assertIn("test_publish_immutable_release.py", workflow)
        self.assertNotIn("gh release upload", workflow)
        self.assertNotIn("--clobber", workflow)

    def test_valid_explicit_new_tag_creates_once_without_asset_mutation(self):
        result = self.execute()
        self.assertEqual(0, result.returncode, result.stderr)
        calls = self.calls()
        self.assertEqual(2, len(calls))
        self.assertIn("release view v1.0.4", calls[0])
        self.assertIn("release create v1.0.4 dist/SF_ExtraHeads1.0.4.jar", calls[1])
        self.assertIn("--verify-tag", calls[1])
        self.assertIn("--target " + "a" * 40, calls[1])
        self.assertFalse(any("release upload" in c or "release edit" in c or "--clobber" in c
                             for c in calls))

    def test_existing_release_cannot_be_replaced(self):
        self.env["GH_EXISTS"] = "1"
        result = self.execute()
        self.assertNotEqual(0, result.returncode)
        self.assertIn("already exists", result.stderr)
        self.assertEqual(1, len(self.calls()))

    def test_tag_push_does_not_touch_github(self):
        self.env["GITHUB_EVENT_NAME"] = "push"
        self.assertNotEqual(0, self.execute().returncode)
        self.assertEqual([], self.calls())

    def test_default_branch_push_does_not_touch_github(self):
        self.env.update(GITHUB_EVENT_NAME="push", GITHUB_REF="refs/heads/master")
        self.assertNotEqual(0, self.execute().returncode)
        self.assertEqual([], self.calls())

    def test_manual_branch_dispatch_does_not_touch_github(self):
        self.env["GITHUB_REF"] = "refs/heads/master"
        self.assertNotEqual(0, self.execute().returncode)
        self.assertEqual([], self.calls())

    def test_wrong_tag_and_filename_are_refused(self):
        self.env["GITHUB_REF"] = "refs/tags/v1.0.3"
        self.assertNotEqual(0, self.execute().returncode)
        self.env["GITHUB_REF"] = "refs/tags/v1.0.4"
        self.env["OUTPUT_NAME"] = "SF_ExtraHeads1.0.3.jar"
        self.assertNotEqual(0, self.execute().returncode)
        self.assertEqual([], self.calls())

    def test_corrupt_and_wrong_descriptor_jars_are_refused(self):
        self.jar.write_bytes(b"not a zip")
        self.assertNotEqual(0, self.execute().returncode)
        self.rewrite_jar("name: ExtraHeads\nversion: 1.0.1\n")
        self.assertNotEqual(0, self.execute().returncode)
        self.rewrite_jar("name: Other\nversion: '1.0.4'\n")
        self.assertNotEqual(0, self.execute().returncode)
        self.assertEqual([], self.calls())

    def test_missing_artifact_and_failed_create_refuse_success(self):
        self.jar.unlink()
        self.assertNotEqual(0, self.execute().returncode)
        self.rewrite_jar("name: ExtraHeads\nversion: '1.0.4'\n")
        self.env["GH_CREATE_FAIL"] = "1"
        self.assertNotEqual(0, self.execute().returncode)
        self.assertEqual(2, len(self.calls()))


if __name__ == "__main__":
    unittest.main()
