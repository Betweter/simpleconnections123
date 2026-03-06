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
        Node nextNode = reconstructNext(start, goal, prev);

        return new PathResult(nextNode, dist.getOrDefault(goal, Double.POSITIVE_INFINITY));
    }

    private static Node reconstructNext(Node start, Node goal, Map<Node, Node> prev) {

        Node step = goal;
        while (prev.containsKey(step) && prev.get(step) != start)
            step = prev.get(step);

        return step;
    }
}
        /*
        chcemy na podstawie boolean time wybrać czy wagą będzie road.distance czy road.distance/road.currentlimit
        chcemy ustawić nextNode na następny w kolejności węzeł do odzwiedznia,
        żeby droga była najkrótsza, a Needed to metryka, ile trzeba do końca
        
        for every node n {
            dist[n] = inf;
            prev[n] = null;
            dist[a] = 0;
            all nodes unexplored
        }
        while(b unexplored){
            n = least-valued uneplored node
            explore n;
            for each rode(n, r){
                if(dist[n]+len(n,r) < dist[r]){
                    dist[r] = dist[n] + len(n,r);
                    prev[r] = n;
                } 
            }
        }
        PathResult result = Dijkstra.search(A, B, true);
        Node next = result.nextNode;
        double needed = result.cost;
        */