package com.demo;
public class Edge {
    Node from;
    Node to;
    double distance;
    double time;
    double limit;

    Edge(Node from, Node to, double distance, double limit) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.limit = limit;
        resetTime();
    }

    void updateTime(double newlimit){
        time = newlimit/distance;
    }

    void resetTime(){
        time = limit/distance;
    }
}
