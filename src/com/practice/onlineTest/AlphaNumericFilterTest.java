package com.practice.onlineTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AlphaNumericFilterTest - Unit tests for AlphaNumericFilter class
 * 
 * Problem: Test alphanumeric character filtering functionality
 * 
 * Test Cases:
 * 1. Normal input with mixed characters
 * 2. Empty string input
 * 3. Null input edge case
 * 
 * Algorithm: JUnit 5 assertions for validation
 * 
 * Time Complexity: O(1) per test method
 * Space Complexity: O(1) per test method
 */
class AlphaNumericFilterTest {

    /**
     * Test filtering alphanumeric characters from mixed input
     * 
     * Input: "a@b#12$c!" 
     * Expected: "ab12c"
     * 
     * Validates that only letters and digits are preserved
     */
    @Test
    void testFilter() {

        assertEquals(
                "ab12c",
                AlphaNumericFilter
                        .filterAlphaNumeric(
                                "a@b#12$c!"));
    }

    /**
     * Test empty string input
     * 
     * Input: "" 
     * Expected: ""
     * 
     * Validates that empty input returns empty output
     */
    @Test
    void testEmpty() {

        assertEquals(
                "",
                AlphaNumericFilter
                        .filterAlphaNumeric(""));
    }

    /**
     * Test null input edge case
     * 
     * Input: null 
     * Expected: null
     * 
     * Validates that null input returns null
     */
    @Test
    void testNull() {

        assertNull(
                AlphaNumericFilter
                        .filterAlphaNumeric(null));
    }
}