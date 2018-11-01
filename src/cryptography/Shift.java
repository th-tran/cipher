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
    for(int x = 0; x < len; x++){
      char c = (char)(msg.charAt(x) + s);
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
