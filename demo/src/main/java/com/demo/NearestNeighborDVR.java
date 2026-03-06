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

        Node current = start;

        List<Node> route = new ArrayList<>();
        route.add(start);

        double totalCost = 0;

        List<Node> remaining = new ArrayList<>(toVisit);

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

            route.add(nextHop);
            if(nextHop == bestNode)
                remaining.remove(nextHop);
            updater.update();
        }

        PathResult finalPath = Dijkstra.search(current, end, useTime);

        totalCost += finalPath.cost;
        route.add(end);

        return new Result(route, totalCost);
    }
}
/*
    the starting point, the endpoint end the list of nodes to visit is provided
    we keep the list of nodes to visit and the final one.
    by Dijkstra we compute the cost of visiting each one and choose the lowest.
    We go to the lowest one, taking it off the todo list.
    after every hop we have to update the values of time.
    Then we recompute costs for remaining nodes.
    
    After clearing the list, we go to the final destination.
    The distance and time are kept. Depending on the useTime value, Dijkstra can go by distance or time.
    This way we can emulate, a postman's gps for example.
*/