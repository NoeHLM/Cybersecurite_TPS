import java.util.Base64;

public class Rc4 {
    public static void main(String[] args) {
        String message = "mathis merite un moins un";

        //notre clé en magnifique latin
        String cle = "nam quis nulla. integer malesuada. in in enim a arcu imperdiet malesuada. sed vel lectus. donec odio urna, tempus molestie, porttitor ut, iaculis quis, sem. phasellus rhoncus. aenean id metus id velit ullamcorper pulvinar. vestibulum fermentum tortor id mi";

        //Chiffrement
        String messageChiffre = RC4(cle, message);
        //on encode en base64 pour avoir des caractères lisibles
        String messageChiffreBase64 = Base64.getEncoder().encodeToString(messageChiffre.getBytes());
        System.out.println("Message chiffré en base64 : " + messageChiffreBase64);

        //Déchiffrement
        String messageDecodeBase64 = new String(Base64.getDecoder().decode(messageChiffreBase64));
        String messageDechiffre = RC4(cle, messageDecodeBase64);
        System.out.println("Message déchiffré : " + messageDechiffre);
    }

    public static String RC4(String cle, String message) {

        //On initialise nos tableaux
        int[] S = new int[256];
        int[] T = new int[256];

        for (int i = 0; i < 256; i++) {
            //S prend l'index et T le code ascii
            S[i] = i;
            T[i] = cle.charAt(i);
        }
        //on permute
        int j= 0;
        for (int i = 0; i < 256; i++) {
            j= (j + S[i]+ T[i]) % 256;
            int indiceTemporaire = S[i];
            S[i] = S[j];
            S[j] = indiceTemporaire;
        }
        //StringBuilder qui va contenir le resultat du chiffrement / dechiffrement
        StringBuilder resultat = new StringBuilder();
        int l = 0, k = 0;
        for (int n = 0; n < message.length(); n++) {
            // Incrément de l pour parcourir S
            l = (l + 1) % 256;

            //Mise à jour de la position de permutation
            k = (k + S[l]) % 256;

            //Permutation de S
            int temp = S[l];
            S[l] = S[k];
            S[k] = temp;

            //Sélection de l'indice de la clé de flux,
            int t = (S[l] + S[k]) % 256;
            int keystreamByte = S[t];

            //XOR entre le message et le flux pour chiffrer/déchiffrer
            char c = (char) (message.charAt(n) ^ keystreamByte);
            resultat.append(c);
        }

        return resultat.toString();

    }
}