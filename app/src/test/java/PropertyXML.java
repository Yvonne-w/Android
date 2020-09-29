import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PropertyXML {
    public static List<StringProperty> properties;

    public PropertyXML() {
        properties = new ArrayList<>();
    }

    public List<StringProperty> getProperty(String filePath) throws FileNotFoundException   //get data from txt
    {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] tokens = line.split("\\|");
            int n = tokens.length;
            if (n == 14) {
                StringProperty p = new StringProperty(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8], tokens[9], tokens[10], tokens[11], tokens[12], tokens[13]);
                properties.add(p);
            }
        }

        return properties;
    }

    public void saveData(String filePath) {
        File f = new File(filePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder(); //create a new instance of DocumentBuilder
            Document d = db.newDocument(); //obtain new instance of a DOM document

            //create the structure of my document
            Element rootElement = d.createElement("Property");//<People>
            d.appendChild(rootElement); //append the root to the document

            for (StringProperty sp : properties) {
                Element spElement = d.createElement("StringProperty");
                spElement.setAttribute("id", sp.getId());
                spElement.setAttribute("propertyState", sp.getPropertyState());
                spElement.setAttribute("propertyType", sp.getPropertyType());
                spElement.setAttribute("price", sp.getPrice());
                spElement.setAttribute("suburb", sp.getSuburb());
                spElement.setAttribute("latitude", sp.getLatitude());
                spElement.setAttribute("longitude", sp.getLongitude());
                spElement.setAttribute("address", sp.getAddress());
                spElement.setAttribute("numBedrooms", sp.getNumBedrooms());
                spElement.setAttribute("numBathrooms", sp.getNumBathrooms());
                spElement.setAttribute("numCarspaces", sp.getNumCarspaces());
                spElement.setAttribute("postcode", sp.getPostcode());
                spElement.setAttribute("agent", sp.getAgent());
                spElement.setAttribute("allowPets", sp.getAllowPets());

                rootElement.appendChild(spElement);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //create document
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        PropertyXML pxml = new PropertyXML();
        pxml.getProperty("sample_properties.txt");
//        System.out.println(pxml.properties);
        pxml.saveData("sample_properties.xml");
    }
}