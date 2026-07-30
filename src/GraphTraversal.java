import java.util.*;

public class GraphTraversal {

    static Map<Character, List<Character>> graph = new LinkedHashMap<>();

    static void buildGraph() {
        graph.put('A', Arrays.asList('C', 'B', 'D'));
        graph.put('B', Arrays.asList('A', 'C', 'E', 'G'));
        graph.put('C', Arrays.asList('A', 'B', 'D'));
        graph.put('D', Arrays.asList('C', 'A'));
        graph.put('E', Arrays.asList('G', 'F', 'B'));
        graph.put('F', Arrays.asList('G', 'E'));
        graph.put('G', Arrays.asList('F', 'B'));
    }

    static void dfs(char node, Set<Character> visited, StringBuilder order) {
        visited.add(node);
        order.append(node).append(" ");
        for (char neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited, order);
            }
        }
    }

    static String bfs(char start) {
        Set<Character> visited = new HashSet<>();
        Queue<Character> queue = new LinkedList<>();
        StringBuilder order = new StringBuilder();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            char node = queue.poll();
            order.append(node).append(" ");
            for (char neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return order.toString();
    }

    public static void main(String[] args) {
        buildGraph();

        Set<Character> visited = new HashSet<>();
        StringBuilder dfsOrder = new StringBuilder();
        dfs('A', visited, dfsOrder);
        System.out.println("DFS order: " + dfsOrder.toString().trim());

        System.out.println("BFS order: " + bfs('A').trim());
    }
}
