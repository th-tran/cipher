#!/bin/bash

echo -e "\nCompiling class files..."
javac -cp lib/* -d bin src/*.java
echo -e "...done.\n"
echo -e "Building jar...\n"
cd bin
jar cvfe Cipher.jar Cipher *.class algorithm/*.class ../lib/*.jar
echo -e "...done.\n"
echo "Build complete! Type 'java -jar bin/Cipher.jar' to run the program."
