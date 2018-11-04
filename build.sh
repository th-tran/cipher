#!/bin/bash

# Exit script on any error
set -e

if [[ "$1" == "-c" ]] || [[ "$1" == "--clean" ]]; then
  echo -e "\nRemoving current class files..."
  rm -rf classes/*
  echo -e "...done\n"
  echo -e "\nRemoving current jar files..."
  rm -rf jar/*
  echo -e "...done"
fi

echo -e "\nCompiling class files..."
mkdir -p classes
javac -cp "*;lib/*" -d classes $(find -name "*.java")
echo -e "...done.\n"
echo -e "Building jar...\n"
cd classes
jar cvfm Main.jar ../manifest.mf *.class cryptography/*.class parser/*.class util/*.class ../lib/*.jar
mkdir -p ../jar
mv Main.jar ../jar
echo -e "...done.\n"
echo "Build complete! Type 'java -jar jar/Main.jar' to run the program."
