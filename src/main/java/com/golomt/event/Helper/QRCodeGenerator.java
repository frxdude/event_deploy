package com.golomt.event.Helper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.json.*;

import java.io.ByteArrayOutputStream;

public class QRCodeGenerator {
	QRCodeWriter qrCodeWriter = new QRCodeWriter();
	ImgurHelper imgur = new ImgurHelper();
	public static final int link_index = 27;

	public QRCodeGenerator() {
	}

	public String generateQRCode64(long eventId, String encodedData) throws WriterException, IOException {
		BufferedImage qrWithLogo = getQRWithLogo(encodedData);
//		File qrPath = new File("../../../../../../src/main/resources/qr/" + encodedData + ".png");
		String tomcatHome = System.getProperty("catalina.base");
		String uploadDir = System.getenv("UPLOAD_DIR");
		Path pathUpl = Paths.get(uploadDir + "/event/" + eventId +"/qr/");
		Path path = Paths.get(tomcatHome + "/webapps/ROOT/WEB-INF/classes/static/event/" + eventId +"/qr/");
		Files.createDirectories(pathUpl);
		Files.createDirectories(path);
		File qrPathUpl = new File( uploadDir + "/event/" + eventId +"/qr/" + encodedData + ".png");
		File qrPath = new File( tomcatHome + "/webapps/ROOT/WEB-INF/classes/static/event/" + eventId +"/qr/" + encodedData + ".png");
		System.out.println(qrPath.getAbsolutePath().toString());
		File qrToImgur = new File(encodedData + ".png");
		ImageIO.write(qrWithLogo, "png", qrPathUpl);
		ImageIO.write(qrWithLogo, "png", qrPath);
//		String response = imgur.postImgurPic(qrPath);
//		JSONObject object = new JSONObject(response);
//		JSONObject data = object.getJSONObject("data");
//		String data = object.getString("data");
//		String link = data.getString("link");
		return "https://events.golomtbank.com/event/" + eventId + "/qr/" + encodedData + ".png";
	}

	public File generateQRCodeImage(String text) throws WriterException, IOException {
		BufferedImage qrWithLogo = getQRWithLogo(text);
		File outputfile = new File("EventQR.png");
		ImageIO.write(qrWithLogo, "png", outputfile);
		return outputfile;
	}

	public BufferedImage getQRWithLogo(String text) throws WriterException, IOException {
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300);
		BufferedImage qr = MatrixToImageWriter.toBufferedImage(bitMatrix);
//		BufferedImage qrWithLogo = getQRCodeWithOverlay(qr);
		return qr;
	}
//
//	public BufferedImage getQRCodeWithOverlay(BufferedImage qrcode) throws IOException {
//		BufferedImage scaledOverlay = scaleOverlay(qrcode);
//
//		Integer deltaHeight = qrcode.getHeight() - scaledOverlay.getHeight();
//		Integer deltaWidth = qrcode.getWidth() - scaledOverlay.getWidth();
//
//		BufferedImage combined = new BufferedImage(qrcode.getWidth(), qrcode.getHeight(), BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2 = (Graphics2D) combined.getGraphics();
//		g2.drawImage(qrcode, 0, 0, null);
//		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
//		g2.drawImage(scaledOverlay, Math.round(deltaWidth / 2), Math.round(deltaHeight / 2), null);
//		return combined;
//	}
//	
//	private BufferedImage scaleOverlay(BufferedImage qrcode) throws IOException {
//		Integer scaledWidth = (int) Math.round(qrcode.getWidth() * 0.13);
//		Integer scaledHeight = (int) Math.round(qrcode.getHeight() * 0.13);
//		Image glogo = ImageIO.read(new File("/golomt-event/src/main/resources/Logo-2.png"));
//		BufferedImage imageBuff = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
//		Graphics g = imageBuff.createGraphics();
//		g.drawImage(glogo.getScaledInstance(scaledWidth, scaledHeight, BufferedImage.SCALE_SMOOTH), 0, 0,
//				new Color(212, 86, 144), null);
//		g.dispose();
//		return imageBuff;
//	}
}