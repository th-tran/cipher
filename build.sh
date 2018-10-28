#!/bin/bash

# Exit script on any error
set -e

echo -e "\nCompiling class files..."
javac -cp lib/* -d classes $(find -name "*.java") 
echo -e "...done.\n"
echo -e "Building jar...\n"
mkdir -p classes
cd classes
jar cvfm Cipher.jar ../manifest.mf *.class algorithm/*.class ../lib/*.jar
mkdir -p ../jar
mv Cipher.jar ../jar
echo -e "...done.\n"
echo "Build complete! Type 'java -jar jar/Cipher.jar' to run the program."
