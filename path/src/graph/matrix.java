package graph;

import java.io.*;
import java.util.*;

public class matrix {
    final double NO_LINK = Double.MAX_VALUE;
    int numStations;
    double[][] distance;
    StationInfo[] stations;

    public matrix(int capacity) {
        stations = new StationInfo[capacity];
        numStations = 0;
        distance = new double[capacity][capacity];
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                distance[i][j] = NO_LINK;
            }
        }

    }

    public void addStation(String name, int x, int y) {
        if (numStations < stations.length) {
            stations[numStations++] = new StationInfo(name, x, y);
        } else {
            System.out.println("Capacity reached, cannot add more stations.");
        }
    }

    public void setLink(String from, String to, double dist) {
        int indexFrom = -1, indexTo = -1;
        for (int i = 0; i < numStations; i++) {
            if (stations[i].getName().equals(from)) {
                indexFrom = i;
            }
            if (stations[i].getName().equals(to)) {
                indexTo = i;
            }
        }
        if (indexFrom != -1 && indexTo != -1) {
            distance[indexFrom][indexTo] = dist;
            distance[indexTo][indexFrom] = dist; // Because it's symmetric
        } else {
            System.out.println("Station names not found.");
        }
    }
    public void readNetworkFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Line read: " + line); // Debug statement
                String[] parts = line.split("\\s+");
                System.out.println("Parts: " + Arrays.toString(parts)); // Debug statement

                if (parts[0].equals("\"station\"")) {
                    String name = parts[1];
                    int xPos = Integer.parseInt(parts[2]);
                    int yPos = Integer.parseInt(parts[3]);

                    addStation(name, xPos, yPos);

                } else if (parts[0].equals("\"link\"")) {
                    System.out.println("Link found"); // Debug statement
                    if (parts.length == 4) { // Link with two nodes and distance
                        double dist = Double.parseDouble(parts[3]);
                        setLink(parts[1], parts[2], dist);
                        System.out.println("Link with two nodes: " + parts[1] + " -> " + parts[2] + ", distance: " + dist); // Debug statement

                    }
                }
            }}catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // After file is read, print the station infos and adjacency matrix
        System.out.println("This is StationInfos[]:");
        for (int i = 0; i < numStations; i++) {
            System.out.print(stations[i].getName() + " ");
        }
        System.out.print("\n");

        System.out.println("This is Adjacency Matrix[][]:");
        for (int i = 0; i < numStations; i++) {
            for (int j = 0; j < numStations; j++) {
                System.out.print((distance[i][j] == NO_LINK ? "NO_LINK" : distance[i][j]) + "\t");
            }
            System.out.println();
        }
    }
    public List<String> depthFirstSearch(String startStation) {
        StackInt stack = new StackInt(9);  // Assuming StackInt supports basic stack operations
        List<String> visited = new ArrayList<>();
        boolean[] visitedIndices = new boolean[numStations];
        int startIndex = getIndexByName(startStation);
        if (startIndex == -1) {
            return visited; // Start station not found
        }
        stack.push(startIndex);

        while (stack.getSize()!=0) {
            int currentIndex = stack.pop();
            if (!visitedIndices[currentIndex]) {
                visitedIndices[currentIndex] = true;
                visited.add(stations[currentIndex].getName());
                for (int i = 0; i < numStations; i++) {
                    if (distance[currentIndex][i] != NO_LINK && !visitedIndices[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        return visited;
    }

    public List<String> breadthFirstSearch(String startStation) {
        QueueInt queue = new QueueInt(9);  // Assuming QueueInt supports basic queue operations
        List<String> visited = new ArrayList<>();
        boolean[] visitedIndices = new boolean[numStations];
        int startIndex = getIndexByName(startStation);
        if (startIndex == -1) {
            return visited; // Start station not found
        }
        queue.addToBack(startIndex);

        while (queue.getSize()!=0) {
            int currentIndex = queue.removefromFront();
            if (!visitedIndices[currentIndex]) {
                visitedIndices[currentIndex] = true;
                visited.add(stations[currentIndex].getName());
                for (int i = 0; i < numStations; i++) {
                    if (distance[currentIndex][i] != NO_LINK && !visitedIndices[i]) {
                        queue.addToBack(i);
                    }
                }
            }
        }
        return visited;
    }
    public List<String> findShortestPath(String start, String end) {
        int startIndex = getIndexByName(start);
        int endIndex = getIndexByName(end);
        if (startIndex == -1 || endIndex == -1) {
            System.out.println("Start or end station not found.");
            return new ArrayList<>();
        }

        double[] dist = new double[numStations];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[startIndex] = 0;

        int[] prev = new int[numStations];
        Arrays.fill(prev, -1);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> dist[node]));
        pq.add(startIndex);

        boolean[] closed = new boolean[numStations];
        int iterations = 0; // To count while loop iterations

        while (!pq.isEmpty()) {
            int current = pq.poll();

            if (closed[current]) continue;
            closed[current] = true;
            iterations++;

            if (current == endIndex) break;

            for (int neighbor = 0; neighbor < numStations; neighbor++) {
                if (distance[current][neighbor] != NO_LINK && !closed[neighbor]) {
                    double newDist = dist[current] + distance[current][neighbor];
                    if (newDist < dist[neighbor]) {
                        dist[neighbor] = newDist;
                        prev[neighbor] = current;
                        pq.add(neighbor);
                    }
                }
            }
        }

        System.out.println("Number of iterations: " + iterations); // Output the count of while loop iterations

        // Reconstruct the path
        List<String> path = new ArrayList<>();
        for (int at = endIndex; at != -1; at = prev[at]) {
            path.add(stations[at].getName());
        }
        Collections.reverse(path);
        return path;
    }
    private int getIndexByName(String name) {
        for (int i = 0; i < numStations; i++) {
            if (stations[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;  // If no station is found
    }


    public static void main(String[] args) {
        matrix network = new matrix(10);
        try {
            network.readNetworkFromFile("C:\\Users\\wwww\\Desktop\\DSA\\path\\src\\graph\\path.txt");
            String startStation = "NodeB";  // Write the truth name
            String endStation = "NodeG";
            // Dfs
            List<String> dfsResult = network.depthFirstSearch(startStation);
            System.out.println("Depth First Search result:");
            for (String station : dfsResult) {
                System.out.println(station);
            }

            // Bfs
            List<String> bfsResult = network.breadthFirstSearch(startStation);
            System.out.println("Breadth First Search result:");
            for (String station : bfsResult) {
                System.out.println(station);
            }


            // Dijkstra's Shortest Path
            List<String> shortestPathResult = network.findShortestPath(startStation, endStation);
            System.out.println("Shortest Path result:");
            for (String station : shortestPathResult) {
                System.out.println(station);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

