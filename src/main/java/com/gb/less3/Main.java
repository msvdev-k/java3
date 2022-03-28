package com.gb.less3;


public class Main {

    public static void main(String[] args) {

        new Thread(new PrintLetter('A', 'B', 5)).start();
        new Thread(new PrintLetter('B', 'C', 5)).start();
        new Thread(new PrintLetter('C', 'A', 5)).start();

//        new Thread(new PrintLetter('A', 'B', 15)).start();
//        new Thread(new PrintLetter('B', 'C', 15)).start();
//        new Thread(new PrintLetter('C', 'D', 15)).start();
//        new Thread(new PrintLetter('D', ' ', 15)).start();
//        new Thread(new PrintLetter(' ', 'A', 15)).start();

    }


    public static class PrintLetter implements Runnable {

        private static final Object mon = new Object();
        private static volatile char currentLetter = 'A';

        private final char letter;
        private final char nexLetter;
        private final int count;

        public PrintLetter(char letter, char nexLetter, int count) {
            this.letter = letter;
            this.nexLetter = nexLetter;
            this.count = count;
        }

        @Override
        public void run() {

            synchronized (mon) {
                try {
                    for (int i = 0; i < count; i++) {

                        while (currentLetter != letter) {
                            mon.wait();
                        }

                        System.out.print(letter);

                        currentLetter = nexLetter;
                        mon.notifyAll();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
