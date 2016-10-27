package com.itchunyang.bytestream.other;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;

/**
 * Created by luchunyang on 2016/10/27.
 */
public class SequenceInputStreamDemo {

    public static void main(String[] args) throws IOException{

        byte[] src1 = {0x01,0x02,0x03,0x04};
        byte[] src2 = {0x05,0x06,0x07,0x08};
        InputStream is1 = new ByteArrayInputStream(src1);
        InputStream is2 = new ByteArrayInputStream(src2);

        //合并流
        SequenceInputStream sis = new SequenceInputStream(is1,is2);
        int len;
        while((len = sis.read()) != -1){
            System.out.print(len+" ");
        }
    }
}
