package com.enva.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockControllerTest {

    @Mock
    MockController mockController;

    @Before
    public void init() {
        mockController = new MockController();
        Mockito.spy(mockController);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMyName_should_return_the_name() {
        String fakeName = "gigel";
        StringBuilder fakeStringBuilder = new StringBuilder("fakeStringBuilder");
        String result;
        StringBuilder resultStringBuilder;


        Mockito.when(mockController.getMyName()).thenReturn(fakeName);
        Mockito.when(mockController.getStringBuilder()).thenReturn(fakeStringBuilder);

        System.out.println(mockController.getMyName());
        System.out.println(mockController.getStringBuilder());

    }
}
