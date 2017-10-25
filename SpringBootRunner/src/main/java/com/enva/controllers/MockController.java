package com.enva.controllers;

import java.util.ArrayList;
import java.util.List;

public class MockController {

    String getMyName() {
        return "Peter";
    }

    StringBuilder getStringBuilder() {
        return new StringBuilder("demo");
    }


    List<String> makeNameList(String startWith) {
        List<String> names = new ArrayList<>();
        names.add("name 1");
        names.add("name 2");
        names.add("name 3");
        names.add("name 4");

        return names;
    }
}
