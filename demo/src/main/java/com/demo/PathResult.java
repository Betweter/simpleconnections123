package com.demo;

import java.util.List;

class PathResult {
    Node nextNode;
    double cost;
    List<Node> path;

    PathResult(Node nextNode, double cost, List<Node> path) {
        this.nextNode = nextNode;
        this.cost = cost;
        this.path = path;
    }
}
