package com.thread.Concurrency;

class ConcurrencyTest {
    private static final long   COUNT=1001;

    public static void main(String[] args) {
        try {
            concurrency();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serial();
    }

    private static void concurrency() throws InterruptedException {
//        返回运行时间
        long start=System.currentTimeMillis();
        Thread thread=new Thread(()->{
           int a=0;
            for (long i = 0; i < COUNT; i++) {
                a+=5;
            }
        });
        thread.start();
        int b=0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }
        thread.join();
        long end=System.currentTimeMillis()-start;
        System.out.println("time="+end);
    }

    private static void serial(){
        long start=System.currentTimeMillis();
        int a=0;
        for (long i = 0; i < COUNT; i++) {
            a+=5;
        }
        int b=0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }

        long end=System.currentTimeMillis()-start;
        System.out.println("time="+end);
    }
}
