import java.security.MessageDigest;

public class Quiz {
    public static void main(String[] args) throws Exception {
        System.out.println(9/2);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest("abracadabra".getBytes("UTF-8"));
        for (byte b : digest) {
            System.out.printf("%02x", b);
        }
    }
}
