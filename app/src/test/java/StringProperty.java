import java.io.Serializable;
import java.util.Comparator;

public class StringProperty implements Comparable<StringProperty> {
    public String id;
    private String propertyState;
    private String propertyType;
    private String price;
    private String suburb;
    private String latitude;
    private String longitude;
    private String address;
    private String numBedrooms;
    private String numBathrooms;
    private String numCarspaces;
    private String postcode;
    private String agent;
    private String allowPets;

    public StringProperty(){
        this.id = "0";
    }

    public StringProperty( String id, String propertyState, String propertyType,
                           String price, String suburb, String latitude, String longitude,
                           String address, String numBedrooms, String numBathrooms, String numCarspaces,
                           String postcode, String agent, String allowPets){
        this.id = id;
        this.propertyState = propertyState;
        this.propertyType = propertyType;
        this.price = price;
        this.suburb = suburb;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.numCarspaces = numCarspaces;
        this.postcode = postcode;
        this.agent = agent;
        this.allowPets = allowPets;

    }

    public String getId() {
        return id;
    }

    public String getPropertyState() {
        return propertyState;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getNumBathrooms() {
        return numBathrooms;
    }

    public String getNumBedrooms() {
        return numBedrooms;
    }

    public String getNumCarspaces() {
        return numCarspaces;
    }

    public String getPrice() {
        return price;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAgent() {
        return agent;
    }

    public String getAllowPets() {
        return allowPets;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "StringProperty{" +
                "id='" + id + '\'' +
                ", propertyState='" + propertyState + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", price='" + price + '\'' +
                ", suburb='" + suburb + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", address='" + address + '\'' +
                ", numBedrooms='" + numBedrooms + '\'' +
                ", numBathrooms='" + numBathrooms + '\'' +
                ", numCarspaces='" + numCarspaces + '\'' +
                ", postcode='" + postcode + '\'' +
                ", agent='" + agent + '\'' +
                ", allowPets='" + allowPets + '\'' +
                '}';
    }


    public int compareTo(StringProperty o) {
        int o1 = Integer.parseInt( o.id);
        int o2 = Integer.parseInt(this.id);
        int result = 0;
        if(o1-o2>0) {
            result =1;
        } else if(o1 - o2<0) {
            result =-1;
        }else{
            result = 0;
        }
        return result;
    }

}

