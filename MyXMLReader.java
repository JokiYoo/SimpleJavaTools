package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.print.Doc;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;



public class MyXMLReader {
	List<String> listChangeNodes = new ArrayList<String>();
    /**
 * 
 * @author yuzongjian
     * @throws DocumentException 
 */
@Test
	public  void testCopy() throws DocumentException{
	String changeLine = null;
	  SAXReader sax=new SAXReader();
	  String path = "C:\\Users\\Administrator\\Desktop\\517.xml";   
	  File xmlFile=new File(path); 
	  Document document=sax.read(xmlFile);
	  document.elementByID("13");
	  Element root=document.getRootElement();
	    String code = "value=\"123010034\"";
	    String newCode = "value=\"123010033\"";
	    String Changedcode = "<node operation=\"addOrUpdate\" value=\"123010033\" path=\"ROOT/HEAD/REV_CODE\" option=\"\" />";
		String inpath = "C:\\Users\\Administrator\\Desktop\\517.xml";
		String outpath = "C:\\Users\\Administrator\\Desktop\\500017.xml";
        try {
            BufferedReader br = new BufferedReader(new FileReader(inpath));

            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter(outpath, false)));

            String line = null;

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
               if(line.indexOf(code)==-1){
                out.println(line); //–¥»Î“ª––
                System.out.println(line);
               }
               else{
            	   changeLine = line.replace(code, newCode);
            	   out.println(changeLine);
            	   System.out.println(changeLine);
               }
            }
            if (br != null) {
                br.close();
            }
            if (out != null) {
                out.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
