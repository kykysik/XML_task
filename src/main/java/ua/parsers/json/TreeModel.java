package ua.parsers.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class TreeModel {
    public static void main(String[] args) throws IOException {
        System.out.print("readJson: ");
        readJson();
        System.out.println();
        System.out.print("writeJson: ");
        writeJson();
    }

    private static void readJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(new URL("http://test-api.rescuegroups.org/v5/public/swagger.php"), JsonNode.class);
        String message = rootNode.get("message").asText(); // get property message
        JsonNode childNode =  rootNode.get("place"); // get object Place
        String place = childNode.get("name").asText(); // get property name
        System.out.println(message + " " + place); // print "Hi World!"
    }

    private static void writeJson() throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("message", "Hi");
        ObjectNode childNode = rootNode.putObject("place");
        childNode.put("name", "World!");
        mapper.writeValue(outputStream, childNode);

        System.out.println(outputStream.toString()); // print "{"message":"Hi","place":{"name":"World!"}}"
    }
}