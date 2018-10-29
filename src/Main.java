import cryptography.*;
import parser.*;
import util.*;

import com.beust.jcommander.JCommander;

public class Main {
  
  public static void main(String[] argv) throws Exception {
    Settings settings = new Settings();
    // Most invalid/edge cases will be caught during parsing
    new JCommander(settings, argv);
    new Main().run(settings);
  }
    
  public void run(Settings settings) throws Exception { 
    //System.out.println(settings.algo);
    //System.out.println(settings.shift);
    String msg;
    switch (settings.algo) {
      case RSA:
        // TODO
        break;
      case SHIFT:
        if (settings.shift == 0) {
          throw new IllegalStateException("Cannot start: missing shift value (provide with -s flag)");
        }
        Shift cipher = new Shift(settings.shift);
        if (settings.decrypt) {
          if (settings.inputFile != null && !settings.inputFile.isEmpty()) {
            msg = IOUtilities.readFromFile(settings.inputFile, true);
            msg = cipher.decrypt(msg);
            System.out.println("Decrypted message: " + msg);
            if (settings.outputFile != null && !settings.outputFile.isEmpty()){
              IOUtilities.writeToFile(settings.outputFile, msg, true);
            }
          } else {
            throw new IllegalStateException("Cannot start decryption: missing text file containing message (provide with -f flag)");
          }
        } else {
          if (settings.inputFile != null && !settings.inputFile.isEmpty()) {
            msg = IOUtilities.readFromFile(settings.inputFile, true);
          } else {
            System.out.println("Enter the plain text:");
            msg = IOUtilities.getUserInput();
          }
          System.out.println("Original message: " + msg);
          msg = cipher.encrypt(msg);
          System.out.println("Encrypted message: " + msg);
          if (settings.outputFile != null && !settings.outputFile.isEmpty()){
            IOUtilities.writeToFile(settings.outputFile, msg, true);
          }
        }
        break;
      default:
        // Should be handled by parser, but throw exception here anyways
        throw new IllegalArgumentException("Invalid algorithm: " + settings.algo);
    }
  }
}
