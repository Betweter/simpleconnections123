package com.demo;
import java.util.Random;
import java.util.Set;

public class RoadUpdate {

    double current_hour; //0-23
    double new_hour;
    Graph graph;
    Random rand = new Random();

    public RoadUpdate(Graph graph, double current_hour, double new_hour) {
        this.graph = graph;
        this.current_hour = current_hour;
        this.new_hour = new_hour;
    }

    public void update() {
        resetAllLimits();
        applyTrafficJams();
        applyRandomEvents();
    }

    private void resetAllLimits() {
        for (Road r : graph.roads)
            r.reset();
    }

    private void applyTrafficJams() {

    Set<Integer> specialNodes = Set.of(0,1,4,5);

        boolean lightJam =
                (new_hour >= 6 && new_hour < 7) || (new_hour >= 8 && new_hour < 9) ||
                (new_hour >= 12 && new_hour < 13) || (new_hour >= 15 && new_hour < 16) ||
                (new_hour >= 17 && new_hour < 18);
        boolean heavyJam =
                (new_hour >= 7 && new_hour < 8) || (new_hour >= 14 && new_hour < 15) ||
                (new_hour >= 16 && new_hour < 17);

        if (!lightJam && !heavyJam) return;

        for (Road road : graph.roads) {
            if (!specialNodes.contains(road.a.id) &&
                !specialNodes.contains(road.b.id))
                continue;

            double newLimit = road.baseLimit;

            if (lightJam) {
                int decrease = rand.nextInt(6) + 5;
                newLimit = road.baseLimit - decrease;
            } if (heavyJam) {
                int decrease = rand.nextInt(14) + 7;
                newLimit = road.baseLimit - decrease;
            } if (newLimit < 5)
                newLimit = 5;

            road.changeLimit(newLimit);
        }
    }

    private void applyRandomEvents() {

        for (Road road : graph.roads) {

            int chance = rand.nextInt(100);
            if (chance < 10) {

                road.changeLimit(0);
                System.out.println("Car crash on road "
                        + road.a.id + " <-> " + road.b.id);
            } else if (chance < 30) {
                double newLimit = road.baseLimit - 10;

                if (newLimit < 15)
                    newLimit = 15;

                road.changeLimit(newLimit);
                System.out.println("Fog on road "
                        + road.a.id + " <-> " + road.b.id);
            }
        }
    }
}

    /*
    on update 1) reset all limits
    
    for all edges connected to nodes {0,1,4,5}:
    6-7;8-9;13-14;15-16;17-18 light traffic jams (random between 5 and 10)
    7-8;14-15;16-17 heavy traffic jams (random between 7 and 20)
    
    additionaly for all edges in the graph:
    a random chance for an event (limitation decreases and the message is printed)
    10% for car crash (set limit to 0)
    20% for fog (limit =-10, but not less than 15)

    Graph graph = new Graph();
    RoadUpdate updater = new RoadUpdate(graph, 6, 7);
    updater.update();
    */