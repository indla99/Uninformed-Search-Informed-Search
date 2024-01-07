import java.util.Comparator;

public class CostComparator implements Comparator<City> {
    @Override
    public int compare(City n1, City n2) {
        /*
        Check the comparator based on the total distance between the cities
         */
        if(n1.getTotalDistance() > n2.getTotalDistance()) {
            return 1;
        }
        if(n1.getTotalDistance() == n2.getTotalDistance()) {
            return 0;
        }
        return -1;
    }
}
