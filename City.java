import java.util.ArrayList;

public class City {
    String name;
    float level;
    float totalDistance;
    float hDistance;
    City prevCity;

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public float gethDistance() {
        return hDistance;
    }

    public void sethDistance(float hDistance) {
        this.hDistance = hDistance;
    }



    public ArrayList<City> getNextCities() {
        return nextCities;
    }

    public void setNextCities(ArrayList<City> nextCities) {
        this.nextCities = nextCities;
    }

    ArrayList<City> nextCities = new ArrayList<City>();
    public City(String name, float level, float totalDistance, City prevCity) {
        this.name = name;
        this.level = level;
        this.totalDistance = totalDistance;
        this.prevCity = prevCity;
    }
    public City(String name, float level, float totalDistance, City prevCity, float hDistance) {
        this.name = name;
        this.level = level;
        this.totalDistance = totalDistance;
        this.hDistance = hDistance;
    }
    public City(String name, float level, float totalDistance, City prevCity, ArrayList<City> nextCities) {
        this.name = name;
        this.level = level;
        this.totalDistance = totalDistance;
        this.prevCity = prevCity;
        this.nextCities = nextCities;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public City getPrevCity() {
        return prevCity;
    }

    public void setPrevCity(City prevCity) {
        this.prevCity = prevCity;
    }
}
