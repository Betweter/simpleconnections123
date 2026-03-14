package com.demo;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int id;
    public String name;
    public int x = 0;
    public int y = 0;

    List<Road> roads = new ArrayList<>();

    Node(int id) {
        this.id = id;
    }

    Node(int id, int x, int y, String name) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        return (o instanceof Node n) && n.id == id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
