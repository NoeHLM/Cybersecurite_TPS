public class LFSR {
    public static void main(String[] args) {
        String graine = "graine";
        LFSR(graine);
    }

    public static void LFSR(String graine) {
        //on convertit chaque caractère de graine en binaire puis on l'insère dans un StringBuilder
        StringBuilder bytesDeLaGraine = new StringBuilder();
        for (char c : graine.toCharArray()) {
            bytesDeLaGraine.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }

        //initialisation de la valeur du LFSR
        String valeurLFSR = bytesDeLaGraine.toString();

        //LFSR avec rétroaction (polynome irréductible: x^8 + x^6 + x^5 + x^4 + 1)
        for (int i = 0; i < 10; i++) {
            //calcul du bit de rétroaction
            //On prend le XOR des bits à des positions spécifiques, selon le polynôme
            //ici, on compare les bits aux indices 0, 4, 5, 6, 7 de la chaîne binaire
            //valeurLFSR.charAt(index) récupère le bit sous forme de caractère ('0' ou '1')
            //Ensuite, on les compare et convertit en 1 ou 0
            int xorDuBit = (valeurLFSR.charAt(0) == valeurLFSR.charAt(4) ? 1 : 0)
                    ^ (valeurLFSR.charAt(0) == valeurLFSR.charAt(5) ? 1 : 0)
                    ^ (valeurLFSR.charAt(0) == valeurLFSR.charAt(6) ? 1 : 0)
                    ^ (valeurLFSR.charAt(0) == valeurLFSR.charAt(7) ? 1 : 0);

            // Convertir le résultat du XOR (int) en char
            char xorDuChar = (xorDuBit == 1) ? '1' : '0';

            //on ajoute le bit de rétroaction à l'extrémité
            valeurLFSR = valeurLFSR.substring(1) + xorDuChar;

            //enfin on affiche l'état du LFSR en binaire et sa valeur décimale
            //radix 2 indique que le string est en binaire au moment de la conversion en long (un int ne peut pas
            //accepter autant de caractères)
            System.out.println("Tour " + (i + 1) + ": " + valeurLFSR + " (" + Long.parseLong(valeurLFSR, 2) + ")");
        }
    }
}
