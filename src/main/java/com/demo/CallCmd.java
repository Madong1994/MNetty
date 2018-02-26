package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @类名: ${CLASS_NAME}
 * @包名: com.demo
 * @描述: ${TODO}(用一句话描述该文件做什么)
 * @日期: 2018/2/26 9:18
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 */
public class CallCmd {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        printProcess();
        Process p = runtime.exec("taskkill /f /im 1.exe");
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String msg = null;
        while ((msg = reader.readLine()) != null){
            System.out.println(msg);
        }
    }
    //打印所有的进程信息
    public static void printProcess(){
        BufferedReader buff = null;

        Process p = null;

        try {
            //打印所有进程的信息
            p = Runtime.getRuntime().exec("tasklist");
            //用流读出来
            buff = new BufferedReader(new InputStreamReader(p.getInputStream()));

            System.out.println("打印进程系信息");

            String temp = null;
            //遍历
            while ((temp=buff.readLine())!=null) {
                System.out.println(buff.readLine());

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(buff!=null){
                try {
                    buff.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }}
}
