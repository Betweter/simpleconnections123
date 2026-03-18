package com.demo;
import java.util.*;

public class Dijkstra {

    public static PathResult search(Node start, Node goal, boolean useTime) {

        Map<Node, Double> dist = new HashMap<>();
        Map<Node, Node> prev = new HashMap<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(dist::get));

        for (Road r : start.roads) 
            dist.put(r.other(start), Double.POSITIVE_INFINITY);

        dist.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current == goal)
                break;

            for (Road r : current.roads) {
                Node next = r.other(current);
                double weight = useTime ? r.travelTime() : r.distance;

                double newDist = dist.get(current) + weight;

                if (newDist < dist.getOrDefault(next, Double.POSITIVE_INFINITY)) {
                    dist.put(next, newDist);
                    prev.put(next, current);
                    pq.add(next);
                }
            }
        }
        List<Node> path = reconstructPath(start, goal, prev);
        Node nextNode = path.size() > 1 ? path.get(1) : start;

        return new PathResult(nextNode, dist.getOrDefault(goal, Double.POSITIVE_INFINITY), path);
    }

    private static List<Node> reconstructPath(Node start, Node goal, Map<Node, Node> prev) {

        List<Node> path = new ArrayList<>();

        Node step = goal;

        if (!prev.containsKey(step) && step != start)
            return path; // brak ścieżki

        while (step != null) {
            path.add(step);
            step = prev.get(step);
        }

        Collections.reverse(path);

        return path;
    }
}
        /*
        PathResult result = Dijkstra.search(A, B, true);
        Node next = result.nextNode;
        double needed = result.cost;
        */