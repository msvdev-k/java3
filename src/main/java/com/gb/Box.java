package com.gb;

import java.util.ArrayList;
import java.util.List;


public class Box<T extends Fruit> {

    /**
     * Коробка с фруктами.
     */
    private final List<T> fruits = new ArrayList<>();


    /**
     * Добавить фрукт в коробку.
     * @param fruit добавляемый фрукт.
     */
    public void putFruit(T fruit) {
        fruits.add(fruit);
    }


    /**
     * Взять первый попавшийся фрукт из коробки.
     * Забираемый фрукт из коробки удаляется.
     * @return первый попавшийся фрукт, либо null если коробка пустая.
     */
    public T takeFruit() {

        if (fruits.isEmpty()) {
            return null;
        }

        T fruit = fruits.get(0);
        fruits.remove(fruit);

        return fruit;
    }


    /**
     * Получить вес фруктов в коробке.
     * @return масса нетто.
     */
    public float getWeight() {
        float weight = 0f;

        for (T fruit: fruits) {
            weight += fruit.getWeight();
        }

        return weight;
    }


    /**
     * Сравнить веса нетто.
     * @param anotherBox сравниваемая коробка.
     * @return true - веса коробок одинаковые, false - разные.
     */
    public boolean compare(Box<?> anotherBox) {
        return Math.abs(getWeight() - anotherBox.getWeight()) < 1e-5f;
    }


    /**
     * Переложить фрукты из одной коробки в другую.
     * @param anotherBox коробка из которой пересыпаются фрукты.
     */
    public void shiftFruits(Box<T> anotherBox) {

        T fruit = anotherBox.takeFruit();

        while (fruit != null) {
            fruits.add(fruit);
            fruit = anotherBox.takeFruit();
        }

    }



}
