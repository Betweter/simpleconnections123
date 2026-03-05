package com.demo;

public class Road {
    Node a;
    Node b;

    double distance;
    double baseLimit;
    double currentLimit;

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

    double travelTime() {
        if (currentLimit == 0)
            return Double.POSITIVE_INFINITY;

        return distance / currentLimit;
    }
}
