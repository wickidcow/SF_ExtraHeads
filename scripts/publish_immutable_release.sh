#!/usr/bin/env bash
# Publication is manual, exact-tag and strictly create-only. Existing releases are immutable.
set -euo pipefail
: "${GITHUB_REPOSITORY:?}"
: "${GITHUB_SHA:?}"
: "${GITHUB_REF:?}"
: "${GITHUB_EVENT_NAME:?}"
: "${RELEASE_VERSION:?}"
: "${OUTPUT_NAME:?}"

tag="v$RELEASE_VERSION"
expected="SF_ExtraHeads$RELEASE_VERSION.jar"
if [[ "$GITHUB_EVENT_NAME" != "workflow_dispatch" || "$GITHUB_REF" != "refs/tags/$tag" ]]; then
  echo "Refusing publication: manual dispatch on exact new version tag required." >&2
  exit 1
fi
if [[ "$OUTPUT_NAME" != "$expected" ]]; then
  echo "Refusing publication: raw artifact name differs from version contract." >&2
  exit 1
fi
jar="dist/$OUTPUT_NAME"
if [[ ! -s "$jar" ]]; then
  echo "Refusing publication: required raw plugin JAR is missing." >&2
  exit 1
fi
unzip -tqq "$jar"
descriptor="$(unzip -p "$jar" plugin.yml)"
if ! grep -Fxq "name: ExtraHeads" <<< "$descriptor" || { ! grep -Fxq "version: $RELEASE_VERSION" <<< "$descriptor" && ! grep -Fxq "version: '$RELEASE_VERSION'" <<< "$descriptor"; }; then
  echo "Refusing publication: packaged plugin name/version do not match the expected artifact." >&2
  exit 1
fi

# Even a matching existing asset is immutable. If the lookup fails, GitHub's
# atomic release-create operation must reject an existing tag/release.
if gh release view "$tag" --repo "$GITHUB_REPOSITORY" >/dev/null 2>&1; then
  echo "Refusing publication: release $tag already exists; prepare a distinct version." >&2
  exit 1
fi
digest="$(sha256sum "$jar" | awk '{print $1}')"
echo "Creating new $tag from $GITHUB_SHA with SHA256 $digest"
gh release create "$tag" "$jar" --repo "$GITHUB_REPOSITORY" --verify-tag \
  --target "$GITHUB_SHA" --title "SF_ExtraHeads $RELEASE_VERSION" \
  --notes "ExtraHeads $RELEASE_VERSION: Java 21 plugin; see exact-head CI for compatibility and native-test limits."
