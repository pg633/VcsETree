//package com.pg.vst.web.test;
//
//import org.junit.Test;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author lianzheng04
// * @version 1.0
// * @date 2020/9/10 4:31 下午
// */
//class WorkerCount extends Thread {
//    private String name;
//    private long time;
//    private CountDownLatch countDownLatch;
//
//    public WorkerCount(String name, long time, CountDownLatch countDownLatch) {
//        this.name = name;
//        this.time = time;
//        this.countDownLatch = countDownLatch;
//    }
//
//    @Override
//    public void run() {
//        try {
//            System.out.println(name + "开始工作");
//            Thread.sleep(time);
//            System.out.println(name + "工作完成, 耗时："+ time);
//            countDownLatch.countDown();
//            System.out.println("countDownLatch.getCount():" + countDownLatch.getCount());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//
// class WorkerCount2 extends Thread {
//    private String name;
//    private long time;
//    private CountDownLatch countDownLatch;
//
//    public WorkerCount2(String name, long time, CountDownLatch countDownLatch) {
//        this.name = name;
//        this.time = time;
//        this.countDownLatch = countDownLatch;
//    }
//
//    @Override
//    public void run() {
//        try {
//            System.out.println(name + "开始阶段1工作");
//            Thread.sleep(time);
//            System.out.println(name + "阶段1完成, 耗时："+ time);
//            countDownLatch.countDown();
//
//            System.out.println(name + "开始阶段2工作");
//            Thread.sleep(time);
//            System.out.println(name + "阶段2完成, 耗时："+ time);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//public class Bas {
//    private static final int RUNNER_COUNT = 10;
//
//    public static void main(String[] args) throws InterruptedException {
//        final CountDownLatch begin = new CountDownLatch(1);
//        final CountDownLatch end = new CountDownLatch(RUNNER_COUNT);
//        final ExecutorService exec = Executors.newFixedThreadPool(10);
//
//        for (int i = 0; i < RUNNER_COUNT; i++) {
//            final int NO = i + 1;
//            Runnable run = new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        begin.await();
//                        Thread.sleep((long) (Math.random() * 100));
//                        System.out.println("No." + NO + " arrived");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } finally {
//                        end.countDown();
//                    }
//                }
//            };
//            exec.submit(run);
//        }
//
//        System.out.println("Game Start ...");
//        begin.countDown();
//        end.await();
////        end.await(30, TimeUnit.SECONDS);
//        System.out.println("Game Over.");
//
//        exec.shutdown();
//
//
//    }
//
//
//
//    @Test
//    public void CountDownLatchTest() throws InterruptedException {
//        int COUNT = 2;
//        final CountDownLatch countDownLatch = new CountDownLatch(COUNT);
//        WorkerCount worker0 = new WorkerCount("lilei-0", (long)(Math.random() * 1000), countDownLatch);
//        WorkerCount worker1 = new WorkerCount("lilei-1", (long)(Math.random() * 1000), countDownLatch);
//        worker0.start();
//        worker1.start();
//        countDownLatch.await();
//        System.out.println("准备工作就绪");
//
//        WorkerCount worker2 = new WorkerCount("lilei-2", (long)(Math.random() * 1000), countDownLatch);
//        worker2.start();
//        Thread.sleep(10000);
//    }
//
//
//    @Test
//    public void CountDownLatchTest2() throws InterruptedException {
//        int COUNT = 2;
//        final CountDownLatch countDownLatch = new CountDownLatch(COUNT);
//        WorkerCount2 worker0 = new WorkerCount2("lilei-0", (long)(Math.random() * 1000), countDownLatch);
//        WorkerCount2 worker1 = new WorkerCount2("lilei-1", (long)(Math.random() * 1000), countDownLatch);
//        worker0.start();
//        worker1.start();
//        countDownLatch.await();
//        System.out.println("准备工作就绪");
//
//        WorkerCount2 worker2 = new WorkerCount2("lilei-2", (long)(Math.random() * 1000), countDownLatch);
//        worker2.start();
//        Thread.sleep(1000);
//    }
//
//}
//
////
////lilei-0开始阶段1工作
////        lilei-1开始阶段1工作
////        lilei-0阶段1完成, 耗时：621
////        lilei-0开始阶段2工作
////        lilei-1阶段1完成, 耗时：808
////        lilei-1开始阶段2工作
////        准备工作就绪
////        lilei-2开始阶段1工作
////        lilei-0阶段2完成, 耗时：621
////        lilei-2阶段1完成, 耗时：724
////        lilei-2开始阶段2工作
////        lilei-1阶段2完成, 耗时：808
//
