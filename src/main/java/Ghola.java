

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class Ghola {

  private static boolean resultEmpty;

    public static void runner(Document docx) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // включаем поддержку пространства имен XML
        builderFactory.setNamespaceAware(true);
        DocumentBuilder builder;
       // Document doc = null;
        try {
            builder = builderFactory.newDocumentBuilder();
            File files = new File("C:\\Users\\User\\Documents\\Учёба IBS\\HW_API\\outfilename.xml");
           // doc = builder.parse(files);

            // Создаем объект XPathFactory
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Получаем экзмепляр XPath для создания
            // XPathExpression выражений
            XPath xpath = xpathFactory.newXPath();
            String inputWord = getInputWord(docx, xpath);
            if(resultEmpty){
                System.out.println("всё верно");
            }else {

                System.out.println("Ввели слово: " + inputWord);

                List<String> variant = getVariant(docx, xpath);
                System.out.println("Варианты: "
                        + variant.toString());


            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }




    private static List<String> getVariant(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            // получаем список всех узлов, которые отвечают условию
            XPathExpression xPathExpression = xpath.compile(
                    "/SpellResult/error/s/text()"
            );
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++)
                list.add(nodeList.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }


    private static String getInputWord(Document doc, XPath xpath) {
        String inputWord = null;
        try {
            XPathExpression xPathExpression = xpath.compile(
                    "/SpellResult/error/word/text()"
            );
            inputWord = (String) xPathExpression.evaluate(doc, XPathConstants.STRING);
            if(inputWord.isEmpty()){
                resultEmpty= true;
            }else{resultEmpty = false;}
            System.out.println(inputWord.isEmpty());
            System.out.println("*"+inputWord+"*");
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return inputWord;
    }

}