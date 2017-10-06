package com.enva;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.enva.trading.MarketWatcher;
import com.enva.trading.Portofolio;

@RunWith(MockitoJUnitRunner.class)
public class StockBrokerTest {
	
	  @Mock 
	  MarketWatcher marketWatcher;

	  @Mock 
	  Portofolio portfolio;
	  
	  @Before 
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  
	  }
	  

	  @Test 
	  public void sanity() throws Exception {
	    assertNotNull(marketWatcher);
	    assertNotNull(portfolio);
	  }

}
