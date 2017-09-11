package com.threadpool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TreadPoolTest {

    public static void main(String[] args){
//        test();
        Logic();
    }

    public static void test(){

        ExecutorService pool = Executors.newFixedThreadPool(100);
        long start = System.currentTimeMillis();

        for(int i = 0; i < 1000; i++){
            Runnable runnable = new Runnable() {
                public void run() {
                    System.out.println("kkkkkk");
                }
            };
            pool.execute(runnable);
        }
        long end = System.currentTimeMillis();
        pool.shutdown();
        System.err.println(end - start);
    }

    public static void Logic() {

        ExecutorService pool = Executors.newFixedThreadPool(100);

        Map<String, String> resultMap = new HashMap<String, String>();
        String[] wordArr = {"大海", "山峰", "浴场", "板凳", "红楼"};

        for (String it : wordArr){
            Worker worker = new Worker(it, resultMap);
            pool.execute(worker);
        }
        pool.shutdown();

        System.out.print(resultMap.size());
        for (String kay : resultMap.keySet()) {
            System.out.println(resultMap.get(kay));
        }

        /*for(Map.Entry<String, String> entry : resultMap.entrySet()){
            System.out.println(entry.getKey() + entry.getValue());
        }*/

    }

}

class Worker implements Runnable{

    String key;
    Map<String, String> resultMap;

    public Worker(String kay, Map<String, String> map) {
        this.key = kay;
        this.resultMap = map;
    }

    public void run() {
        resultMap.put(key, key + "==========");
    }
}
