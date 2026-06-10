# Drozda Coding Template

Minimal Java template for coding challenges that require an executable `.jar` file.

## Package

```java
package io.drozda.coding;
```

## Input and output

The application reads from:

```text
input.txt
```

and writes to:

```text
output.txt
```

Both files are resolved relative to the current working directory.

## Build

```powershell
.\gradlew clean test jar
```

## Run jar

```powershell
java -jar .\build\libs\solution.jar
```

## Notes

- Uses Java 17.
- Avoids `java.util.Scanner` for performance reasons.
- Includes source files inside the jar under `source/`.
- Main class: `io.drozda.coding.Main`.
