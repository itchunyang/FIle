package com.itchunyang.characterstream.other;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * Created by luchunyang on 2016/10/28.
 */
public class PushbackReaderDemo {

    public static void main(String[] args) throws IOException{
        char[] buffer = {'a','b','c','d','e'};
        char[] data = new char[2];
        PushbackReader pr = new PushbackReader(new CharArrayReader(buffer),2);//可以回推2个字节
        pr.read(data);
        System.out.println(new String(data));

        pr.unread(data);
        System.out.println((char)pr.read());


    }
}
