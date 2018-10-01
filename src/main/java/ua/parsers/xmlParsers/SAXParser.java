package ua.parsers.xmlParsers;

import entity.Person;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXParser {

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/main/java/ua/parsers/testDoc.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
            writeToFile(userhandler.list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(List<Person> list) {
        File file = new File("src/main/java/ua/parsers/SAXParserDoc.xml");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for(int i = 0; i < list.size(); i++) {
                if(Integer.parseInt(list.get(i).getCash()) > 10000) {
                    fr.write("\n" + list.get(i).toString());
                    System.out.println(list.get(i).toString());
                }

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

class UserHandler extends DefaultHandler {
    public List<Person> list = new ArrayList<>();
    private Person person;

    boolean name = false;
    boolean address = false;
    boolean cash = false;

    @Override
    public void startElement(
            String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("name")) {
            name = true;
        } else if (qName.equalsIgnoreCase("address")) {
            address = true;
        } else if (qName.equalsIgnoreCase("cash")) {
            cash = true;
        }
    }
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (name) {
            person = new Person();
            person.setName(new String(ch, start, length));
            System.out.println(person.getName());
            name = false;
        }
        if (address) {
            person.setAddress(new String(ch, start, length));
            System.out.println(person.getAddress());
            address = false;
        }

        if (cash) {
            person.setCash(new String(ch, start, length));
            System.out.println(person.getCash());
            list.add(person);
            cash = false;
        }
    }
}
