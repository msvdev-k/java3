package com.gb.less06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Less06ClassTest {

    private Less06Class less06Class;

    @BeforeEach
    public void init() {
        less06Class = new Less06Class();
    }


    @Test
    public void LastElementsTest() {

        int[] arrIn1 = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] arrOut1 = {1, 7};

        Assertions.assertArrayEquals(arrOut1, less06Class.LastElements(arrIn1));
    }


    @ParameterizedTest
    @MethodSource
    public void testLastElements(int[] array, int[] result) {
        Assertions.assertArrayEquals(result, less06Class.LastElements(array));
    }

    public static Stream<Arguments> testLastElements() {
        List<Arguments> out = new ArrayList<>();

        out.add(Arguments.of(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}));
        out.add(Arguments.of(new int[]{1, 2, 4, 4, 2, 3, 5, 1, 7}, new int[]{2, 3, 5, 1, 7}));
        out.add(Arguments.of(new int[]{1, 2, 4, 3, 2, 3, 3, 1, 7}, new int[]{3, 2, 3, 3, 1, 7}));
        out.add(Arguments.of(new int[]{1, 2, 4, 4, 2, 3, 4, 4, 7}, new int[]{7}));
        //out.add(Arguments.of(new int[]{1, 2, 0, 0, 2, 3, 0, 0, 7}, new int[]{}));

        return out.stream();
    }


    @ParameterizedTest
    @MethodSource
    public void testArray14(int[] array, boolean result) {
        Assertions.assertEquals(result, less06Class.Array14(array));
    }

    public static Stream<Arguments> testArray14() {
        List<Arguments> out = new ArrayList<>();

        out.add(Arguments.of(new int[]{1, 1, 1, 4, 4, 1, 4, 4}, true));
        out.add(Arguments.of(new int[]{1, 1, 1, 1, 1, 1}, false));
        out.add(Arguments.of(new int[]{4, 4, 4, 4}, false));
        out.add(Arguments.of(new int[]{1, 4, 4, 1, 1, 4, 3}, false));

        return out.stream();
    }


}
