package graph;
import java.io.*;
import java.util.*;
public class matrix {
    final double NO_LINK = Double.MAX_VALUE;
    int numStations;
    double[][] distance;
    StationInfo[] stations;
    static Scanner kb;

    public matrix(int capacity) {
        numStations = 0;
        distance = new double[capacity][capacity];
        stations = new StationInfo[capacity];
        // Initialize distances to NO_LINK
        for (int i = 0; i < capacity; i++) {
            Arrays.fill(distance[i], NO_LINK);
            distance[i][i] = 0;
        }
    }
    public void addStation(StationInfo station) {
        if (numStations < stations.length) {
            stations[numStations++] = station;
        }
    }
    public void setLink(int from, int to, double dist) {
        distance[from][to] = dist;
        distance[to][from] = dist; // Undirected graph
    }

    public void readNetworkFromFile(String filename) throws FileNotFoundException {
//        File file = new File(filename);
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            if (line.startsWith("Node")) {
//                String[] parts = line.split(":");
//                String[] coords = parts[1].trim().replace("(", "").replace(")", "").split(",");
//                addStation(new StationInfo(parts[0].trim(), Integer.parseInt(coords[0].trim()), Integer.parseInt(coords[1].trim())));
//            } else if (line.contains("to")) {
//                String[] parts = line.split(":");
//                String[] nodes = parts[0].split("to");
//                int from = findStationIndex(nodes[0].trim());
//                int to = findStationIndex(nodes[1].trim());
//                double dist = Double.parseDouble(parts[1].trim());
//                setLink(from, to, dist);
//            }
//        }
//        scanner.close();
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;  // Skip empty lines

            if (line.startsWith("Node")) {
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                String[] coords = parts[1].trim().replace("(", "").replace(")", "").split(",");
                if (coords.length < 1) {
                    System.out.println("Skipping invalid coordinates: " + line);
                    continue;
                }
                addStation(new StationInfo(parts[0].trim(), Integer.parseInt(coords[0].trim()), Integer.parseInt(coords[1].trim())));
            } else if (line.contains("to")) {
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    System.out.println("Skipping invalid link: " + line);
                    continue;
                }
                String[] nodes = parts[0].split("to");
                if (nodes.length < 2) {
                    System.out.println("Skipping invalid connection: " + line);
                    continue;
                }
                int from = findStationIndex(nodes[0].trim());
                int to = findStationIndex(nodes[1].trim());
                if (from == -1 || to == -1) {
                    System.out.println("Skipping unknown station in connection: " + line);
                    continue;
                }
                double dist = Double.parseDouble(parts[1].trim());
                setLink(from, to, dist);
            }
        }
        scanner.close();
    }

    private int findStationIndex(String name) {
        for (int i = 0; i < numStations; i++) {
            if (stations[i].getName().equals(name)) {
                return i;
            }
        }
        return -1; // Not found
    }

    // Add methods for depth-first traversal, breadth-first traversal, and Dijkstra's algorithm here

    // Method to print the network
    public void printNetwork() {
        System.out.println("Stations:");
        for (int i = 0; i < numStations; i++) {
            System.out.println(stations[i]);
        }
        System.out.println("Distance Matrix:");
        for (int i = 0; i < numStations; i++) {
            for (int j = 0; j < numStations; j++) {
                if (distance[i][j] == NO_LINK) {
                    System.out.print("NO_LINK ");
                } else {
                    System.out.print(distance[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        matrix network = new matrix(10);
        try {
            network.readNetworkFromFile("C:\\Users\\wwww\\Desktop\\DSA\\path\\src\\graph\\path.txt");
            network.printNetwork();
            // Call traversal and shortest path methods here
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}



