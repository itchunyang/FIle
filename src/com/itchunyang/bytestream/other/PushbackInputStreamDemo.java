package com.itchunyang.bytestream.other;

import java.io.*;

import static com.itchunyang.file.Mac.PARENT_DIR;

/**
 * Created by luchunyang on 2016/10/27.
 */
public class PushbackInputStreamDemo {

    public static void main(String[] args) throws IOException{
        InputStream is = new FileInputStream(new File(PARENT_DIR,"json.txt"));

        //如果不指定size  默认为1 只能回推一个字节，多了会报错！
        PushbackInputStream pis = new PushbackInputStream(is,64);
        byte[] buffer = new byte[20];

        int len = pis.read(buffer);
        System.out.println("len="+len);
        System.out.print(new String(buffer,0,len));

        //把buffer的内容回推进PushbackInputStream内部的缓存数据，且更新内部读取的pos
        pis.unread(buffer,0,1);

        //pis.read会判断pos是否小于数组长度，如小于，则读取缓存数组内的内容来达到回推得目的
        len = pis.read(buffer);
        System.out.print(new String(buffer,0,len));
    }
}
