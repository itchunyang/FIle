package com.itchunyang.characterstream;

import com.itchunyang.file.Mac;

import java.io.*;

/**
 * Created by luchunyang on 2016/10/27.
 */
public class OutputStreamWriterDemo {

    public static void main(String[] args) throws IOException{
        File file = new File(Mac.PARENT_DIR,Mac.TEXT_ABC);

        OutputStream os = new FileOutputStream(file,false);
        //将字节流转换为字符流,可使用指定的 charset 将要写入流中的字符编码成字节.
        //如果不指定字符集编码，该解码过程将使用平台默认的字符编码
        OutputStreamWriter osw = new OutputStreamWriter(os,"utf-8");
        char[] characters = {'B','C','D','E','F'};
        osw.write('A');//将单个字符写入。
        osw.write(characters,0,4);
        osw.write("android");//将字符串某部分写入
        osw.close();

        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is,"utf-8");
        int read;
        while((read = isr.read()) != -1){
            System.out.print((char)read);
        }


        /*****************************************************/

        FileWriter fw = new FileWriter(file,true);
        fw.write("FileWriter");
        fw.close();

    }
}
