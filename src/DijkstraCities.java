import java.util.*;

public class DijkstraCities {

    static List<String> cityList = Arrays.asList("Glasgow", "Stirling", "Edinburgh", "Perth", "Dundee");

    public static void main(String[] args) {
        Map<String, List<int[]>> graph = new HashMap<>();
        for (String c : cityList) graph.put(c, new ArrayList<>());

        addEdge(graph, "Glasgow", "Stirling", 50);
        addEdge(graph, "Glasgow", "Edinburgh", 70);
        addEdge(graph, "Stirling", "Perth", 40);
        addEdge(graph, "Stirling", "Edinburgh", 50);
        addEdge(graph, "Edinburgh", "Perth", 100);
        addEdge(graph, "Perth", "Dundee", 60);

        Map<String, Integer> dist = dijkstra(graph, "Edinburgh", cityList);

        System.out.println("Shortest distance from Edinburgh to Dundee: " + dist.get("Dundee"));
    }

    static void addEdge(Map<String, List<int[]>> graph, String a, String b, int weight) {
        graph.get(a).add(new int[]{cityIndex(b), weight});
        graph.get(b).add(new int[]{cityIndex(a), weight});
    }

    static int cityIndex(String city) {
        return cityList.indexOf(city);
    }

    static Map<String, Integer> dijkstra(Map<String, List<int[]>> graph, String start, List<String> cities) {
        Map<String, Integer> dist = new HashMap<>();
        for (String c : cities) dist.put(c, Integer.MAX_VALUE);
        dist.put(start, 0);

        PriorityQueue<Object[]> pq = new PriorityQueue<>((a, b) -> (int) a[0] - (int) b[0]);
        pq.offer(new Object[]{0, start});

        Set<String> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Object[] current = pq.poll();
            int d = (int) current[0];
            String node = (String) current[1];

            if (visited.contains(node)) continue;
            visited.add(node);

            for (int[] neighbor : graph.get(node)) {
                String neighborCity = cityList.get(neighbor[0]);
                int weight = neighbor[1];
                int newDist = d + weight;
                if (newDist < dist.get(neighborCity)) {
                    dist.put(neighborCity, newDist);
                    pq.offer(new Object[]{newDist, neighborCity});
                }
            }
        }
        return dist;
    }
}
