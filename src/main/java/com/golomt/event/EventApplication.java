package com.golomt.event;


import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class EventApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
		try {
			String tomcatHome = System.getProperty("catalina.base");
			String uploadDir = System.getenv("UPLOAD_DIR");

			Path pathUpl = Paths.get(uploadDir + "/event");
			Path path = Paths.get(tomcatHome + "/webapps/ROOT/WEB-INF/classes/static/event");
			Files.createDirectories(pathUpl);
			Files.createDirectories(path);

			File qrPathUpl = new File( uploadDir + "/event");
			File qrPath = new File( tomcatHome + "/webapps/ROOT/WEB-INF/classes/static/event");
			FileUtils.copyDirectory(qrPathUpl, qrPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
