import java.io.BufferedReader;
import java.io.InputStreamReader;

import cryptography.*;
import parser.*;
import util.*;

import com.beust.jcommander.JCommander;

public class Main {
  
  public static void main(String[] argv) throws Exception {
    Settings settings = new Settings();
    for (String arg : argv){
      System.out.println(arg);
    }
    // All invalid/edge cases are caught during parsing
    new JCommander(settings, argv);
    new Main().run(settings);
  }
    
  public void run(Settings settings) throws Exception { 
    //System.out.println(settings.algo);
    //System.out.println(settings.shift);
    String input;
    switch (settings.algo) {
      case RSA:
        // TODO
        break;
      case SHIFT:
        if (settings.shift == 0) {
          throw new IllegalStateException("Cannot start encryption: missing shift value (provide with -s flag)");
        }
        System.out.println("Enter the plain text:");
        input = IOUtilities.getUserInput();
        System.out.println("Original message: " + input);
        Shift cipher = new Shift();
        String encrypted = cipher.encrypt(input, settings.shift);
        System.out.println("Encrypted message: " + encrypted);
        break;
      default:
        // Should be handled by parser, but throw exception here anyways
        throw new IllegalArgumentException("Invalid algorithm: " + settings.algo);
    }
  }
}
