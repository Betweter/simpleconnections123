package com.demo;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    List<Node> nodes = new ArrayList<>();
    List<Road> roads = new ArrayList<>();

    void connect(Node a, Node b, double distance, double limit) {

        Road r = new Road(a, b, distance, limit);

        roads.add(r);

        a.roads.add(r);
        b.roads.add(r);
    }

    Graph(){
        Node Pilawin = new Node(0);
        Node Paryzew = new Node(1);
        Node Lisowyje = new Node(2);
        Node Sojka = new Node(3);
        Node Gawronin = new Node(4);
        Node Kanielec = new Node(5);
        Node Trojkacie = new Node(6);
        Node Maloklatkowice = new Node(7);
        Node Zielechow = new Node(8);
        Node Eltonowka = new Node(9);
        Node Miasteczkowo = new Node(10);
        Node Borowiki = new Node(11);

        nodes.add(Pilawin);
        nodes.add(Paryzew);
        nodes.add(Lisowyje);
        nodes.add(Sojka);
        nodes.add(Gawronin);
        nodes.add(Kanielec);
        nodes.add(Trojkacie);
        nodes.add(Maloklatkowice);
        nodes.add(Zielechow);
        nodes.add(Eltonowka);
        nodes.add(Miasteczkowo);
        nodes.add(Borowiki);

        connect(Pilawin, Lisowyje, 13, 30);
        connect(Pilawin, Paryzew, 12, 30);

        connect(Paryzew, Borowiki, 8, 30);

        connect(Gawronin, Lisowyje, 10, 40);
        connect(Kanielec, Lisowyje, 11, 30);
        connect(Sojka, Lisowyje, 12, 30);

        connect(Sojka, Kanielec, 20, 32);

        connect(Gawronin, Borowiki, 13, 40);
        connect(Gawronin, Trojkacie, 10, 40);

        connect(Kanielec, Maloklatkowice, 18, 15);
        connect(Kanielec, Trojkacie, 17, 30);

        connect(Trojkacie, Miasteczkowo, 10, 30);
        connect(Trojkacie, Zielechow, 14, 30);


        connect(Zielechow, Miasteczkowo, 12, 30);
        connect(Zielechow, Eltonowka, 15, 15);

        connect(Miasteczkowo, Borowiki, 8, 40);
    }
}
