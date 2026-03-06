package com.demo;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int id;
    List<Road> roads = new ArrayList<>();

    Node(int id) {
        this.id = id;
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
