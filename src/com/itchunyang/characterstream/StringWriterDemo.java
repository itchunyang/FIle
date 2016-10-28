package com.itchunyang.characterstream;

import java.io.StringWriter;

/**
 * Created by luchunyang on 2016/10/28.
 */
public class StringWriterDemo {

    public static void main(String[] args) {
        //没什么大用 就是StringBuffer
        StringWriter sw = new StringWriter(5);
        sw.write("hello");
        sw.append('a').append('b');
        System.out.println(sw.toString());

    }
}
