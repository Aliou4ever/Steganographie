package hide;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class HideMessage {

	int size;
	Scanner sc;
	public int steganographie() {
		try {

			BufferedImage image = ImageIO.read(new FileInputStream("stego.bmp"));
			BufferedImage imageFinal = ImageIO.read(new FileInputStream("stego.bmp"));

			sc = new Scanner(System.in);
			System.out.print("Entrer le message � cacher : ");
			String message = sc.nextLine(); 
			
			char[] ascii; 
			char[] cleAscii = new char[8 * message.length()];
			String cle = "";

			ascii = message.toCharArray();
			for (int i = 0; i < ascii.length; i++) {
				cle = cle + Integer.toBinaryString(ascii[i]);
			}
			//on met la cl� sous forme de tableau de char
			cleAscii = cle.toCharArray();

			System.out.print("message : ");
			for (int i = 0; i < cleAscii.length; i++) {
				System.out.print(cleAscii[i]);
			}
			
			System.out.println();

			int pixel;
			char pixelNew[] = new char[32];
			char tabCle[] = new char[3];
			int pos = 0;

			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {

					if (pos > cleAscii.length) {
						break;
					}
					//On prend trois bits du message
					Useful.copyToTab3(tabCle, cleAscii, pos);

					System.out.print("\nOn  va cacher : ");
					System.out.print(tabCle[0] + "" + tabCle[1] + "" + tabCle[2]);
					pos = pos + 3;

					System.out.println();
					
					//On r�cup�re un pixel � la position (i,j)
					pixel = image.getRGB(i, j);
					
					//On transforme le pixel en chaine binaire
					Useful.copyTo(Integer.toBinaryString(pixel), pixelNew);

					System.out.print("pixel Initial : ");
					
					for (int k = 0; k < pixelNew.length; k++) {
						System.out.print(pixelNew[k]);
					}
					System.out.println();

					// on va remplacer les bits de poids faible de chaque pixel par notre bit � cacher
					if (tabCle[0] == 'X') {
						break;
					}
					if (tabCle[1] == 'X') {
						pixelNew[15] = tabCle[0];
						System.out.print("pixel   Final : ");
						for (int l = 0; l < pixelNew.length; l++) {
							System.out.print(pixelNew[l]);
						}
						System.out.println();
						
						String pixString = "";
						for (int p = 0; p < pixelNew.length; p++) {
							pixString = pixString + pixelNew[p];
						}
						int pixInt = (int) Long.parseLong(pixString, 2);
						imageFinal.setRGB(i, j, pixInt);
						
						break;
					}
					if (tabCle[2] == 'X') {
						pixelNew[23] = tabCle[1];
						pixelNew[15] = tabCle[0];
						System.out.print("pixel   Final : ");
						for (int m = 0; m < pixelNew.length; m++) {
							System.out.print(pixelNew[m]);
						}
						String pixString = "";
						for (int p = 0; p < pixelNew.length; p++) {
							pixString = pixString + pixelNew[p];
						}
						int pixInt = (int) Long.parseLong(pixString, 2);
						imageFinal.setRGB(i, j, pixInt);
						break;
					}
					//On met � jour les nouveaux pixels
					pixelNew[31] = tabCle[2];
					pixelNew[23] = tabCle[1];
					pixelNew[15] = tabCle[0];
					
					System.out.print("pixel   Final : ");
					for (int n = 0; n < pixelNew.length; n++) {
						System.out.print(pixelNew[n]);
					}
					System.out.println();
					
					//On transforme le tableau de char en chaine 
					String pixString = "";
					for (int p = 0; p < pixelNew.length; p++) {
						pixString = pixString + pixelNew[p];
					}
					//On r�cup�re dans un pixel(int) la chaine � rajouter
					int pixInt = (int) Long.parseLong(pixString, 2);					

					//On met � jour le nouveau pixel dans la nouvelle image				
					imageFinal.setRGB(i, j, pixInt);
					
				}
			}
			
			//On �crit notre image dans un fichier
			 
			File output = new File("stegoFinal.bmp");			 
			ImageIO.write(imageFinal, "bmp", output);

			System.out.print("\n\nLe message cach� : ");
			for (int i = 0; i < cleAscii.length; i++) {
				System.out.print(cleAscii[i]);
			}
			//on retourne la taille du message cach�
			size = cleAscii.length;
			
		} catch (

		Exception e)

		{
			e.printStackTrace();
		}
		return size;
	}
	
	public int getSize(){
		return size;
	}

}
