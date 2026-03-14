package com.demo;

import java.util.List;

public class Result {

    public List<Node> route;
    public double cost;
    public List<DVRframe> frames;

    public Result(List<Node> route, double cost, List<DVRframe> frames) {
        this.route = route;
        this.cost = cost;
        this.frames = frames;
    }
}
