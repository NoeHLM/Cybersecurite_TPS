public class BitShift {
    public static void main(String[] args) {
        Bitshift("hello");
    }

    public static void Bitshift(String motAChiffrer) {
        //on boucle motAchiffrer pour afficher chaque bit
        for (int i = 0; i < motAChiffrer.length(); i++) {
            //la lettre
            char charMotAChiffrer = motAChiffrer.charAt(i);

            //l equivalent ascii de la lettre
            int motAChiffrerAscii = (int) charMotAChiffrer;

            //Rotation circulaire vers la droite à l'aide de l'opérateur >>, 0xFF sert à garder seulement les 8 bits de droite après la rotation
            int rotationDroite = (motAChiffrerAscii >> 2 | motAChiffrerAscii << (8 - 2)) & 0xFF;

            //à l'aide de toBinaryString on retourne l'entier sous forme de byte (en chaine de caractères)
            //%8s indique que le retour est forcément 8 caractères et que par conséquent si le bit commence par des 0
            //(qui ne seront normalement pas affichés) alors il y aura des espaces à la place
            //enfin .replace(' ', '0'); indique que les espaces sont remplacés par des 0
            String stringDuBitSansRotation = String.format("%8s", Integer.toBinaryString(motAChiffrerAscii)).replace(' ', '0');
            String stringDuBitAvecRotation = String.format("%8s", Integer.toBinaryString(rotationDroite)).replace(' ', '0');
            System.out.println("Avant rotation : " + stringDuBitSansRotation);
            System.out.println("Après rotation : " + stringDuBitAvecRotation);

            char charApresRotation = (char) rotationDroite;
            System.out.println("Rotation : " + charApresRotation);
            System.out.println("---------------------------------------");
        }
    }
}
