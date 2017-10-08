package com.enva.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.enva.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapBenchmark {
	private static final Logger logger = LoggerFactory.getLogger(MapBenchmark.class);
	private long startTime;
	private long stopTime;
	private long elapsedTime;
	Map<String, Person> list;
	
	public MapBenchmark() {
		list = new HashMap<>();
		createList(list);
		forEachEntrySet(list);
		forEachValues(list);
		iteratorEntrySet(list);
		iteratingOverKeys(list);
		logger.info("*****************************");
		logger.info("* Change the iteration order");
		logger.info("*****************************");
		iteratingOverKeys(list);
		iteratorEntrySet(list);
		forEachValues(list);
		forEachEntrySet(list);
	}


	/**
	 * Create the list of elements
	 * 
	 * @param list
	 */
	private void createList(Map<String, Person> list) {
		createList(list, 10_000_000);
	}

	/**
	 * Create the list of elements
	 * 
	 * @param list -  the list
	 * @param objNum - number of objects
	 */
	private void createList(Map<String, Person> list, int objNum) {
		logger.info("Prepare to add " + objNum + " of objects");
		startTime();
		Random randomGenerator = new Random();
		for (int n = 0; n < objNum; ++n) {
			list.put("k" + n + randomGenerator.nextInt(1_000_000), new Person());
		}
		logger.info("Added " + objNum + " of object");
		endTime();
		showResults();
	}

	/**
	 * For each iterator with
	 * 
	 * @param list
	 */
	private void forEachEntrySet(Map<String, Person> list) {
		startTime();
		logger.info("Prepear to forEachEntrySet");
		for (Map.Entry<String, Person> entry : list.entrySet()) {
			entry.getValue().setUsername("admin");
		}
		endTime();
		showResults();
		logger.info("end forEachEntrySet");
		
	}

	/**
	 * 
	 * @param list
	 */
	private void forEachValues(Map<String, Person> list) {
		startTime();
		logger.info("Prepear to forEachValues");
		for (Person entry : list.values()) {
			entry.setUsername("admin");
		}
		endTime();
		showResults();
		logger.info("end forEachValues");
		
	}

	/**
	 * 
	 * @param list
	 */
	private void iteratorEntrySet(Map<String, Person> list) {
		startTime();
		logger.info("Prepare to iteratorEntrySet");
		Iterator<Map.Entry<String, Person>> entries = list.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Person> entry = entries.next();
			entry.getValue().setUsername("admin");
		}
		endTime();
		showResults();
		logger.info("end iteratorEntrySet");
	}

	/**
	 * 
	 * @param list
	 */
	public void iteratingOverKeys(Map<String, Person> list) {
		startTime();
		logger.info("Prepare to iteratingOverKeys");
		for (String key : list.keySet()) {
			Person entry = list.get(key);
			entry.setUsername("admin");
		}
		endTime();
		showResults();
		logger.info("end iteratingOverKeys");
	}

	/**
	 * Start the time
	 */
	private void startTime() {
		startTime = System.currentTimeMillis();
	}

	/**
	 * End the time
	 */
	private void endTime() {
		stopTime = System.currentTimeMillis();
	}

	/**
	 * Show results
	 */
	private void showResults() {
		elapsedTime = stopTime - startTime;
		logger.info("Executed in " + elapsedTime + " milliseconds");
	}
}
