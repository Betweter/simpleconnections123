package com.demo;

import java.util.*;


public class DVRframe {

    public Node vehicle;
    public List<Node> visited = new ArrayList<>();
    public List<Node> remaining = new ArrayList<>();
    public Graph graph;
    public List<Node> path;
    public double totalCost;

    public DVRframe(Node vehicle, List<Node> visited, List<Node> remaining, Graph graph, List<Node> path, double totalCost) {
        this.vehicle = vehicle;
        this.visited = new ArrayList<>(visited);
        this.remaining = new ArrayList<>(remaining);
        this.graph = graph;
        this.path = path;
        this.totalCost = totalCost;
    }
}
