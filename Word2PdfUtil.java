package com.test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.aspectj.weaver.ast.Test;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;


/**
 * @author joki
 */
public class Word2PdfUtil {

    public static void main(String[] args) {
        //doc2pdf("C:/Users/lss/Desktop/test.doc");
    }

    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml"); // license.xmlӦ����..\WebRoot\WEB-INF\classes·����
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void doc2pdf(String inPath, String outPath) {
        if (!getLicense()) { // ��֤License ������֤��ת������pdf�ĵ�����ˮӡ����
            return;
        }
        try {
            long old = System.currentTimeMillis();
            File file = new File(outPath); // �½�һ���հ�pdf�ĵ�
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address�ǽ�Ҫ��ת����word�ĵ�
            doc.save(os, SaveFormat.PDF);// ȫ��֧��DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
                                         // EPUB, XPS, SWF �໥ת��
            long now = System.currentTimeMillis();
            System.out.println("����ʱ��" + ((now - old) / 1000.0) + "��"); // ת����ʱ
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
