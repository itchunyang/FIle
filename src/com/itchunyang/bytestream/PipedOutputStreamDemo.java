package com.itchunyang.bytestream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by luchunyang on 2016/10/26.
 * 管道流，用于线程间的通信。一个线程的PipedInputStream对象从另外一个线程的PipedOutputStream对象读取输入。
 * 要使管道流有用，必须同时构造管道输入流和管道输出流。
 */
public class PipedOutputStreamDemo {

    public static void main(String[] args) throws IOException {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();
        pos.connect(pis);
        new Producer(pos).start();
        new Consumer(pis).start();

    }

    private static class Producer extends Thread{
        private PipedOutputStream pos;

        public Producer(PipedOutputStream pos) {
            this.pos = pos;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    pos.write((i+" hello PipedStream").getBytes());
                    System.out.println("生产数据"+i);
                    Thread.sleep(2000);
                }
                pos.write("quit".getBytes());
                pos.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Consumer extends Thread{
        private PipedInputStream pis;

        public Consumer(PipedInputStream pis) {
            this.pis = pis;
        }

        @Override
        public void run() {
            byte[]data = new byte[1024];
            while(true){
                try {
                    int len = pis.read(data);
                    String message = new String(data,0,len);
                    if(message.equals("quit")){
                        System.out.println("生产者请求结束");
                        pis.close();
                        break;
                    }

                    System.out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
