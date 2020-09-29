import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return this.properties.stream().map(property -> "•" + property.description() + ";\n").collect(Collectors.joining());
    }

    public void saveToBespokeFile(File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for (Property property: properties) {
                String line = property.id+"|"+property.propertyState+"|"+property.propertyType+"|"+
                        property.price+"|"+property.suburb+"|"+property.latitude+"|"+
                        property.longitude+"|"+property.address+"|"+property.numBedrooms+"|"+
                        property.numBathrooms+"|"+property.numCarspaces+"|"+property.postcode+"|"+
                        property.agent+"|"+property.allowPets;
                fileWriter.write(line);
                fileWriter.write(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static PropertyGenerator loadFromBespokeFile(File file) {
        PropertyGenerator propertyGenerator = new PropertyGenerator();
        Set<Property> propertyList = new HashSet<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] items = line.split("\\|");
                PropertyState propertyState = Enum.valueOf(PropertyState.class, items[0]);
                PropertyType propertyType = Enum.valueOf(PropertyType.class, items[1]);
                double price = Double.parseDouble(items[2]);
                Suburb suburb = Enum.valueOf(Suburb.class, items[3]);
                double latitude = Double.parseDouble(items[4]);
                double longtitude = Double.parseDouble(items[5]);
                String address = items[6];
                int numBedrooms = Integer.parseInt(items[7]);
                int numBathrooms = Integer.parseInt(items[8]);
                int numCarspaces = Integer.parseInt(items[9]);
                int postcode = Integer.parseInt(items[10]);
                Agent agent = Enum.valueOf(Agent.class, items[11]);
                boolean allowPets = Boolean.parseBoolean(items[12]);
                Property property = new Property(propertyState, propertyType, price, suburb, latitude, longtitude, address, numBedrooms, numBathrooms, numCarspaces, postcode, agent, allowPets);
                propertyList.add(property);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        propertyGenerator.setProperties(propertyList);
        return propertyGenerator;
    }

    public PropertyGenerator createProperties() {
        PropertyGenerator propertyGenerator;
        Set<Property> properties = new HashSet<>();
        int numProperties = 1000;
        double base_latitude = -35.282001;
        double base_longtitude = 149.128998;
        Random random = new Random();
        for (int i = 0; i < numProperties; i++) {
            PropertyState ps = randomEnum(PropertyState.class);
            PropertyType pt = randomEnum(PropertyType.class);
            double price = 0;
            if (ps == PropertyState.Auction || ps == PropertyState.Sale) {
                price = 100000 + 900000*random.nextDouble();
            } else {
                price = 100 + 600*random.nextDouble();
            }
            Suburb suburb = randomEnum(Suburb.class);
            double latitude = base_latitude + 0.1*(random.nextDouble()-0.5);
            double longtitude = base_longtitude + 0.1*(random.nextDouble()-0.5);
            String address = "TBD";
            int numBedrooms = 1+random.nextInt(5);
            int numBathrooms = 1+random.nextInt(2);
            int numCarspaces = random.nextInt(2);
            int postcode = 2600+random.nextInt(30);
            Agent agent = randomEnum(Agent.class);
            boolean allowPets = random.nextBoolean();

            Property p =new Property(ps, pt, price, suburb, latitude, longtitude, address, numBedrooms, numBathrooms, numCarspaces, postcode, agent, allowPets);
            p.id = 1001+i;
            properties.add(p);
        }
        propertyGenerator = new PropertyGenerator(properties);
        System.out.println("Number of properties generated: " + properties.size());
        File file = new File("sample_properties.txt");
        file.delete();
        propertyGenerator.saveToBespokeFile(file);

        return propertyGenerator;
    }

    // helper to select randomly from an enum list
    public static <T extends Enum<?>> T randomEnum(Class<T> tClass) {
        Random random = new Random();
        int randomNo = random.nextInt(tClass.getEnumConstants().length);
        return tClass.getEnumConstants()[randomNo];
    }

    public void testGenerator() {

    }

    public static void main(String[] args) {
        PropertyGenerator pg = new PropertyGenerator().createProperties();
//        System.out.println(pg);
    }
}
