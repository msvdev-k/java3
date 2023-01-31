package com.gb.less06;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Less06Class {

    /*
     Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
     Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
     идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
     необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных).
     Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
     */
    public int[] LastElements(int[] inputArray) {

        int lastIndex = -1;

        for (int i = inputArray.length-1; i >= 0; i--) {
            if (inputArray[i] == 4) {
                lastIndex = i;
                break;
            }
        }

        if (lastIndex < 0) {
            throw new RuntimeException("Цифры 4 в массиве нет!");
        }

        int[] outArray = new int[inputArray.length - lastIndex - 1];

        for (int i = 0; i < outArray.length; i++) {
            outArray[i] = inputArray[i + lastIndex + 1];
        }

        return outArray;

    }



   /*
    Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
    то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    */
    public boolean Array14(int[] inputArray) {

        boolean one = false;
        boolean four = false;

        for (Integer integer : inputArray) {

            switch (integer) {
                case 1:
                    one = true;
                    break;
                case 4:
                    four = true;
                    break;
                default:
                    return false;
            }
        }

        return one && four;
    }



}
