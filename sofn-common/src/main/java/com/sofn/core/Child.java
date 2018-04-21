package com.sofn.core;

import java.io.*;

/**
 * @author sofn
 * @version 2017年03月14日 下午 4:09
 */
public class Child implements Runnable{
    final static Child child = new Child();//保证锁唯一

    @Override
    public void run(){
        File file = new File("F://Test//test.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        File file1 = new File("F://Test//test.txt");
        try {
            fis = new FileInputStream(file1);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
//            for(int i=0;i<10000;i++){
//                sb.append("关于个人自身修养的提升！"+"\r\n");
//            }
            String temp;
            int line = 1;
            while((temp = br.readLine())!=null){
                sb.append(line).append(temp).append("\r\n");
                line++;
            }
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(sb.toString().toCharArray());
            pw.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            pw.close();
        }
    }

    public static void main(String[] args){
        for(int i=0;i<50;i++){
            new Thread(child).start();
        }
    }
}
