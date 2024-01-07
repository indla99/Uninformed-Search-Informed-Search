import java.io.*;
import java.util.*;

public class find_route {
    /*
    Reading the input1.txt file until "END OF INPUT"
     */
    public static void formGraph(List<Link> list, String fileName)  {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                    if (line.equals("END OF INPUT")) {
                        break;
                    } else {
                        /*
                        split into such array [source-city, destination-city, distance]
                         */
                        String[] path = line.split(" ");
                        Link link = new Link(path[0], path[1], Float.parseFloat(path[2]));
                        list.add(link);
                    }
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    static int nodes_expanded=0;
    static int nodes_generated=1;
    static void findRoute(City startCity, City endCity, PriorityQueue<City> queue, ArrayList<Link> allCitiesRoutes, List<String> visitedCity) {
        queue.add(startCity);
        while(!queue.isEmpty()) {
            City currentCity=queue.poll();
            nodes_expanded++;
            /*
            Check if the current city reaches the destination
             */
            if(currentCity.getName().equals(endCity.getName())) {
                print_route(currentCity,allCitiesRoutes);
                break;
            }
            /*
            Ignore the case if city is already visited
            Checking the cities which are not visited and Mark them as visited
             */
            if(!visitedCity.contains(currentCity.getName())) {
                visitedCity.add(currentCity.getName());
                ArrayList<City> nextCities=new ArrayList<>();
                for(Link link:allCitiesRoutes) {
                    /*
                    Constructing the path whether source or destination of all the routes is matched
                    with the current city Name to keep track of the next cities as well.
                    Therefore, we get the routes linked to current city
                     */
                    if(link.getSrcCity().equals(currentCity.getName())) {
                        City nextCity=new City(link.getDestCity(),currentCity.getTotalDistance()+1,currentCity.getTotalDistance()+link.getDistance(),currentCity);
                        nextCities.add(nextCity);
                    }
                    else if(link.getDestCity().equals(currentCity.getName())) {
                        City nexCity=new City(link.getSrcCity(),currentCity.getLevel()+1,currentCity.getTotalDistance()+link.getDistance(),currentCity);
                        nextCities.add(nexCity);
                    }
                }
                currentCity.setNextCities(nextCities);
                /*
                Adding the remaining cities to the existing queue
                 */
                for(int i=0;i<currentCity.getNextCities().size();i++) {
                    City nextCity= currentCity.getNextCities().get(i);
                    nodes_generated++;
                    queue.add(nextCity);
                }
            }
        }
        /*
        If all the cities in the Queue are visited and did not find
        any destination
         */
        if(queue.isEmpty())
        {
            System.out.println("nodes expanded: "+nodes_expanded);
            System.out.println("nodes generated: "+nodes_generated);
            System.out.println("distance= infinity");
            System.out.println("route:"+'\n'+"none");
        }
    }

     /*
        Reading the h_cityName.txt file until "END OF INPUT"
     */
    public static void formHGraph(ArrayList<HeuristicPair> hList, String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String parsedString;
            while ((parsedString = reader.readLine()) != null) {
                if (parsedString.equals("END OF INPUT")) {
                    break;
                } else {
                    /*
                    split into such array [source-city, distance]
                     */
                    String[] path = parsedString.split(" ");
                    HeuristicPair hPair = new HeuristicPair(path[0], Float.parseFloat(path[1]));
                    hList.add(hPair);
                }
            }
            reader.close();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
    Uniform Cost search
     */
    public static void findHRoute(City startCity, City endCity, PriorityQueue<City> queue, ArrayList<Link> allCitiesRoutes, ArrayList<HeuristicPair> hRoutes) {
        nodes_generated=0;
        queue.add(startCity);
        City destCity = null;
        ArrayList<String> visited = new ArrayList<>();
        while(!queue.isEmpty()) {
            City currCity = queue.poll();
            /*
            Check if the current city reaches the destination
             */
            if(currCity.getName().equals(endCity.getName())) {
                nodes_expanded=(int)currCity.getLevel()+1;
                print_route(currCity,allCitiesRoutes);
                destCity=currCity;
                break;
            } else {
                nodes_expanded++;
                /*
                Ignore the case if city is already visited
                Checking the cities which are not visited and Mark them as visited
                 */
                if(!visited.contains(currCity.getName())) {
                    visited.add(currCity.getName());
                    /*
                    Compare the source of current city with every other city
                    of given input file and destination of the current city in
                    the Heuristic data file which point to the endCity always.
                     */
                    for(Link allCity: allCitiesRoutes) {
                        if(allCity.getSrcCity().equals(currCity.getName())) {
                            for (HeuristicPair hPair : hRoutes) {
                                if(allCity.getDestCity().equals(hPair.gethCityName())) {
                                    City newCity = new City(allCity.destCity,currCity.getLevel()+1, currCity.getTotalDistance()+allCity.getDistance(), currCity);
                                    queue.add(newCity);
                                }
                            }
                        } else if(allCity.getDestCity().equals(currCity.getName())) {
                            for(HeuristicPair hPair : hRoutes) {
                                if(allCity.srcCity.equals(hPair.gethCityName())) {
                                    City newCity = new City(allCity.srcCity, currCity.getLevel()+1, currCity.getTotalDistance()+allCity.getDistance(),currCity);
                                    nodes_generated++;
                                    queue.add(newCity);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(destCity == null)
        {
            System.out.println("nodes expanded: "+nodes_expanded);
            System.out.println("nodes generated: "+nodes_generated);
            System.out.println("distance= infinity");
            System.out.println("route:"+'\n'+"none");
        }
    }

    /*
    To find the total distance between the source city and destination city
     */
    public static float getTotalPathDistance(String source_city,String destination_city, ArrayList<Link> allCitiesLinks) {
        float distance=0;
        for(int i=0;i<allCitiesLinks.size();i++) {
            if(source_city.equals(allCitiesLinks.get(i).getSrcCity()) || source_city.equals(allCitiesLinks.get(i).getDestCity()) ){
                if(destination_city.equals(allCitiesLinks.get(i).getDestCity()) || destination_city.equals(allCitiesLinks.get(i).getSrcCity())) {
                    distance=allCitiesLinks.get(i).getDistance();
                }
            }
        }
        return distance;
    }

    /*
    Print the route from source city to destination city
     */
    public static void print_route(City destCity,ArrayList<Link> allCitiesRoutes)
    {
        System.out.println("nodes expanded: "+nodes_expanded);
        System.out.println("nodes generated: "+nodes_generated);
        System.out.println("distance: "+destCity.getTotalDistance()+ "km");
        System.out.println("route:");
        ArrayList<String> str = new ArrayList<>();
        while(destCity.getPrevCity()!=null) {
            /*
            Since we kept track of parent, loop in back to the source and form the path
             */
            String pathName = destCity.getPrevCity().getName()+" to "+ destCity.getName()+":"+ getTotalPathDistance(destCity.getName(),destCity.getPrevCity().getName(),allCitiesRoutes)+ "km";
            str.add(pathName);
            destCity=destCity.getPrevCity();
        }
        /*
        Print the path from source to destination
         */
        for(int i = str.size()-1;i >= 0 ; i--) {
            System.out.println(str.get(i));
        }
    }
    public static void main(String[] args) throws IOException {
        ArrayList<Link> list = new ArrayList<>();
        ArrayList<String> visitedCity = new ArrayList<>();
        ArrayList<HeuristicPair> hList = new ArrayList<>();
        City srcCity = new City(args[1], 0, 0, null); // source city
        City destCity = new City(args[2], 0, 0, null); // destination city
        /*
        Un-informed cost search
         */
        if(args.length <=3) {
                formGraph(list, args[0]);
                findRoute(srcCity, destCity, new PriorityQueue<City>(1000, new CostComparator()), list, visitedCity);
        } else {
            /*
            Informed cost search
             */
            formGraph(list, args[0]);
            formHGraph(hList, args[3]);
            findHRoute(srcCity,destCity, new PriorityQueue<City>(1000, new CostComparator()),list, hList);
        }
    }
}
