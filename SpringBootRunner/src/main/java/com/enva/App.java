package com.enva;

import com.enva.controllers.MapBenchmark;
import com.enva.controllers.ZipCreator;
import com.enva.helpers.ArgumentParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.Banner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Stream;

import java.util.Arrays;

/**
 * Hello world!
 */
@SpringBootApplication
public class App implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.setBannerMode(Banner.Mode.OFF);

		app.run(args);
	}

	/**
	 * Run the command liner
	 */
	public void run(String... args) throws Exception {
		LOGGER.info("Started apps");
		String runClass = null;if(ObjectUtils.isEmpty(args)) {
			LOGGER.error("You have no parameters") ;
			showUsage();
			return;
		}

		Properties properties = StringUtils.splitArrayElementsIntoProperties(args, "=");

		runClass = properties.getProperty("--runClass");

		if (runClass != null) {
			switch (runClass) {
				case "MapBenchmark":
					new MapBenchmark();
					break;
                case "ZipCreator":
                    new ZipCreator();
                    break;
			}
		}
	}

	/**
	 * Show the usage if no parameter was provided
	 */
	private void showUsage() {
		StringBuilder stars = new StringBuilder();
		int starsCount = 50;
		Stream.generate(() -> "*")
				.limit(starsCount)
				.forEach(stars::append);
		LOGGER.info("{}", stars.toString());
		LOGGER.info("Spring boot console");
		LOGGER.info("{}", stars.toString());
		
	}
}
