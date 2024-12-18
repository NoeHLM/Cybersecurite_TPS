import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static void main(String[] args) {
        hashMd5("valdoversionbackendsuperieuraversionfrontend");
    }
    public static void hashMd5(String message) {
        //on convertit la chaine de caractères en tableau de bytes
        byte[] messageEnBytes = message.getBytes();

        try {
            //Initialisation de l'algorithme MD5
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            //calcul du hash
            byte[] messageHashe = messageDigest.digest(messageEnBytes);

            //StringBuilder qui va contenir le hash en hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : messageHashe) {
                //chaque byte est transformé en deux caractères hexadécimaux
                sb.append(String.format("%02x", b));
            }
            System.out.println(sb);


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("L'algorithme MD5 n'est pas supporté.", e);
        }
    }
}
