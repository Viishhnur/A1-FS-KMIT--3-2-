/*
Question: Building Management System using Java Streams

You are tasked with developing a simple Building Management System. Each building has the following attributes:

    int ID: Unique identifier for the building

    String type: Type of building (e.g., "Residential", "Commercial", etc.)

    int occupancy: Number of people occupying the building

    double cost: Maintenance or rental cost associated with the building

Task:

    1) Create a Building class with appropriate constructor, getters, and toString() method.

    2) Accept a list of Building objects as input from the user.

    3) Using Java Streams, perform the following operations:

        a. List all Commercial buildings, ordered by occupancy in descending order.
        b. Calculate the average cost for each building type (e.g., average cost for "Commercial", "Residential", etc.).
        c. Find the Commercial building with the highest cost.

    Sample Output Format:-

    Commercial Buildings (sorted by occupancy):
    Building{id=102, type='Commercial', occupancy=80, cost=25000.0}
    Building{id=105, type='Commercial', occupancy=60, cost=20000.0}
    ...

    Average cost per type:
    Residential: 12000.5
    Commercial: 23000.0
    Industrial: 18000.0

    Most expensive Commercial Building:
    Building{id=102, type='Commercial', occupancy=80, cost=25000.0}

 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class Building {
    private final int id;
    private final String type;
    private final int occupancy;
    private final double cost;

    public Building(int id, String type, int occupancy, double cost) {
        this.id = id;
        this.type = type;
        this.occupancy = occupancy;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Building{id=%d, type='%s', occupancy=%d, cost=%.2f}", id, type, occupancy, cost);
    }
}

public class BuildingManagement {
    public static void main(String[] args) {
        List<Building> buildings = new ArrayList<>();
        int n;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of buildings: ");
            n = sc.nextInt();
            sc.nextLine();
            
            for (int i = 0; i < n; i++) {
                System.out.println("\nEnter details for building " + (i + 1));
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                
                System.out.print("Type: ");
                String type = sc.nextLine();
                
                System.out.print("Occupancy: ");
                int occupancy = sc.nextInt();
                
                System.out.print("Cost: ");
                double cost = sc.nextDouble();
                sc.nextLine();
                
                buildings.add(new Building(id, type, occupancy, cost));
            }
            
        }

        // a. List Commercial buildings sorted by occupancy (descending)
        System.out.println("\nCommercial Buildings (sorted by occupancy):");
        buildings.stream()
                .filter(b -> b.getType().equalsIgnoreCase("Commercial"))
                .sorted(Comparator.comparingInt(Building::getOccupancy).reversed())
                .forEach(System.out::println);
        
        // b. Average cost per building type
        System.out.println("\nAverage cost per type:");
        Map<String, Double> avgCostPerType = buildings.stream()
                .collect(Collectors.groupingBy(
                        Building::getType,
                        Collectors.averagingDouble(Building::getCost)
                ));
        
        avgCostPerType.forEach((type, avgCost) ->
                System.out.printf("%s: %.2f\n", type, avgCost));
        
        // c. Commercial building with the maximum cost
        System.out.println("\nMost expensive Commercial Building:");
        buildings.stream()
                .filter(b -> b.getType().equalsIgnoreCase("Commercial"))
                .max(Comparator.comparingDouble(Building::getCost))
                .ifPresent(System.out::println);
    }
}
