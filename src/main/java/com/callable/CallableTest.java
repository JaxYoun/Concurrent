package com.callable;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class CallableTest {

    static final ExecutorService fixedPool;

    static {
        fixedPool = Executors.newFixedThreadPool(100);
    }

    public static void main(String[] args){
        /*String[] wordArr = {"spark", "hadoop", "oozie", "storm"};
        Map<String, String> resultMap = wordQuery(wordArr);
        for(Map.Entry<String, String> entry : resultMap.entrySet()){
            System.out.println(entry.getKey() + "*********" + entry.getValue());
        }*/
//        pureFuture();
//        futureTask();

        try {
            Thread.sleep(15000);
            System.out.println("进入方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadRuseTest();
    }

    public static void threadRuseTest(){
        try {
            Thread.sleep(15000);
            System.out.println("准备开启");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 200; i++){
            Callable<String> callable = new Callable() {
                public String call() throws Exception {
                    return "*****************************";
                }
            };

            Future<String> future = fixedPool.submit(callable);
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("准备关闭");
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fixedPool.shutdown();
//        fixedPool.awaitTermination();
        System.out.println("再次工作");
        for(int i = 0; i < 100; i++){
            Callable<String> callable = new Callable() {
                public String call() throws Exception {
                    return "*****************************";
                }
            };

            Future<String> future = fixedPool.submit(callable);
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("关闭了");
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> wordQuery(String[] wordArr){
        HashMap<String, String> hashMap = new HashMap<String, String>();

        try {
            for(String it : wordArr){
                if(StringUtils.isNotBlank(it)){
                    MyFutureCallable callable = new MyFutureCallable(it);
                    FutureTask<String> futureTask = new FutureTask<String>(callable);
                    fixedPool.submit(futureTask);
                    try {
                        hashMap.put(it, futureTask.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fixedPool.shutdown();
        }

        return hashMap;
    }

    public static void pureFuture(){
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 15; i++){
            Future<String> future = fixedPool.submit(new MyFutureCallable("u"));

            try {
                map.put(i + "", future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        fixedPool.shutdown();

        for (String iter : map.keySet()){
            System.out.println(iter + "=========" + map.get(iter));
        }
    }

    public static void futureTask(){

        MyFutureCallable callable = new MyFutureCallable("e");

        FutureTask<String> futureTask = new FutureTask<String>(callable);
        fixedPool.submit(futureTask);

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            fixedPool.shutdown();
        }
    }

}

class MyFutureCallable implements Callable<String>{

    private String kay;

    public MyFutureCallable(String key){
        this.kay = key;
    }

    public String call() throws Exception {
        return "{\"name\": \""+ kay +"\"}";
    }

}
