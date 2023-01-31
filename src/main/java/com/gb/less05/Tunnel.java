package com.gb.less05;

import java.util.concurrent.Semaphore;


public class Tunnel extends Stage {

    private final Semaphore smp;


    public Tunnel(int semaphore) {

        smp = new Semaphore(semaphore);

        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }


    @Override
    public void go(Car c) {

        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждёт): " + description);

                smp.acquire();

                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000L);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
