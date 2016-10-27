package com.itchunyang.bytestream;

import com.itchunyang.file.Mac;

import java.io.*;

/**
 * Created by luchunyang on 2016/10/27.
 *
 * 1.BufferedOutputStream
 * 2.DataOutputStream
 * 3.PrintStream
 *
 *  ps
 *  1.ObjectOutputStream与DataOutputStream有什么区别?
 *      DataOutputStream和ObjectOutputStream(内部就是DataOutputStream)在处理基本类型的时候没有什么很大的区别，
 *      主要区别是：ObjectOutputStream可以将一个实现了序列化的类实例写入到输出流中，
 *      ObjectInputStream可以从输入流中将ObjectOutputStream输出的类实例读入到一个实例中。
 *      DataOutputStream只能处理基本类型
 *
 *  2.PrintStream和DataOutputStream的区别?
 *      DataOutputStream的作用是装饰其它的输出流，它和DataInputStream配合使用：允许应用程序以与机器无关的方式从底层输入流中读写java数据类型。
 *      而PrintStream的作用虽然也是装饰其他输出流，但是它的目的不是以与机器无关的方式从底层读写java数据类型；而是为其它输出流提供打印各种数据值表示形式，使其它输出流能方便的通过print(), println()或printf()等输出各种格式的数据。
 *
 *
 */

public class FilterOutputStreamDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
//        testBufferedOutputStream();
//        testDataOutputStream();
//        testPrintStream();
    }

    /**
     * PrintStream 是用来装饰其它输出流。它能为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式。
     */
    private static void testPrintStream() throws IOException{
        File file = new File(Mac.PARENT_DIR,Mac.TEXT_ABC);
        //所谓“自动flush”，就是每次执行print(), println(), write()函数，都会调用flush()函数；
        //PrintStream ps = new PrintStream(new FileOutputStream(file),false);
        PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)),false);
        ps.print(true);
        ps.print('A');
        ps.print(99.9f);
        ps.println(false);
        ps.format("%d,%s",100,"格式化文本");

        //疑问 为什么autoFlush设置为false，仍然可以写进去？
        //因为FileOutputStream 没有缓存的功能，如果换成BufferedOutputStream就会发现，不能立刻刷新进去了
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ps.close();
    }

    private static void testDataOutputStream() throws IOException{
        File file = new File(Mac.PARENT_DIR,Mac.TEXT_ABC);

        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file,false));
        //将指定字节（参数 b 的八个低位）写入基础输出流。
        dos.write(18496);//低八位就是64
        dos.writeBoolean(false);
        dos.writeUTF("data output stream test");
        dos.close();
        //System.out.println("size="+dos.size());//到目前为止写入此数据输出流的字节数

        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        System.out.println(dis.read());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readUTF());
        dis.close();

    }

    private static void testBufferedOutputStream() throws IOException, InterruptedException {
        File file = new File(Mac.PARENT_DIR,Mac.TEXT_ABC);
        FileOutputStream fos = new FileOutputStream(file,false);
        //当向缓冲流写入数据时候，数据先写到缓冲区，待缓冲区写满后，系统一次性将数据发送给输出设备 size是缓冲区大小
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(99);
        bos.write("hello world".getBytes());

        //bos.flush();
        //Thread.sleep(6000);
        bos.close();

        //第一次read时，会一下读取x个字节放入缓存区，然后后续的read都会从缓存中读取，当read到缓存区末尾时，会再次读取x个字节放入缓存区
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        int ret = bis.read();
        byte[] b = new byte[100];

        int len = bis.read(b);

        System.out.println(ret);
        System.out.println(new String(b,0,len));
    }
}
