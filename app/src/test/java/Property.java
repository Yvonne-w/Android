import java.io.Serializable;

public class Property implements Serializable {
    public int id;
    public PropertyState propertyState;
    public PropertyType propertyType;
    public double price;
    public Suburb suburb;
    public double latitude;
    public double longitude;
    public String address;
    public int numBedrooms;
    public int numBathrooms;
    public int numCarspaces;
    public int postcode;
    public Agent agent;
    public boolean allowPets;

    public Property(PropertyState ps, PropertyType pt, double price, Suburb sb, double lat, double lon,
                    String add, int numBedrooms, int numBathrooms, int numCs, int pc, Agent ag, boolean pets) {
        this.propertyState = ps;
        this.propertyType = pt;
        this.price = price;
        this.suburb = sb;
        this.latitude = lat;
        this.longitude = lon;
        this.address = add;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.numCarspaces = numCs;
        this.postcode = pc;
        this.agent = ag;
        this.allowPets = pets;
    }

    //
    public String description() {
        return String.format("This is a %s for %s at price %.0f in suburb %s. \n" +
                        "Number of bedrooms: %d; \n" +
                        "Number of bathrooms: %d; \n" +
                        "Number of car spaces: %d; \n" +
                        "Petsallowed: %b; \n" +
                        "Managed by agent: %s",
                this.propertyType, this.propertyState.getState(), this.price, this.suburb,
                this.numBedrooms, this.numBathrooms, this.numCarspaces, this.allowPets,
                this.agent);
    }

    public static void main(String[] args) {
        Property p = new Property(PropertyState.Rent, PropertyType.Duplex, 350.5, Suburb.Belconnen, -35.282001, 149.128998,
                "11 Kirinari St", 5, 2, 0, 2617, Agent.Unilodge, false);
        System.out.println(p.description());
    }
}

enum PropertyState {
    // Sold("So"),
    Rent("rent"), Sale("sale"), Auction("auction"), Share("share");
    private PropertyState(String state) {
        this.state = state;
    }
    public String getState() {
        return this.state;
    }
    private String state;
}

enum PropertyType {
    House, Unit, Townhouse, Flat, Apartment, Duplex;
}

enum Suburb {
    Belconnen, Canberra_Central, Gungahlin, Jerrabomberra, Majura, Molonglo_Valley,
    Tuggeranong, Weston_Creek, Woden_Valley;
}

enum Agent {
    Luton, Strive, Badenoch, Above, RayWhite, Harcourts, UniGardens, LJ_Hooker, Bolton,
    Mountain, Veriton, Jada, Smart_Home_Creation, Mangolilly, Action_Advantage, Unilodge;
}




