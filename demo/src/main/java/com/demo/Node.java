package com.demo;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int id;
    List<Road> roads = new ArrayList<>();

    Node(int id) {
        this.id = id;
    }
}
