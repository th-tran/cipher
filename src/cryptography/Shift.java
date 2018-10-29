package cryptography;

public class Shift {
  
  private int s;

  public Shift(int shift) {
    s = shift;
  }
  
  private Character determineShiftBoundary(char context, char c) {
    if (('a' <= c) && (c <= 'z')) {
      return (Character.toLowerCase(context) == 'd') ? 'a' : 'z';
    } else if (('A' <= c) && (c <= 'Z')) {
      return (Character.toLowerCase(context) == 'd') ? 'A' : 'Z';
    } else {
      return null;
    }
  }

  public String encrypt(String msg) {
    String encrypted = "";
    int len = msg.length();
    for(int x = 0; x < len; x++){
      char c = (char)(msg.charAt(x) + s);
      Character start = determineShiftBoundary('E', c);
      if (start == null) {
        encrypted += c;
        continue;
      }
      if (c > start){
        encrypted += (char)(msg.charAt(x) - (26-s));
      } else {
        encrypted += (char)(msg.charAt(x) + s);
      }
    }
    return encrypted;
  }

  public String decrypt(String msg) {
    String decrypted = "";
    int len = msg.length();
    for(int x = 0; x < len; x++){
      char c = (char)(msg.charAt(x) - s);
      Character start = determineShiftBoundary('D', c);
      if (start == null) {
        decrypted += c;
        continue;
      }
      if (c < start) {
        decrypted += (char)(msg.charAt(x) + (26-s));
      } else {
        decrypted += (char)(msg.charAt(x) - s);
      }
    }
    return decrypted;
  }
}
