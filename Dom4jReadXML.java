package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
* 
* @author yuzongjian
*/

public class Dom4jReadXML{
	@Test
	public void changedXML() throws Exception{  
		   SAXReader sax=new SAXReader();
		   String path = "C:\\Users\\Administrator\\Desktop\\517.xml";   
		   File xmlFile=new File(path); 
		    Document document=sax.read(xmlFile);
//		    document.elementByID("13");
		    Element root=document.getRootElement();
//		    writeXML(root);
	}
	
	public  void writeXML(Element node){
		String outpath = "C:\\Users\\Administrator\\Desktop\\517.xml"; 
//		PrintWriter out = new PrintWriter(new BufferedWriter(
//                new FileWriter(outpath, false)));
		
		List<Element> listElement=node.elements();
		for(int i = 0 ; i<listElement.size() ; i++){
		List<Element> listElementnex=listElement.get(i).element("classes").elements();
		for(Element e:listElementnex){
			int nodeId = Integer.parseInt(e.attributeValue("id"));
			if(13==nodeId){
//				System.out.println(e.asXML());
				List<Element> listNodes = e.elements();
				for (Element eNode : listNodes){
					if(eNode.attributeValue("path").equals("ROOT/HEAD/REV_CODE")){
						eNode.setAttributeValue("value", "123010033");
						System.out.println(eNode.asXML());
					}
				}
			}
			
		}
		}
	}
	
	
	
}