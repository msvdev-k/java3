package com.gb;

public abstract class Fruit {

    /**
     * Вес фрукта.
     */
     final float weight;


    /**
     * Основной конструктор класса.
     * @param weight вес фрукта.
     */
    public Fruit(float weight) {
        this.weight = weight;
    }


    /**
     * Получить вес фрукта.
     * @return вес.
     */
    public float getWeight() {
        return weight;
    }

}
