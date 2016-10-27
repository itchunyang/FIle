package com.itchunyang.bytestream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by luchunyang on 2016/10/26.
 * 内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中
 */
public class ByteArrayOutputStreamDemo {
    public static void main(String[] args) throws IOException {

        //初始数组容量
        ByteArrayOutputStream bos = new ByteArrayOutputStream(10);

        /**
         * 每次写的时候都会检查容量，不足则扩容。
         * PS:
         * private void grow(int minCapacity) 源码
         *
         * if (newCapacity - minCapacity < 0)
         *      newCapacity = minCapacity;
         *
         * 按理来说newCapacity是原来数组长度的2倍，而minCapacity是count + 1，newCapacity应该永远大于minCapacity的，为什么还要判断？
         * 因为ByteArrayOutputStream可以指定数组的长度，如果为0，这时newCapacity 值为0，minCapacity值为1，这个判断就起作用了！
         * 同样ArrayList的grow方法也一样的道理
         *
         */
        bos.write(1);
        bos.write(new byte[]{2,3,4,5,6,7,8,9,10,11,12});
        //bos.reset(); count字段重置为零,如果reset后，bos.toByteArray()为空，看源码
        byte[] buffer = bos.toByteArray();
        System.out.println(Arrays.toString(buffer));

        ByteArrayInputStream bis = new ByteArrayInputStream(buffer,1,10);
        int read;
        while((read = bis.read()) != -1){
            System.out.print(read+" ");
        }
        System.out.println("");

    }
}
