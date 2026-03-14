package com.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    public List<Node> nodes = new ArrayList<>();
    public List<Road> roads = new ArrayList<>();

    void connect(Node a, Node b, double distance, double limit) {

        Road r = new Road(a, b, distance, limit);

        roads.add(r);

        a.roads.add(r);
        b.roads.add(r);
    }

    Graph(){
        Node Pilawin = new Node(0, 200, 5, "Pilawin");
        Node Paryzew = new Node(1, 380, 6, "Paryżew");
        Node Lisowyje = new Node(2, 20 , 20, "Lisowyje");
        Node Sojka = new Node(3, 20, 200, "Sójka");
        Node Gawronin = new Node(4, 150, 150, "Gawronin");
        Node Kanielec = new Node(5, 100, 270, "Kanielec");
        Node Trojkacie = new Node(6, 200, 200, "Trójkącie");
        Node Maloklatkowice = new Node(7, 40, 400, "Małoklatkowice");
        Node Zielechow = new Node(8, 350, 350, "Zielechów");
        Node Eltonowka = new Node(9, 400, 400, "Eltonówka");
        Node Miasteczkowo = new Node(10, 420, 180, "Miasteczkowo");
        Node Borowiki = new Node(11, 400, 70, "Borowiki");

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

    Node getNode(int id){
        Node node = null;
        for(Node n : nodes)
            if(id == n.id){
                node = n;
                break;
            }
        return node;
    }

    public Graph copy() {
        Graph g = new Graph();

        g.nodes.clear();
        g.roads.clear();

        Map<Node, Node> map = new HashMap<>();

        for (Node n : this.nodes) {
            Node nn = new Node(n.id);
            nn.name = n.name;
            nn.x = n.x;
            nn.y = n.y;

            map.put(n, nn);
            g.nodes.add(nn);
        }

        for (Road r : this.roads) {

            Node a = map.get(r.a);
            Node b = map.get(r.b);

            Road nr = new Road(a, b, r.distance, r.baseLimit);
            nr.currentLimit = r.currentLimit;

            g.roads.add(nr);

            a.roads.add(nr);
            b.roads.add(nr);
        }

        return g;
    }
}
