package com.demo;

public class Graph {
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

        Pilawin.addEdge(Lisowyje, 13, 10);
        Pilawin.addEdge(Paryzew, 12, 10);

        Paryzew.addEdge(Pilawin, 12, 6);
        Paryzew.addEdge(Borowiki, 8, 6);

        Lisowyje.addEdge(Pilawin, 13, 10);
        Lisowyje.addEdge(Gawronin, 10, 10);
        Lisowyje.addEdge(Kanielec, 11, 10);
        Lisowyje.addEdge(Sojka, 12, 10);

        Sojka.addEdge(Lisowyje, 12, 6);
        Sojka.addEdge(Kanielec, 20, 6);

        Gawronin.addEdge(Lisowyje, 10, 10);
        Gawronin.addEdge(Borowiki, 13, 10);
        Gawronin.addEdge(Trojkacie, 10, 10);

        Kanielec.addEdge(Lisowyje, 11, 6);
        Kanielec.addEdge(Sojka, 20, 6);
        Kanielec.addEdge(Maloklatkowice, 18, 6);
        Kanielec.addEdge(Trojkacie, 17, 6);

        Trojkacie.addEdge(Kanielec, 17, 10);
        Trojkacie.addEdge(Gawronin, 10, 10);
        Trojkacie.addEdge(Miasteczkowo, 10, 10);
        Trojkacie.addEdge(Zielechow, 14, 10);

        Maloklatkowice.addEdge(Kanielec, 18, 6);

        Zielechow.addEdge(Trojkacie, 14, 10);
        Zielechow.addEdge(Miasteczkowo, 12, 10);
        Zielechow.addEdge(Eltonowka, 15, 10);

        Eltonowka.addEdge(Zielechow, 15, 6);

        Miasteczkowo.addEdge(Borowiki, 8, 10);
        Miasteczkowo.addEdge(Trojkacie, 10, 10);
        Miasteczkowo.addEdge(Zielechow, 12, 10);

        Borowiki.addEdge(Paryzew, 8, 6);
        Borowiki.addEdge(Gawronin, 13, 6);
        Borowiki.addEdge(Miasteczkowo, 8, 6);
        
    }
}
