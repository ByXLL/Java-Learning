package com.transform_demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtToArrayListDemo {
    /**
     * 从文件到集合
     * @param args
     */

    private static String PATH = "FileOutputStreamDemo\\dist\\files\\array2txt.txt";

    public static void main(String[] args) throws IOException {
        ArrayList<String> strArray = new ArrayList<String>();

        BufferedReader brd = new BufferedReader(new FileReader(PATH));

        String line;
        while ((line = brd.readLine()) != null) {
            strArray.add(line);
        }
        System.out.println(strArray);
        brd.close();
    }
}
