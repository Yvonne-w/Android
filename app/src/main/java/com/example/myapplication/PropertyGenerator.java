package com.example.myapplication;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * This class is used to generate 1000 property instances. We run this class in IntelliJ to get the XML (sample_properties.xml). To run it in the Android, it might have errors.
 */

public class PropertyGenerator {
    private Set<Property> properties;

    public PropertyGenerator() {
        this.properties = new HashSet<>();
    }

    public PropertyGenerator(Set<Property> properties) {
        this.properties = properties;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public void setProperties(Set<Property> properties) {
        this.properties = properties;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String toString() {
        return this.properties.stream().map(property -> "•" + property.description() + ";\n").collect(Collectors.joining());
    }

    public PropertyGenerator createProperties() {
        PropertyGenerator propertyGenerator;
        Set<Property> properties = new HashSet<>();
        int numProperties = 1000;
        double base_latitude = -35.282001;
        double base_longtitude = 149.128998;
        Random random = new Random();
        for (int i = 0; i < numProperties; i++) {
            State ps = randomEnum(State.class);
            Type pt = randomEnum(Type.class);
            double price = 0;
            if (ps == State.auction || ps == State.sale) {
                price = 100000 + 900000 * random.nextDouble();
            } else {
                price = 100 + 600 * random.nextDouble();
            }
            Suburb suburb = randomEnum(Suburb.class);
            double latitude = base_latitude + 0.1 * (random.nextDouble() - 0.5);
            double longtitude = base_longtitude + 0.1 * (random.nextDouble() - 0.5);
            String address = "TBD";
            int numBedrooms = 1 + random.nextInt(5);
            int numBathrooms = 1 + random.nextInt(2);
            int numCarspaces = random.nextInt(2);
            int postcode = 2600 + random.nextInt(30);
            Agent agent = randomEnum(Agent.class);
            boolean allowPets = random.nextBoolean();

            Property p = new Property(ps, pt, price, suburb, latitude, longtitude, address, numBedrooms, numBathrooms, numCarspaces, postcode, agent, allowPets);
            p.id = 1001 + i;
            properties.add(p);
        }
        propertyGenerator = new PropertyGenerator(properties);
        System.out.println("Number of properties generated: " + properties.size());
//        File file = new File("sample_properties.txt");
//        file.delete();
//        propertyGenerator.saveToJSONFile(new File("sample_properties.json"));
//        propertyGenerator.saveToTxtFile(file);
        propertyGenerator.saveToXML("sample_properties.xml");

        return propertyGenerator;
    }



    // helper to select randomly from an enum list
    public static <T extends Enum<?>> T randomEnum(Class<T> tClass) {
        Random random = new Random();
        int randomNo = random.nextInt(tClass.getEnumConstants().length);
        return tClass.getEnumConstants()[randomNo];
    }

    public void saveToXML(String filePath) {
        File f = new File(filePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.newDocument();

            Element rootElement = d.createElement("Properties");
            d.appendChild(rootElement);

            for (Property p: properties) {
                Element spElement = d.createElement("Property");
                spElement.setAttribute("id", String.valueOf(p.getId()));
                Element pStateElement = d.createElement("propertyState");
                pStateElement.appendChild(d.createTextNode(String.valueOf(p.getState())));
                spElement.appendChild(pStateElement);
                Element pTypeElement = d.createElement("propertyType");
                pTypeElement.appendChild(d.createTextNode(String.valueOf(p.getType())));
                spElement.appendChild(pTypeElement);
                Element priceElement = d.createElement("price");
                priceElement.appendChild(d.createTextNode(String.valueOf(p.getPrice())));
                spElement.appendChild(priceElement);
                Element surburbElement = d.createElement("suburb");
                surburbElement.appendChild(d.createTextNode(String.valueOf(p.getSuburb())));
                spElement.appendChild(surburbElement);
                Element latElement = d.createElement("latitude");
                latElement.appendChild(d.createTextNode(String.valueOf(p.getLatitude())));
                spElement.appendChild(latElement);
                Element longElement = d.createElement("longitude");
                longElement.appendChild(d.createTextNode(String.valueOf(p.getLongitude())));
                spElement.appendChild(longElement);
                Element addressElement = d.createElement("address");
                addressElement.appendChild(d.createTextNode(String.valueOf(p.getAddress())));
                spElement.appendChild(addressElement);
                Element bedroomElement = d.createElement("numBedrooms");
                bedroomElement.appendChild(d.createTextNode(String.valueOf(p.getNumBedrooms())));
                spElement.appendChild(bedroomElement);
                Element bathroomElement = d.createElement("numBathrooms");
                bathroomElement.appendChild(d.createTextNode(String.valueOf(p.getNumBathrooms())));
                spElement.appendChild(bathroomElement);
                Element carElement = d.createElement("numCarspaces");
                carElement.appendChild(d.createTextNode(String.valueOf(p.getNumCarspaces())));
                spElement.appendChild(carElement);
                Element postElement = d.createElement("postcode");
                postElement.appendChild(d.createTextNode(String.valueOf(p.getPostcode())));
                spElement.appendChild(postElement);
                Element agentElement = d.createElement("agent");
                agentElement.appendChild(d.createTextNode(String.valueOf(p.getAgent())));
                spElement.appendChild(agentElement);
                Element petElement = d.createElement("allowPets");
                petElement.appendChild(d.createTextNode(String.valueOf(p.getAllowPets())));
                spElement.appendChild(petElement);

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




    public static void main(String[] args) {
        PropertyGenerator pg = new PropertyGenerator().createProperties();
    }
}

