package hide;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class ShowMessage {

	public void deStegano(int size) {
		try {

			BufferedImage image = ImageIO.read(new FileInputStream("stegoFinal.bmp"));
			
			int pixel;
			char pixelNew[] = new char[32];
			char message[] = new char[size];
			int pos = 0;
			for (int i = 0; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight(); j++) {

					if (pos == size)
						break;
					
					pixel = image.getRGB(i, j);
					
					Useful.copyTo(Integer.toBinaryString(pixel), pixelNew);
					
					if (pos == size) {
						break;						
					}
					message[pos] = pixelNew[15];
					pos++;
					if (pos == size) {
						break;
					}
					message[pos] = pixelNew[23];
					pos++;

					if (pos == size) {
						break;
					}
					message[pos] = pixelNew[31];
					pos++;
				}
			}
			System.out.print("\nMessage retrouvé : ");
			for (int i = 0; i < message.length; i++) {
				System.out.print(message[i]);
			}			

		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
