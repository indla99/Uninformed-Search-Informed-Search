public class Link {
    public String srcCity;
    public String destCity;
    public float distance;

    public String getSrcCity() {
        return srcCity;
    }

    public void setSrcCity(String srcCity) {
        this.srcCity = srcCity;
    }

    public void setSource(String source) {
        this.srcCity = source;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Link(String source, String destination, float distance) {
        this.srcCity = source;
        this.destCity = destination;
        this.distance = distance;
    }
}
