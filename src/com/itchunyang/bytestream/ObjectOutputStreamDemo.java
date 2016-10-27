package com.itchunyang.bytestream;

import com.itchunyang.file.Mac;

import java.io.*;

/**
 * Created by luchunyang on 2016/10/26.
 * 将一个序列化的对象写入到构造ObjectOutputStream时传入的底层字节输出流中
 */
public class ObjectOutputStreamDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File file = new File(Mac.PARENT_DIR,Mac.TEXT_ABC);
        OutputStream os = new FileOutputStream(file,false);
        //带有缓冲区的
        ObjectOutputStream oos = new ObjectOutputStream(os);//首先会写头信息
        oos.writeUTF("UTF字符串");
        oos.write(10);
        oos.writeChar('B');
        oos.writeFloat(11.2f);
        oos.writeObject(new Staff("员工1",22));//要求序列化
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        System.out.println(ois.readUTF());
        System.out.println(ois.read());
        System.out.println(ois.readChar());
        System.out.println(ois.readFloat());
        System.out.println(ois.readObject());
        ois.close();
    }

    private static class Staff implements Serializable{
        String name;
        int age;

        public Staff(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Staff{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
