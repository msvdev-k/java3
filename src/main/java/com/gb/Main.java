package com.gb;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //////////////////////////////////////////////
        // Первое задание
        Integer[] intArray = {1, 2, 3, 4, 5, 6};
        Double[] doubleArray = {1.5, 2.5, 3.5, 4.5, 5.5, 6.5};

        changeArrayItems(intArray, 1, 4);
        changeArrayItems(doubleArray, 2, 5);

        printArray(intArray);
        printArray(doubleArray);


        //////////////////////////////////////////////
        // Второе задание
        printList(toArrayList(intArray));
        printList(toArrayList(doubleArray));


        //////////////////////////////////////////////
        // Третье задание
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        appleBox1.putFruit(new Apple());
        appleBox1.putFruit(new Apple());
        System.out.println("Вес первой коробки " + appleBox1.getWeight());

        appleBox2.putFruit(new Apple());
        appleBox2.putFruit(new Apple());
        appleBox2.putFruit(new Apple());
        System.out.println("Вес второй коробки " + appleBox2.getWeight());

        orangeBox.putFruit(new Orange());
        orangeBox.putFruit(new Orange());
        System.out.println("Вес коробки с апельсинами " + orangeBox.getWeight());

        System.out.println(orangeBox.compare(appleBox1));
        System.out.println(orangeBox.compare(appleBox2));


        appleBox1.shiftFruits(appleBox2);
        System.out.println("Вес первой коробки " + appleBox1.getWeight());
        System.out.println("Вес второй коробки " + appleBox2.getWeight());

        //orangeBox.shiftFruits(appleBox1);

    }


    /**
     * Вывод элементов массива на экран.
     * @param arr массив.
     */
    public static <T> void printArray(T[] arr) {
        for (T t : arr) {
            System.out.print(t + ", ");
        }
        System.out.println();
    }

    /**
     * Вывод элементов списка на экран.
     * @param list массив.
     */
    public static <T> void printList(List<T> list) {
        for (T t : list) {
            System.out.print(t + ", ");
        }
        System.out.println();
    }


    /**
     * Метод, меняющий два элемента массива местами.
     * @param arr массив.
     * @param first индекс первого элемента.
     * @param second индекс второго элемента.
     */
    public static <T> void changeArrayItems(T[] arr, int first, int second) {

        if (arr == null) {
            return;
        }

        if (first < 0 || first >= arr.length) {
            throw new IndexOutOfBoundsException();
        }

        if (second < 0 || second >= arr.length) {
            throw new IndexOutOfBoundsException();
        }

        T item = arr[first];
        arr[first] = arr[second];
        arr[second] = item;

    }


    /**
     * Метод, преобразующий массив в ArrayList.
     * @param arr массив.
     * @return ArrayList.
     */
    public static <T> ArrayList<T> toArrayList(T[] arr) {
        return new ArrayList<T>(Arrays.<T>asList(arr));
    }


}
