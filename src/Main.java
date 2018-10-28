import cryptography.*;
import parser.*;

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
    System.out.println(settings.algo);
    System.out.println(settings.shift);
    switch (settings.algo) {
      case RSA:
        // TODO
        break;
      case SHIFT:
        Shift cipher = new Shift();
        cipher.encrypt();
        break;
      default:
        // Should be handled by parser, but throw exception here anyways
        throw new IllegalArgumentException("Invalid algorithm: " + settings.algo);
    }
  }
}
