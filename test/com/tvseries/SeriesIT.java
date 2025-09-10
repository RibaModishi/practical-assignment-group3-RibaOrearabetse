/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.tvseries;

import com.tvseries.Series;
import com.tvseries.SeriesModel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lab_services_student
 */
public class SeriesIT {
    
    private Series series;
    
    @Before
    public void setUp() {
        series = new Series();
        series.addSeriesForTest("S001", "The Crown", "13", "50");
    }

    @Test
    public void testSearchSeries() {
        System.out.println("Executing TestSearchSeries()...");
        SeriesModel foundSeries = series.searchSeriesById("S001");
        assertNotNull(foundSeries);
        assertEquals("The Crown", foundSeries.getSeriesName());
    }
    
    @Test
    public void testSearchSeries_SeriesNotFound() {
        System.out.println("Executing TestSearchSeries_SeriesNotFound()...");
        SeriesModel notFoundSeries = series.searchSeriesById("S999");
        assertNull(notFoundSeries);
    }
    
    @Test
    public void testUpdateSeries() {
        System.out.println("Executing TestUpdateSeries()...");
        boolean isUpdated = series.updateSeries("S001", "The Crown Updated", null, null);
        assertTrue(isUpdated);
        SeriesModel updatedSeries = series.searchSeriesById("S001");
        assertEquals("The Crown Updated", updatedSeries.getSeriesName());
    }

    @Test
    public void testDeleteSeries() {
        System.out.println("Executing TestDeleteSeries()...");
        boolean isDeleted = series.deleteSeries("S001");
        assertTrue(isDeleted);
        assertNull(series.searchSeriesById("S001"));
    }

    @Test
    public void testDeleteSeries_SeriesNotFound() {
        System.out.println("Executing TestDeleteSeries_SeriesNotFound()...");
        boolean isNotDeleted = series.deleteSeries("S999");
        assertFalse(isNotDeleted);
    }
    
    @Test
    public void testSeriesAgeRestriction_AgeValid() {
        System.out.println("Executing TestSeriesAgeRestriction_AgeValid()...");
        assertTrue(series.validateAgeRestriction("13"));
        assertTrue(series.validateAgeRestriction("2"));
        assertTrue(series.validateAgeRestriction("18"));
    }
    
    @Test
    public void testSeriesAgeRestriction_SeriesAgeInvalid() {
        System.out.println("Executing TestSeriesAgeRestriction_SeriesAgeInvalid()...");
        assertFalse(series.validateAgeRestriction("1"));
        assertFalse(series.validateAgeRestriction("19"));
        assertFalse(series.validateAgeRestriction("abc"));
        assertFalse(series.validateAgeRestriction(""));
    }
}