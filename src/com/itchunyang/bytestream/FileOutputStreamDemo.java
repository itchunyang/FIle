package com.itchunyang.bytestream;

import com.itchunyang.file.Mac;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by luchunyang on 2016/10/26.
 */
public class FileOutputStreamDemo {

    public static void main(String[] args) throws IOException {
        String message = "Android是一种基于Linux的自由及开放源代码的操作系统";
        byte[] data = message.getBytes();

        File file = new File(Mac.PARENT_DIR,Mac.TEXT_ABC);
        FileOutputStream fos = new FileOutputStream(file,false);
        fos.write(data);
        fos.close();

        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[100];
        int length = fis.read(buffer,0,buffer.length);
        System.out.println("read len = "+ length);
        fis.close();
    }
}
