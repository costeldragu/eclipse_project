package com.enva;

import com.enva.controllers.RegularExpression;
import com.enva.controllers.ZipCreator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RegularExpressionTest {


    @Mock
    ZipCreator zipCreator;


    RegularExpression regularExpression;

    @Before
    public void init() {
        regularExpression = new RegularExpression();
    }

    @Test
    public void testGetErrorCategory() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Internal DAILY - Error found", "DAILY ERROR");
        errorMap.put("No of bucket: 5 Expected 50", "No of bucket error");
        errorMap.put("The PROBLEM_WITH_UNDERSCORE -found it ", "PROBLEM_WITH_UNDERSCORE message");
        for (Map.Entry<String, String> entry : errorMap.entrySet()) {
            assertEquals(entry.getValue(), regularExpression.getErrorCategory(entry.getKey()));
        }
    }

    @Test
    public void testGetErrorCode() {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error: 4056 code - not found  Error: 8455 code - not found, Error: 958 code - not found", "4056 8455 958 ");
        errorMap.put("The PROBLEM_WITH_UNDERSCORE -found it ", "404");
        for (Map.Entry<String, String> entry : errorMap.entrySet()) {
            assertEquals(entry.getValue(), regularExpression.getErrorCode(entry.getKey()));
        }
    }
}
