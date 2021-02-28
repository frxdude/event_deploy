package com.golomt.event.Helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImgurHelper {
	public static final String myClientId = "e631dd45129f01d";
	OkHttpClient client = new OkHttpClient();
	
	public String postImgurPic(File image) throws IOException {
		RequestBody body = RequestBody.create(MediaType.parse("image/png"), image);
		Request request = new Request.Builder().url("https://api.imgur.com/3/image")
				.addHeader("Authorization", "Client-ID " + myClientId)
				.post(body)
				.build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	public String getImgurPic(String encodedData) throws IOException {
		  Request request = new Request.Builder()
		      .url("https://api.imgur.com/3/image/" + encodedData)
		      .addHeader("Authorization", "Client-ID " + myClientId)
		      .build();
		  try (Response response = client.newCall(request).execute()) {
		    return response.body().string();
		  }
		}
//	public void postImgurPic()
//	{
//		URL url = new URL("https://api.imgur.com/3/image");
//	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//	    String data = URLEncoder.encode("image", "UTF-8") + "="
//	            + URLEncoder.encode(IMAGE_URL, "UTF-8");
//
//	    conn.setDoOutput(true);
//	    conn.setDoInput(true);
//	    conn.setRequestMethod("POST");
//	    conn.setRequestProperty("Authorization", "Client-ID " + "e631dd45129f01d");
//	    conn.setRequestMethod("POST");
//	    conn.setRequestProperty("Content-Type",
//	            "application/x-www-form-urlencoded");
//
//	    conn.connect();
//	    StringBuilder stb = new StringBuilder();
//	    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//	    wr.write(data);
//	    wr.flush();
//	}
}
