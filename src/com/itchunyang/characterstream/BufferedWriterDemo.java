package com.itchunyang.characterstream;

import com.itchunyang.file.Mac;

import java.io.*;

/**
 * Created by luchunyang on 2016/10/28.
 */
public class BufferedWriterDemo {

    public static void main(String[] args) throws IOException{

        char[] buffer = new char[1024];
        Reader reader = new FileReader(new File(Mac.PARENT_DIR,"庆余年.txt"));
        BufferedReader br = new BufferedReader(reader);

        Writer writer = new FileWriter(new File(Mac.PARENT_DIR,"庆余年_copy.txt"));
        BufferedWriter bw = new BufferedWriter(writer);

        int len ;
        while((len = br.read(buffer)) != -1){
            writer.write(buffer,0,len);
        }

        bw.newLine();
        br.close();
        bw.close();
    }
}
