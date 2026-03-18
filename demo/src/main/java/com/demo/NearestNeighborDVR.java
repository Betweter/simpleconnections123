package com.demo;

import java.util.*;

public class NearestNeighborDVR {

    public static Result solve(
        Node start,
        Node end,
        List<Node> toVisit,
        Graph graph,
        boolean useTime,
        RoadUpdate updater
    ) {
        List<DVRframe> frames = new ArrayList<>();
        Node current = start;
        System.out.println("DVR starts...\n");

        List<Node> route = new ArrayList<>();
        route.add(start);

        double totalCost = 0;
        boolean itsjoever = false;

        List<Node> remaining = new ArrayList<>(toVisit);
        remaining.remove(end);

        frames.add(new DVRframe(start, route, remaining, graph, null, 0));

        while (!remaining.isEmpty()) {

            Node bestNode = null;
            Node nextHop = null;
            double bestCost = Double.POSITIVE_INFINITY;
            List<Node> path = null;

            for (Node candidate : remaining) {

                PathResult r = Dijkstra.search(current, candidate, useTime);

                if (r.cost < bestCost) {
                    bestCost = r.cost;
                    bestNode = candidate;
                    nextHop = r.nextNode;
                    path = r.path;
                }
                if (bestNode == null || bestCost == Double.POSITIVE_INFINITY) {
                    System.out.println("No reachable path to remaining destinations.");
                    throw new IllegalStateException("Graph became disconnected due to road events.");
                }
            }

            double nextCost = Double.POSITIVE_INFINITY;
            
            for (Road r : current.roads) {
                if (r.other(current).equals(nextHop)) {
                    nextCost = useTime ? r.travelTime() : r.distance;
                    break;
                }
            }
            if(current.equals(nextHop))
                nextCost = 0;

            System.out.println("koszt: " + totalCost);
            totalCost += nextCost;
            current = nextHop;

            System.out.println("\nUpdatin the road, current - " + current.name);

            route.add(nextHop);
            if(nextHop == bestNode)
                remaining.remove(nextHop);
            updater.update(nextCost);
            if(remaining.isEmpty() && !itsjoever){
                remaining.add(end);
                itsjoever = true;
            }
            
            frames.add(new DVRframe(current, route, remaining, graph.copy(), path, totalCost));
            if(current == end && itsjoever)
                break;
        }

        updater.resetAllLimits();
        return new Result(route, totalCost, frames);
    }
}
