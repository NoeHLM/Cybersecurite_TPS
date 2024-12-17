public class Vigenere {
    public static void main(String[] args) {
        System.out.println("Chiffrage du mot clementleboss");
        System.out.println("---------------------------------------");
        String motVigenereChiffre = vigenere("clementleboss","verite");
        dechiffrerVigenere(motVigenereChiffre,"verite");
    }

    public static String vigenere(String motEnClair, String cle) {

        //longueur de la clé pour ensuite selectionner le caractère
        int longueurCle = cle.length();

        //Initialisation du StringBuilder qui contiendra le mot chiffré
        StringBuilder motChiffre = new StringBuilder();

        //on boucle le mot en clair pour avoir la taille à chiffrer
        for (int i = 0; i < motEnClair.length(); i++) {
            //la lettre actuelle du mot à chiffrer
            char charMotEnClair = motEnClair.charAt(i);

            //la lettre de la clé
            char charCle = cle.charAt(i % longueurCle);

            //on chiffre a l'aide de la formule (Pi + Ki)mod26
            int intChiffre = ((charMotEnClair -97)+ (charCle - 97)) % 26;

            //on convertit l'entier en char
            char charChiffre = (char) (intChiffre + 97);

            //on pousse le caractere dans le stringBuilder pour construire le String à chaque itération
            motChiffre.append(charChiffre);
        }
        System.out.println("mot chiffré : " + motChiffre);
        return motChiffre.toString();
    }

    public static void dechiffrerVigenere(String motChiffre, String cle) {

        //longueur de la clé pour ensuite selectionner le caractère
        int longueurCle = cle.length();

        //Initialisation du StringBuilder qui contiendra le mot déchiffré
        StringBuilder motDechiffre = new StringBuilder();

        //On boucle le mot chiffré pour le déchiffrer
        for (int i = 0; i < motChiffre.length(); i++) {
            //La lettre actuelle du mot chiffré
            char charMotChiffre = motChiffre.charAt(i);

            //La lettre de la clé
            char charCle = cle.charAt(i % longueurCle);

            //On déchiffre à l'aide de la formule (Pi - Ki + 26) % 26
            int intDechiffre = ((charMotChiffre - 97) - (charCle - 97) + 26) % 26;

            //On convertit l'entier en char
            char charDechiffre = (char) (intDechiffre + 97);

            //on pousse le caractere dans le stringBuilder pour construire le String à chaque itération
            motDechiffre.append(charDechiffre);
        }

        System.out.println("Mot déchiffré : " + motDechiffre);
    }

}