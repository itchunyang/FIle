package com.itchunyang.characterstream;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by luchunyang on 2016/10/28.
 */
public class CharArrayWriterDemo {

    public static void main(String[] args) throws IOException{
        CharArrayWriter caw = new CharArrayWriter(3);
        caw.write("abc");
        caw.write('d');
        caw.write('e');
        caw.close();

        char[] buffer = caw.toCharArray();
        System.out.println(Arrays.toString(caw.toCharArray()));

        CharArrayReader car = new CharArrayReader(buffer,1,3);
        System.out.println((char)car.read());
        System.out.println((char)car.read());
        System.out.println((char)car.read());
    }
}
