package com.gb.less05;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Car implements Runnable {

    private static int CARS_COUNT = 0;

    private static CountDownLatch cdlOnYourMarks;
    private static CyclicBarrier cyclicBarrierGo;
    private static CountDownLatch cdlFinish;

    private final static Lock lock = new ReentrantLock();

    private static boolean finishedFirst = true;

    private final Race race;
    private final int speed;
    private final String name;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }


    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

            cdlOnYourMarks.countDown();
            cyclicBarrierGo.await();

        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        try {
            lock.lock();

            if (finishedFirst) {
                finishedFirst = false;
                System.out.println(this.name + " - WIN");
            }

        } finally {
            lock.unlock();
        }


        cdlFinish.countDown();
    }


    /**
     * На старт
     */
    public static void onYourMarks() {
        cdlOnYourMarks = new CountDownLatch(CARS_COUNT);
        cdlFinish = new CountDownLatch(CARS_COUNT);
        cyclicBarrierGo = new CyclicBarrier(CARS_COUNT + 1);
    }


    /**
     * Внимание
     */
    public static void getSet() {
        try {
            cdlOnYourMarks.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Марш!
     */
    public static void go() {
        try {
            cyclicBarrierGo.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


    /**
     * Финишировали все участники. Гонка закончена
     */
    public static void finishedAll() {
        try {
            cdlFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

}
