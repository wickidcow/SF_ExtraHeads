#!/usr/bin/env python3
"""Fail closed if a distributable JAR contains non-Java-21 bytecode."""
import sys
from pathlib import Path
from zipfile import BadZipFile, ZipFile


def verify(path: Path) -> int:
    with ZipFile(path) as archive:
        names = [entry.filename for entry in archive.infolist()]
        if len(names) != len(set(names)):
            raise ValueError("duplicate ZIP entries")
        classes = [entry for entry in archive.infolist() if entry.filename.endswith(".class")]
        if not classes:
            raise ValueError("plugin JAR has no class files")
        majors = []
        for entry in classes:
            with archive.open(entry) as stream:
                header = stream.read(8)
            if len(header) != 8 or header[:4] != b"\xca\xfe\xba\xbe":
                raise ValueError(f"invalid class header: {entry.filename}")
            major = int.from_bytes(header[6:8], "big")
            if major < 45 or major > 65:
                raise ValueError(f"unsupported bytecode major {major}: {entry.filename}")
            majors.append(major)
        if 65 not in majors:
            raise ValueError("expected Java 21 plugin classes (major 65)")
        print(f"Verified {len(majors)} unique Java classes; max major={max(majors)}")
        return len(majors)


if __name__ == "__main__":
    try:
        if len(sys.argv) != 2:
            raise ValueError("usage: verify_java21_jar.py <plugin.jar>")
        verify(Path(sys.argv[1]))
    except (OSError, BadZipFile, ValueError) as exc:
        print(f"Java 21 JAR verification failed: {exc}", file=sys.stderr)
        sys.exit(1)
