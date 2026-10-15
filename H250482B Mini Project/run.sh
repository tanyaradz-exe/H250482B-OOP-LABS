#!/bin/sh
# Compile, test, and run the Library Management System (needs JDK 17+)
mkdir -p out && javac -d out src/*.java && java -cp out LibraryTests && java -cp out Main data
