package com.enva.helpers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
public class ArgumentParser {
	private static final Logger logger = LoggerFactory.getLogger(ArgumentParser.class);
	private String[] args;
	private Map<String, String> mapArgs;
	private String lastKeyCheck;
	private boolean lastKeyCheckResult;

	public ArgumentParser() {
		mapArgs = new HashMap<>();
	}

	public  ArgumentParser(String... args) {
		this();
		this.args = args;
		processArgs();
	}

	private void processArgs() {
		for (String arg : args) {
			if (arg.startsWith("--")) {
				arg = arg.replace("--", "");
				String[] splitArg = arg.split("=");
				if (splitArg.length > 1) {
					mapArgs.put(splitArg[0], splitArg[1]);
				} else if (splitArg.length == 1) {
					mapArgs.put(splitArg[0], "");
				}
			}
		}
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

	@Override
	public String toString() {
		return "ArgumentParser{" +
				"args=" + Arrays.toString(args) +
				", mapArgs=" + mapArgs +
				", lastKeyCheck='" + lastKeyCheck + '\'' +
				", lastKeyCheckResult=" + lastKeyCheckResult +
				'}';
	}
}
