package com.demo;

import java.util.List;

class Result {

    List<Node> route;
    double cost;

    Result(List<Node> route, double cost) {
        this.route = route;
        this.cost = cost;
    }
}
