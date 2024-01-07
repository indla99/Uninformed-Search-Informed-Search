public class HeuristicPair {
    String hCityName;
    float hCost;

    public float gethCost() {
        return hCost;
    }

    public void sethCost(float hCost) {
        this.hCost = hCost;
    }

    public HeuristicPair(String hCityName, float hCost) {
        this.hCityName = hCityName;
        this.hCost = hCost;
    }

    public String gethCityName() {
        return hCityName;
    }

    public void sethCityName(String hCityName) {
        this.hCityName = hCityName;
    }
}
