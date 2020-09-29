import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class BST {
    public List<StringProperty> properties;
    public BSTree bsTree;

    public BST() {
        this.bsTree = new BSTree();
    }


    public static void main(String[] args) {
        BST bst = new BST();
        List<StringProperty> properties = new ArrayList<StringProperty>();
        properties = bst.loadData("sample_properties.xml");  //load data from xml
        for (int i = 0; i < properties.size(); i++) {  //build BSTree to store data
            bst.bsTree.add(properties.get(i));
        }

//        String check = bst.bsTree.toString();
//        String[] checks = check.split("\\{" );
//        for(String s:checks){
//            System.out.println(s);
//        }

//        bst.bsTree.traverse();
    }

    public List<StringProperty> loadData(String filePath) {
        File f = new File(filePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<StringProperty> lp = new ArrayList<StringProperty>();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse(f);
            d.getDocumentElement().normalize();

            NodeList nl = d.getElementsByTagName("StringProperty");

            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) n;

                    String id = element.getAttribute("id");
                    String propertyState = element.getAttribute("propertyState");
                    String propertyType = element.getAttribute("propertyType");
                    String price = element.getAttribute("price");
                    String suburb = element.getAttribute("suburb");
                    String latitude = element.getAttribute("latitude");
                    String longitude = element.getAttribute("longitude");
                    String address = element.getAttribute("address");
                    String numBedrooms = element.getAttribute("numBedrooms");
                    String numBathrooms = element.getAttribute("numBathrooms");
                    String numCarspaces = element.getAttribute("numCarspaces");
                    String postcode = element.getAttribute("postcode");
                    String agent = element.getAttribute("agent");
                    String allowPets = element.getAttribute("allowPets");
                    StringProperty p = new StringProperty(id, propertyState, propertyType, price, suburb, latitude, longitude, address, numBedrooms, numBathrooms, numCarspaces, postcode, agent, allowPets);

                    lp.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lp;
    }

}
