## Compilation

First, open the command prompt and move to the project root directory (i.e. the one with this README file).
To build the project, there are two options:

1) Automatic: Run the build script by typing './build.sh'.

2) Manual: Run the following commands:
    1) javac -cp lib/* -d classes src/\*.java
    2) cd classes
    3) jar cvfe Cipher.jar Cipher \*.class algorithm/\*.class ../lib/\*.jar

## Running the program

There are two options:

(Assuming you're in the project root directory)

1) Class file: 
    1) cd classes
    2) java -cp ".;../lib/\*" Cipher

2) Jar: java -jar jar/Cipher.jar

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
