package com.itchunyang.characterstream;

import java.io.*;

/**
 * Created by luchunyang on 2016/10/28.
 */
public class PipedWriterDemo {

    public static void main(String[] args) throws IOException{
        PipedReader pr = new PipedReader();
        PipedWriter pw = new PipedWriter();
        pw.connect(pr);

        new Producer(pw).start();
        new Consumer(pr).start();
    }

    private static class Producer extends Thread{
        private PipedWriter pw;

        public Producer(PipedWriter pw) {
            this.pw = pw;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    pw.write((i+" hello PipedStream"));
                    System.out.println("生产数据"+i);
                    Thread.sleep(2000);
                }
                pw.write("quit");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Consumer extends Thread{
        private PipedReader pr;

        public Consumer(PipedReader pr) {
            this.pr = pr;
        }

        @Override
        public void run() {
            char[]data = new char[1024];
            while(true){
                try {
                    int len = (char) pr.read(data);
                    String message = new String(data,0,len);
                    if(message.equals("quit")){
                        System.out.println("生产者请求结束");
                        pr.close();
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
