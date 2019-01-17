package com.github.fhr.compare;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LevenshteinDistanceComparerTest {

    @Test
    public void compare() {
        LevenshteinDistanceComparer comparer = new LevenshteinDistanceComparer();
        comparer.compare(new String[]{"abc","def"}, new String[]{"abc","mn","rt","def"});
    }
}