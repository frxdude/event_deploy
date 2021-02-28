package com.golomt.event.Helper;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class ImageResizer {
	public BufferedImage resizer(File imageFile) throws IOException {
		BufferedImage image = ImageIO.read(imageFile);
		BufferedImage resizedImage = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(image, 0, 0, 500, 300, null);
		graphics2D.dispose();
		return resizedImage;
	}
}
