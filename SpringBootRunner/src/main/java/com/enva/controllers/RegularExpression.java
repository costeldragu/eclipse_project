package com.enva.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

    public String getErrorCategory(String message) {
        Map<Pattern, String> patterns = new HashMap<>();
        patterns.put(Pattern.compile("DAILY"), "DAILY ERROR");
        patterns.put(Pattern.compile("No of bucket:\\s+[0-9]\\s+Expected\\s+[0-9]"), "No of bucket error");
        patterns.put(Pattern.compile("PROBLEM_WITH_UNDERSCORE"), "PROBLEM_WITH_UNDERSCORE message");

        for (Map.Entry<Pattern, String> entry : patterns.entrySet()) {
            Matcher m = entry.getKey().matcher(message);
            if (m.find()) {
                return entry.getValue();
            }
        }
        return "";
    }


    public String getErrorCode(String message) {

        Map<Pattern, String> patterns = new HashMap<>();
        patterns.put(Pattern.compile("(?<=Error: )[0-9]+(?= code)"), "");
        if (message.length() > 0) {
            for (Map.Entry<Pattern, String> entry : patterns.entrySet()) {
                StringBuilder codes = new StringBuilder();
                Matcher m = entry.getKey().matcher(message);
                while (m.find()) {
                    codes.append(m.group());
                    codes.append(" ");
                }
                if (codes.length() > 0) {
                    return codes.toString();
                }
            }

            return "404";
        }


        return "";
    }

}
