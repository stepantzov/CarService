package com.mycarservice.services;

import junit.framework.TestCase;

public class CarServiceTest extends TestCase {
    public void testServicePrinter(){
        assertEquals("Car web service.", CarService.getCarInstances().contains(2101));
    }
}