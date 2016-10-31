package fj.linyu.lucence;

import java.io.IOException;

/**
 * Created by linyu on 2016/9/29.
 */
public class JvmShutDown {
    public static void main(String[] args) {
        //when jvm shutdown do something
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
               //TODO something must todo
            }
        });
    }
}
