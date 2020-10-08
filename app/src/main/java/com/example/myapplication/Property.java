package com.example.myapplication;

public class Property implements Comparable<Property> {
    public int id;
    private State state;
    private Type type;
    private double price;
    private Suburb suburb;
    private double latitude;
    private double longitude;
    private String address;
    private int numBedrooms;
    private int numBathrooms;
    private int numCarspaces;
    private int postcode;
    private Agent agent;
    private boolean allowPets;

    public Property() {
        this.id = 0;
    }

    public Property(State ps, Type pt, double price, Suburb sb, double lat, double lon,
                    String add, int numBedrooms, int numBathrooms, int numCs, int pc, Agent ag, boolean pets) {
        this.state = ps;
        this.type = pt;
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
        this.id = 0;
    }

    public Property(String id, String propertyState, String propertyType,
                    String price, String suburb, String latitude, String longitude,
                    String address, String numBedrooms, String numBathrooms, String numCarspaces,
                    String postcode, String agent, String allowPets) {
        this.id = Integer.parseInt(id);
        this.state = State.valueOf(propertyState);
        this.type = Type.valueOf(propertyType);
        this.price = Double.parseDouble(price);
        this.suburb = Suburb.valueOf(suburb);
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.address = address;
        this.numBedrooms = Integer.parseInt(numBedrooms);
        this.numBathrooms = Integer.parseInt(numBathrooms);
        this.numCarspaces = Integer.parseInt(numCarspaces);
        this.postcode = Integer.parseInt(postcode);
        this.agent = Agent.valueOf(agent);
        this.allowPets = Boolean.parseBoolean(allowPets);

    }

    public int getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public Type getType() {
        return type;
    }

    public Suburb getSuburb() {
        return suburb;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getNumBathrooms() {
        return numBathrooms;
    }

    public int getNumBedrooms() {
        return numBedrooms;
    }

    public int getNumCarspaces() {
        return numCarspaces;
    }

    public double getPrice() {
        return price;
    }

    public int getPostcode() {
        return postcode;
    }

    public Agent getAgent() {
        return agent;
    }

    public boolean getAllowPets() {
        return allowPets;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String description() {
        return String.format("This is a %s for %s at price %.0f in suburb %s. \n" +
                        "Number of bedrooms: %d; \n" +
                        "Number of bathrooms: %d; \n" +
                        "Number of car spaces: %d; \n" +
                        "Petsallowed: %b; \n" +
                        "Managed by agent: %s",
                this.type, this.state.getState(), this.price, this.suburb,
                this.numBedrooms, this.numBathrooms, this.numCarspaces, this.allowPets,
                this.agent);
    }


    public static void main(String[] args) {
        Property p = new Property(State.rent, Type.duplex, 350.5, Suburb.belconnen, -35.282001, 149.128998,
                "11 Kirinari St", 5, 2, 0, 2617, Agent.unilodge, false);
        System.out.println(p.description());
    }

    public int compareTo(Property o) {
        int o1 = o.id;
        int o2 = this.id;
        int result = 0;
        if (o1 - o2 > 0) {
            result = 1;
        } else if (o1 - o2 < 0) {
            result = -1;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", state=" + state +
                ", type=" + type +
                ", price=" + price +
                ", suburb=" + suburb +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", numBedrooms=" + numBedrooms +
                ", numBathrooms=" + numBathrooms +
                ", numCarspaces=" + numCarspaces +
                ", postcode=" + postcode +
                ", agent=" + agent +
                ", allowPets=" + allowPets +
                '}';
    }

    public String getAttribute(String s) {
        switch (s) {
            case "id":
                return String.valueOf(this.getId());
            case "state":
                return String.valueOf(this.getId());
            case "type":
                return String.valueOf(this.getType());
            case "price":
                return String.valueOf(this.getPrice());
            case "suburb":
                return String.valueOf(this.getSuburb());
            case "latitude":
                return String.valueOf(this.getLatitude());
            case "longitude":
                return String.valueOf(this.getLongitude());
            case "address":
                return String.valueOf(this.getAddress());
            case "bedroom":
                return String.valueOf(this.getNumBedrooms());
            case "bathroom":
                return String.valueOf(this.getNumBathrooms());
            case "car":
                return String.valueOf(this.getNumCarspaces());
            case "postcode":
                return String.valueOf(this.getPostcode());
            case "agent":
                return String.valueOf(this.getAgent());
            case "allowpets":
                return String.valueOf(this.getAllowPets());
        }

        return "";
    }
}


enum State {
    rent("rent"), sale("sale"), auction("auction"), share("share");

    private State(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    private String state;
}

enum Type {
    house, unit, townhouse, flat, apartment, duplex;
}

enum Suburb {
    belconnen, city, gungahlin, jerrabomberra, majura, molonglo_Valley,
    tuggeranong, weston_Creek, woden_Valley;
}

enum Agent {
    luton, strive, badenoch, above, rayWhite, harcourts, uniGardens, lj_hooker, bolton,
    mountain, veriton, jada, smart_home_creation, mangolilly, action_advantage, unilodge;
}




