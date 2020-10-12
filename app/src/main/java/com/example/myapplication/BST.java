package com.example.myapplication;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class BST {
    public List<Property> properties;
    public BSTree bsTree;

    public BST() {
        this.bsTree = new BSTree();
    }

    public List<Property> loadData(String filePath) {
        File f = new File(filePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<Property> lp = new ArrayList<Property>();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse(f);
            d.getDocumentElement().normalize();

            NodeList nl = d.getElementsByTagName("Property");

            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;

                    String id = element.getAttribute("id");
                    String propertyState = element.getElementsByTagName("propertyState").item(0).getTextContent();
                    String propertyType = element.getElementsByTagName("propertyType").item(0).getTextContent();
                    String price = element.getElementsByTagName("price").item(0).getTextContent();
                    String suburb = element.getElementsByTagName("suburb").item(0).getTextContent();
                    String latitude = element.getElementsByTagName("latitude").item(0).getTextContent();
                    String longitude = element.getElementsByTagName("longitude").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    String numBedrooms = element.getElementsByTagName("numBedrooms").item(0).getTextContent();
                    String numBathrooms = element.getElementsByTagName("numBathrooms").item(0).getTextContent();
                    String numCarspaces = element.getElementsByTagName("numCarspaces").item(0).getTextContent();
                    String postcode = element.getElementsByTagName("postcode").item(0).getTextContent();
                    String agent = element.getElementsByTagName("agent").item(0).getTextContent();
                    String allowPets = element.getElementsByTagName("allowPets").item(0).getTextContent();
                    Property p = new Property(id, propertyState, propertyType, price, suburb, latitude, longitude, address, numBedrooms, numBathrooms, numCarspaces, postcode, agent, allowPets);

                    lp.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lp;
    }

}
