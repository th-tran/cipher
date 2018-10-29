package cryptography;

public class Shift {

  private int s;

  public Shift(int shift) {
    s = shift;
  }

  private Character determineShiftBoundary(char context, char c) {
    if (Character.isLowerCase(c)) {
      return (Character.toLowerCase(context) == 'd') ? 'a' : 'z';
    } else if (Character.isUpperCase(c)) {
      return (Character.toLowerCase(context) == 'd') ? 'A' : 'Z';
    } else {
      return null;
    }
  }

  public String encrypt(String msg) {
    StringBuilder strBuilder = new StringBuilder();
    int len = msg.length();
    boolean outOfBounds = false;
    for(int x = 0; x < len; x++){
      char c = (char)(msg.charAt(x) + s);
      if ((int)c > 127){
        outOfBounds = true;
      }
      Character start = determineShiftBoundary('E', c);
      if (start == null) {
        strBuilder.append(c);
        continue;
      }
      char encryptedChar;
      if (c > start){
        encryptedChar = (char)(msg.charAt(x) - (26-s));
      } else {
        encryptedChar = (char)(msg.charAt(x) + s);
      }
      strBuilder.append(encryptedChar);
    }
    if (outOfBounds) {
      // TODO: Prompt user when encryption could corrupt the message
      //System.out.println("WARNING: During encryption, some characters have shifted out of bounds.");
      //System.out.println("The message might be partially corrupted after decryption.");
      //System.out.println("Continue anyways? (y/n)");
    }
    return strBuilder.toString();
  }

  public String decrypt(String msg) {
    StringBuilder strBuilder = new StringBuilder();
    int len = msg.length();
    for(int x = 0; x < len; x++){
      char c = (char)(msg.charAt(x) - s);
      Character start = determineShiftBoundary('D', c);
      if (start == null) {
        strBuilder.append(c);
        continue;
      }
      char decryptedChar;
      if (c < start) {
        decryptedChar = (char)(msg.charAt(x) + (26-s));
      } else {
        decryptedChar = (char)(msg.charAt(x) - s);
      }
      strBuilder.append(decryptedChar);
    }
    return strBuilder.toString();
  }
}
