# Require named argument

[![Maven Central](https://img.shields.io/maven-central/v/io.github.chao2zhang/rna-gradle-plugin)](https://search.maven.org/artifact/io.github.chao2zhang/rna-gradle-plugin)

Enforce named arguments usage for callers of a function through a Kotlin Compiler Plugin.
See [KT-14934](https://youtrack.jetbrains.com/issue/KT-14934)

Once this issue is addressed by Jetbrains/Kotlin, this solution should be deprecated.

## Usage

1. In the build script,
    - Apply the plugin `id("io.github.chao2zhang.rna") version "x.y.z"`.
    - Add the dependency to the annotation.
    ```
    dependencies {
        implementation("io.github.chao2zhang:rna-annotation:x.y.z")
    }
    ```
2. At the declaration site, annotate functions with `@RequireNamedArgument`
3. At the caller site, `:compileKotlin` should fail in functions with `@RequireNamedArgument` are invoked without named arguments.

A full example can be found in `sample/`.

## License
```
Copyright 2021 Chao Zhang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
