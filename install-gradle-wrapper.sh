#!/usr/bin/env bash
set -euo pipefail

# Downloads Gradle distribution and extracts gradle-wrapper.jar into gradle/wrapper/
DIST_URL="https://services.gradle.org/distributions/gradle-8.5-bin.zip"
TMPDIR=$(mktemp -d)
echo "Downloading $DIST_URL to $TMPDIR..."
if command -v curl >/dev/null 2>&1; then
  curl -fsSL "$DIST_URL" -o "$TMPDIR/dist.zip"
elif command -v wget >/dev/null 2>&1; then
  wget -qO "$TMPDIR/dist.zip" "$DIST_URL"
else
  echo "curl or wget required to download Gradle distribution" >&2
  exit 2
fi

echo "Unpacking..."
unzip -q "$TMPDIR/dist.zip" -d "$TMPDIR"
JAR_PATH=$(find "$TMPDIR" -type f -name 'gradle-wrapper.jar' | head -n1 || true)
if [ -z "$JAR_PATH" ]; then
  echo "Failed to locate gradle-wrapper.jar inside distribution" >&2
  echo "Listing contents for debugging:" >&2
  find "$TMPDIR" -maxdepth 3 -type f -print >&2 || true
  exit 3
fi

mkdir -p gradle/wrapper
cp "$JAR_PATH" gradle/wrapper/
echo "Copied gradle-wrapper.jar to gradle/wrapper/ (from $JAR_PATH)"

rm -rf "$TMPDIR"
echo "Done. You can now run ./gradlew <task> (make sure ./gradlew is executable)."
