package com.enva;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.Banner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	/**
	 * Run the command liner
	 */
	public void run(String... args) throws Exception {
		logger.info("Started apps");
		ArgumentParser arguments = new ArgumentParser(args);
		if (arguments.contain("runClass")) {
			switch (arguments.getValue("runClass")) {
			case "MapBenchmark":
				new MapBenchmark();
				break;
			}
		}

	}

}
