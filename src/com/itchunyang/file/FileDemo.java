package com.itchunyang.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import static com.itchunyang.file.Mac.PARENT_DIR;

/**
 * Created by luchunyang on 2016/10/26.
 */
public class FileDemo {

    public static final String JSON = "json.txt";

    //快捷键psvm
    public static void main(String[] args) throws IOException {
        File json = new File(PARENT_DIR,JSON);

        System.out.println("getAbsolutePath="+json.getAbsolutePath());
        System.out.println("getName="+json.getName());
        System.out.println("getParent="+json.getParent());
        System.out.println("length="+json.length());
        System.out.println("lastModified="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(json.lastModified()));
        System.out.println("compareTo="+json.compareTo(new File(PARENT_DIR,JSON)));//文件名的字符串比对
        //System.out.println(json.renameTo(new File(PARENT_DIR,"copy_json.txt")));//相当于移动并且重命名

        /**
         * 想要建立一个档案暂时使用，但是你不在乎其精确的档案名，只要不覆盖到已存在的档案时.
         * 临时文件如果不手动删除，也是会一直存在的
         * prefix文件名前面的部分
         * suffix文件名后面的部分，如果传null，则默认.tmp
         * directory所在目录
         */
        File temp = File.createTempFile("itchunyang",null,new File(PARENT_DIR));

        // 在虚拟机终止时，请求删除路径名表示的文件或目录。
        temp.deleteOnExit();
        System.out.println("temp这时候文件还存在！程序完全结束后，会自动删除");


        System.out.println("\n------------------- list -------------------");

        File dir = new File(PARENT_DIR);
        //String[] childs = dir.list();
        //File[] childFiles = dir.listFiles();

        String[] names = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                System.out.println(dir.getAbsolutePath()+" ----> "+name);
                if(name.endsWith(".jpg") || name.endsWith(".JPG"))
                    return true;
                return false;
            }
        });

        System.out.println("过滤jpg文件:"+Arrays.toString(names));

        System.out.println("\n------------------- Random -------------------");

        //RandomAccessFile的绝大多数功能，但不是全部，已经被JDK 1.4的nio的"内存映射文件(memory-mapped files)"给取代了
        RandomAccessFile raf = new RandomAccessFile("random.txt","rw");
        System.out.println("getFilePointer="+raf.getFilePointer());//文件指针0
        raf.writeChar('A');
        raf.write('B');
        System.out.println("getFilePointer="+raf.getFilePointer());
        raf.seek(2);
        System.out.println("getFilePointer="+raf.getFilePointer());
        raf.writeUTF("hello android");//覆盖了'B'
        raf.seek(0);
        raf.close();
    }

}
