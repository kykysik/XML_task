package ua.parsers.xmlParsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    private static List<Element> list = new ArrayList();
    public static void main(String[] args) {

        try {
            File inputFile = new File("src/main/java/ua/parsers/testDoc.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("person");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if(Integer.parseInt(eElement.getElementsByTagName("cash").item(0).getTextContent()) > 10000){
                        list.add(eElement);
                    }
                }
            }
            writeToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile() {
            File file = new File("src/main/java/ua/parsers/DomParserDoc.xml");
            FileWriter fr = null;
            try {
                fr = new FileWriter(file);
                for(int k = 0; k < list.size(); k++) {
                    fr.write("\n" + list.get(k).getElementsByTagName("name").item(0).getTextContent() + "\n");
                    fr.write(list.get(k).getElementsByTagName("address").item(0).getTextContent() + "\n");
                    fr.write(list.get(k).getElementsByTagName("cash").item(0).getTextContent());
                    System.out.println("-----------------------");
                    System.out.println(list.get(k).getElementsByTagName("name").item(0).getTextContent());
                    System.out.println(list.get(k).getElementsByTagName("address").item(0).getTextContent());
                    System.out.println(list.get(k).getElementsByTagName("cash").item(0).getTextContent());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
