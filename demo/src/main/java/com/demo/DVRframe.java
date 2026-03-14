package com.demo;

import java.util.*;
import java.util.List;


public class DVRframe {

    public Node vehicle;
    public List<Node> visited = new ArrayList<>();
    public List<Node> remaining = new ArrayList<>();
    public Graph graph;

    public DVRframe(Node vehicle, List<Node> visited, List<Node> remaining, Graph graph) {
        this.vehicle = vehicle;
        this.visited = new ArrayList<>(visited);
        this.remaining = new ArrayList<>(remaining);
        this.graph = graph;
    }
}
