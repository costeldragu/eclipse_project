package com.enva;

import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArgumentParser {
	private static final Logger logger = LoggerFactory.getLogger(ArgumentParser.class);
	private String[] args;
	private Map<String, String> mapArgs;
	private String lastKeyCheck;
	private boolean lastKeyCheckResult;

	ArgumentParser() {
	}

	ArgumentParser(String... args) {
		this.args = args;
		processArgs();
	}

	private void processArgs() {
		logger.info(Arrays.deepToString(args));
		for (String arg : args) {
			if (arg.startsWith("--")) {
				arg = arg.replace("--", "");
				String[] splitArg = arg.split("=");
				logger.info(Arrays.deepToString(splitArg));
				if (splitArg.length > 1) {
					logger.info("0:" + splitArg[0]);
					logger.info("1:" + splitArg[1]);
					mapArgs.put(splitArg[0], splitArg[1]);
				} else if (splitArg.length == 1) {
					mapArgs.put(splitArg[0], "");
				}
			}
		}

		// TODO Auto-generated method stub

	}

	/**
	 * Verify if a key exits
	 * 
	 * @param key
	 * @return
	 */
	public boolean contain(String key) {
		try {
			lastKeyCheckResult = mapArgs.containsKey(key);
			lastKeyCheck = key;
			return lastKeyCheckResult;
		} catch (NullPointerException e) {
			return false;
		}

	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if (key.equals(lastKeyCheck) && lastKeyCheckResult == true) {
			return mapArgs.get(key);
		} else {
			if (contain(key)) {
				return mapArgs.get(key);
			} else {
				return "";
			}
		}
	}

}
