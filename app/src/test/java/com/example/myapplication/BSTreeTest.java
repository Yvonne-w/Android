package com.example.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BSTreeTest {

    BSTree bsTree = new BSTree();
    List<Property> propertiesSimple = new ArrayList<>();
    List<Property> propertiesComplex = new ArrayList<>();

    public void initializeProperties() {
        String[] ids = new String[] {"50", "30", "80", "25", "90", "85", "40"};
        String[] states = new String[] {"rent", "auction", "share", "rent", "share", "rent", "rent"};
        String[] types = new String[] {"house", "flat", "unit", "duplex", "house", "apartment", "house"};
        String[] prices = new String[] {"360", "300", "350", "600", "200", "250", "220"};
        String[] suburbs = new String[] {"city", "woden_Valley", "belconnen", "city", "belconnen", "city", "belconnen"};
        String[] agents = new String[] {"above", "jada", "lj_hooker", "jada", "unilodge", "lj_hooker", "lj_hooker"};
        String[] latitudes = new String[] {"30.1", "30.2", "30.3", "30.15", "30.2", "30.33", "30.05"};
        String[] longitudes = new String[] {"111.1", "111.2", "111.0", "111.15", "111.25", "111.05", "111.1"};
        String[] addresses = new String[] {"1 Marcus Rd", "10 Kiri St", "5 Centre Av", "2 Marcus Rd", "11 Kiri St", "55 Centre Av", "12 Kiri Ln"};
        String[] bedrooms = new String[] {"2", "1", "3", "2", "4", "3", "5"};
        String[] bathrooms = new String[] {"2", "1", "1", "1", "1", "1", "3"};
        String[] carspaces = new String[] {"1", "1", "0", "1", "2", "0", "2"};
        String[] postcodes = new String[] {"2600", "2619", "2617", "2600", "2620", "2617", "2605"};
        String[] allowPets = new String[] {"false", "false", "true", "false", "false", "true", "false"};
        int numProperties = ids.length;
        for (int i = 0; i < 3; i++) {
            propertiesSimple.add(new Property(ids[i], states[i], types[i], prices[i], suburbs[i], latitudes[i], longitudes[i], addresses[i], bedrooms[i], bathrooms[i], carspaces[i], postcodes[i], agents[i], allowPets[i]));
        }
        for (int i = 0; i < numProperties; i++) {
            propertiesComplex.add(new Property(ids[i], states[i], types[i], prices[i], suburbs[i], latitudes[i], longitudes[i], addresses[i], bedrooms[i], bathrooms[i], carspaces[i], postcodes[i], agents[i], allowPets[i]));
        }
    }


    @Test(timeout = 1000)
    public void addTest() {
        initializeProperties();
        for (int i = 0; i < propertiesSimple.size(); i++) {
            assertTrue(bsTree.add(propertiesSimple.get(i)));
        }
    }

    @Test(timeout = 1000)
    public void checkConditionsTest() {
        initializeProperties();
        bsTree.add(propertiesSimple.get(0));
        assertFalse(bsTree.checkConditions("price>520"));
        assertTrue(bsTree.checkConditions("price<380"));
    }

    @Test(timeout = 1000)
    public void inOrderSimpleTest() {
        initializeProperties();
        for (int i = 0; i < propertiesSimple.size(); i++) {
            bsTree.add(propertiesSimple.get(i));
        }
//        System.out.println(bsTree.toString());
        bsTree.inOrderTraverse(bsTree, "price<350");
        assertEquals(1, bsTree.result.size());
        assertEquals(propertiesSimple.get(1), bsTree.result.get(0));

        // re-initialize the result for next check
        bsTree.result = new ArrayList<>();

        bsTree.inOrderTraverse(bsTree, "bathrooms=1");
        assertEquals(2, bsTree.result.size());
//        System.out.println(bsTree.result);
        assertEquals(propertiesSimple.get(1), bsTree.result.get(0));
        assertEquals(propertiesSimple.get(2), bsTree.result.get(1));

        // check the condition that there is no valid result
        bsTree.result = new ArrayList<>();
    }

    @Test(timeout = 1000)
    public void noValidResult() {
        initializeProperties();
        for (int i = 0; i < propertiesSimple.size(); i++) {
            bsTree.add(propertiesSimple.get(i));
        }
        bsTree.inOrderTraverse(bsTree, "bedroom >3");
        assertEquals(new ArrayList<Property>(), bsTree.result);
    }

    @Test(timeout = 1000)
    public void inOrderComplexTest() {
        initializeProperties();
        for (int i = 0; i < propertiesComplex.size(); i++) {
            bsTree.add(propertiesComplex.get(i));
        }
        bsTree.inOrderTraverse(bsTree, "price > 300; suburb=city");
        assertEquals(2, bsTree.result.size());
        assertEquals(propertiesComplex.get(3), bsTree.result.get(0));
        assertEquals(propertiesComplex.get(0), bsTree.result.get(1));
        bsTree.result = new ArrayList<>();
        bsTree.inOrderTraverse(bsTree, "suburb=belconnen; bathrooms>1; price<300");
        assertEquals(1, bsTree.result.size());
        assertEquals(propertiesComplex.get(6), bsTree.result.get(0));
    }
}
