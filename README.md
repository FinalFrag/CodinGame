# CodinGame
A repository containing all my CodinGame solutions

# Compiler
CodinGame expects the solution to be contained in 1 source file.
The cgcompiler maven plugin can compile multiple files to 1.

Install the cgcompiler plugin locally by running
```
cd cgcompiler-maven-plugin
mvn clean install
```
Then you can use it to compile solutions by running
```
cd <project>
mvn cgcompiler:compile
```
The compiler does not support packages, so make sure all files are directly under `<project>/src/main/java`.