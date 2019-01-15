## Compilation

First, open the command prompt and move to the project root directory (i.e. the one with this README file).
To build the project, there are two options:

1) Automatic: Run the build script by typing './build.sh'.

2) Manual: Run the following commands:
    ```
    $ javac -cp lib/* -d classes src/\*.java
    $ cd classes
    $ jar cvfe Main.jar Main \*.class algorithm/\*.class ../lib/\*.jar
    ```

## Running the program

There are two options:

(Assuming you're in the project root directory)

1) Class file:
    ```
    $ cd classes
    $ java -cp ".;../lib/\*" Main
    ```

2) Jar:
   ```
   $ java -jar jar/Main.jar
   ```

## Current features:
- Encryption algorithms:
  - Shift (https://simple.wikipedia.org/wiki/Caesar_cipher)
  - RSA (https://simple.wikipedia.org/wiki/RSA_algorithm)
- Enable encryption/decryption based on passed parameters (keys, files containing message, etc.)

## Planned features:
- Encryption algorithm(s):
  - Hybrid -> RSA + AES (https://en.wikipedia.org/wiki/Advanced_Encryption_Standard)
- Optional modes:
  - Strong encryption
  - Generate keys only
- Improve shift algorithm (remove shift overflow)
