package com.demo;

class PathResult {
    Node nextNode;
    double cost;

    PathResult(Node nextNode, double cost) {
        this.nextNode = nextNode;
        this.cost = cost;
    }
}
