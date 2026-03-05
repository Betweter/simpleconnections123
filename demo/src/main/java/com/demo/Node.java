package com.demo;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int id;
    List<Edge> edges = new ArrayList<>();

    Node(int id) {
        this.id = id;
    }

    public void addEdge(Node to, double distance, double limit) {
        edges.add(new Edge(this, to, distance, limit));
    }
}
