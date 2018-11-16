package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.hyperic.sigar.Sigar;

public class GetProcessPath {
	public static void main (String args[]){
    //tasklist：获取所有进程
    String[] cmd = {"tasklist"};
    try {
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String string_temp = "";
        while ((string_temp = in.readLine()) != null) {
            //在控制台中打印进程信息
            System.out.println(string_temp);
            if(string_temp.indexOf("firefox") != -1) {
                //截取进程PID
                String pid = string_temp.substring(29, string_temp.indexOf("Console")).trim();               
                Sigar sigar = new Sigar();
                //获取进程路径
                String processPath = sigar.getProcExe(pid).getName();
                System.out.println("----进程路径:" + processPath);
                break;
            }
        }
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
	}
}
