package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.hyperic.sigar.Sigar;

public class GetProcessPath {
	public static void main (String args[]){
    //tasklist����ȡ���н���
    String[] cmd = {"tasklist"};
    try {
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String string_temp = "";
        while ((string_temp = in.readLine()) != null) {
            //�ڿ���̨�д�ӡ������Ϣ
            System.out.println(string_temp);
            if(string_temp.indexOf("firefox") != -1) {
                //��ȡ����PID
                String pid = string_temp.substring(29, string_temp.indexOf("Console")).trim();               
                Sigar sigar = new Sigar();
                //��ȡ����·��
                String processPath = sigar.getProcExe(pid).getName();
                System.out.println("----����·��:" + processPath);
                break;
            }
        }
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
	}
}
