import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Steganographie {
    public static void main(String[] args) {
        try {
            //Création d'un bufferedImage pour l'image écran et l'image secrête
            //type qui détient les informations d'une image comme sa taille, sa couleur, etc...
            BufferedImage imageEcran = ImageIO.read(new File("images/pokemon.png"));
            BufferedImage imageSecrete = ImageIO.read(new File("images/chatTropMignon.png"));

            //fonction pour encoder l'image
            BufferedImage imageEncodee = encoderImage(imageEcran, imageSecrete);

            //sauvegarde de l'image encodée
            ImageIO.write(imageEncodee, "png", new File("imagesEncodeDecode/imageEncodee.png"));
            System.out.println("L'image a été encodée");

            //fonction pour décoder l'image
            BufferedImage imageDecodee = decoderImage(imageEncodee, imageSecrete.getWidth(), imageSecrete.getHeight());

            //sauvegarde de l'image décodée
            ImageIO.write(imageDecodee, "png", new File("imagesEncodeDecode/imageDecodee.png"));
            System.out.println("L'image cachée a été décodée");
        } catch (Exception e) {
            System.out.println("erreur" + e.getMessage());
        }
    }

    public static BufferedImage encoderImage(BufferedImage imageEcran, BufferedImage imageSecrete) {

        //récupération des dimensions de l'image hôte
        int longueurImageEcran = imageEcran.getWidth();
        int hauteurImageEcran = imageEcran.getHeight();

        //nouvelle image où l'image encodée sera stockée, ayant donc les dimensions de l'image écran
        BufferedImage imageEncodee = new BufferedImage(longueurImageEcran, hauteurImageEcran, BufferedImage.TYPE_INT_RGB);

        //on parcours chaque pixel
        for (int x = 0; x < longueurImageEcran; x++) {
            for (int y = 0; y < hauteurImageEcran; y++) {

                //on récupere la couleur du pixel actuel
                Color couleurActuelle = new Color(imageEcran.getRGB(x, y));

                //bits faibles des pixels Rouge, Vert, Bleu
                int rouge = couleurActuelle.getRed();
                int vert = couleurActuelle.getGreen();
                int bleu = couleurActuelle.getBlue();

                //on modifie les bits faibles uniquement si l'on est dans les dimensions de l'image secrète
                if (x < imageSecrete.getWidth() && y < imageSecrete.getHeight()) {
                    Color couleurImageSecrete = new Color(imageSecrete.getRGB(x, y));

                    //on insére les bits forts de l'image secrète dans les bits faibles de l'image écran
                    //(red & 0xFE): met le dernier bit (bit faible) à 0 du rouge de l'image écran.
                    //(secretColor.getRed() >> 7) & 0x01 : récupère le bit de poids fort du rouge de l'image secrète pour
                    //le mettre dans le bit faible de l'image encodée.
                    rouge = (rouge & 0xFE) | ((couleurImageSecrete.getRed() >> 7) & 0x01);
                    vert = (vert & 0xFE) | ((couleurImageSecrete.getGreen() >> 7) & 0x01);
                    bleu = (bleu & 0xFE) | ((couleurImageSecrete.getBlue() >> 7) & 0x01);
                }

                //construit la nouvelle couleur
                Color nouvelleCouleur = new Color(rouge, vert, bleu);

                //modifie au pixel actuel avec la nouvelle couleur
                imageEncodee.setRGB(x, y, nouvelleCouleur.getRGB());
            }
        }

        return imageEncodee;
    }

    public static BufferedImage decoderImage(BufferedImage encodedImage, int secretWidth, int secretHeight) {
        BufferedImage decodedImage = new BufferedImage(secretWidth, secretHeight, BufferedImage.TYPE_INT_RGB);

        //on parcours chaque pixels contenu dans la taille de l'image secrète afin de cibler les pixels de l'image cachée
        for (int x = 0; x < secretWidth; x++) {
            for (int y = 0; y < secretHeight; y++) {

                //on récupere la couleur du pixel actuel
                Color couleurEncodee = new Color(encodedImage.getRGB(x, y));

                // on extrait les bits faibles
                int rouge = (couleurEncodee.getRed() & 0x01) << 7;
                int vert = (couleurEncodee.getGreen() & 0x01) << 7;
                int bleu = (couleurEncodee.getBlue() & 0x01) << 7;

                //on construit la nouvelle couleur (donc tous les pixels faibles de l'image encodée)
                Color secretColor = new Color(rouge, vert, bleu);

                //modifie au pixel actuel avec la nouvelle couleur
                decodedImage.setRGB(x, y, secretColor.getRGB());
            }
        }

        return decodedImage;
    }
}
