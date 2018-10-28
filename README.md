## Compilation

First, open the command prompt and move to the project root directory (i.e. the one with this README file).
To build the project, there are two options:

1) Automatic: Run the build script by typing './build.sh'.

2) Manual: Run the following commands:
    1) javac -cp lib/* -d bin src/\*.java
    2) cd bin
    3) jar cvfe Cipher.jar Cipher \*.class algorithm/\*.class ../lib/\*.jar

## Running the program

There are two options:

(Assuming you're in the project root directory)

1) java bin/Cipher

2) java -jar bin/Cipher.jar

Note that jar files are portable, so you can move it anywhere you want and run it!

## Current features:
- New project ;)

## Planned features:
- Encryption algorithms:
  - Caesar (https://simple.wikipedia.org/wiki/Caesar_cipher)
  - RSA (https://simple.wikipedia.org/wiki/RSA_algorithm)
  - AES (https://en.wikipedia.org/wiki/Advanced_Encryption_Standard)
- Optional modes:
  - Strong encryption
  - Generate keys only
- Enable encryption/decryption based on passed parameters (keys, files containing message, etc.)
