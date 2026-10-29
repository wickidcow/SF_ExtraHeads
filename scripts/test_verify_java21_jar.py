#!/usr/bin/env python3
"""Offline negative controls for Java 21 JAR class and ZIP identity checks."""
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest
import warnings
from zipfile import ZipFile

ROOT = Path(__file__).resolve().parents[1]
VERIFY = ROOT / "scripts" / "verify_java21_jar.py"


class VerifyJava21JarTest(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.addCleanup(self.temp.cleanup)
        self.jar = Path(self.temp.name) / "test.jar"

    def write(self, members):
        with warnings.catch_warnings():
            warnings.simplefilter("ignore", UserWarning)
            with ZipFile(self.jar, "w") as archive:
                for name, data in members:
                    archive.writestr(name, data)

    @staticmethod
    def clazz(major):
        return b"\xca\xfe\xba\xbe\x00\x00" + major.to_bytes(2, "big")

    def check(self, expected):
        result = subprocess.run([sys.executable, str(VERIFY), str(self.jar)],
                                text=True, capture_output=True, check=False)
        self.assertEqual(expected, result.returncode == 0, result.stderr)
        return result

    def test_valid_java21_and_older_dependency(self):
        self.write([("plugin/Main.class", self.clazz(65)),
                    ("shaded/Util.class", self.clazz(61))])
        self.assertIn("Verified 2 unique Java classes", self.check(True).stdout)

    def test_java22_dependency_rejected(self):
        self.write([("Main.class", self.clazz(65)), ("TooNew.class", self.clazz(66))])
        self.assertIn("major 66", self.check(False).stderr)

    def test_invalid_header_rejected(self):
        self.write([("Main.class", b"not-a-java-class")])
        self.assertIn("invalid class header", self.check(False).stderr)

    def test_duplicate_zip_member_rejected(self):
        self.write([("Main.class", self.clazz(65)), ("Main.class", self.clazz(65))])
        self.assertIn("duplicate ZIP entries", self.check(False).stderr)

    def test_no_classes_rejected(self):
        self.write([("plugin.yml", b"name: ExtraHeads")])
        self.assertIn("no class files", self.check(False).stderr)

    def test_old_only_bytecode_rejected(self):
        self.write([("Main.class", self.clazz(61))])
        self.assertIn("expected Java 21", self.check(False).stderr)

    def test_corrupt_zip_rejected(self):
        self.jar.write_bytes(b"corrupt")
        self.assertIn("JAR verification failed", self.check(False).stderr)


if __name__ == "__main__":
    unittest.main()
