package com.demo;

public class Road {
    public Node a;
    public Node b;

    public double distance;
    public double baseLimit;
    public double currentLimit;

    Road(Node a, Node b, double distance, double limit) {
        this.a = a;
        this.b = b;
        this.distance = distance;
        this.baseLimit = limit;
        this.currentLimit = limit;
    }

    Node other(Node n) {
        return (n == a) ? b : a;
    }

    void reset() {
        currentLimit = baseLimit;
    }

    void changeLimit(double newLimit) {
        currentLimit = newLimit;
    }

    public double travelTime() {
        if (currentLimit == 0)
            return Double.POSITIVE_INFINITY;

        return distance / currentLimit;
    }
}
