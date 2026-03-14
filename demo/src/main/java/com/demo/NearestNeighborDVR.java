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

        List<Node> remaining = new ArrayList<>(toVisit);
        frames.add(new DVRframe(start, route, remaining, graph));

        while (!remaining.isEmpty()) {

            Node bestNode = null;
            Node nextHop = null;
            double bestCost = Double.POSITIVE_INFINITY;

            for (Node candidate : remaining) {

                PathResult r = Dijkstra.search(current, candidate, useTime);

                if (r.cost < bestCost) {
                    bestCost = r.cost;
                    bestNode = candidate;
                    nextHop = r.nextNode;
                }
                if (bestNode == null || bestCost == Double.POSITIVE_INFINITY) {
                    System.out.println("No reachable path to remaining destinations.");
                    throw new IllegalStateException("Graph became disconnected due to road events.");
                }
            }

            double nextCost = Double.POSITIVE_INFINITY;

            for (Road r : current.roads) {
                if (r.other(current) == nextHop) {
                    nextCost = useTime ? r.travelTime() : r.distance;
                    break;
                }
            }
            totalCost += nextCost;
            current = nextHop;

    System.out.println("\nUpdatin the road, current - " + current.name);

            route.add(nextHop);
            if(nextHop == bestNode)
                remaining.remove(nextHop);
            updater.update(nextCost);
            
            frames.add(new DVRframe(current, route, remaining, graph.copy()));
            if(current == end)
                break;
        }

        updater.resetAllLimits();
        return new Result(route, totalCost, frames);
    }
}
