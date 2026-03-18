package com.graphics;

import java.util.*;
import java.util.List;

import javax.swing.*;

import com.demo.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GraphPanel extends JPanel {

    private Graph graph;
    private Graph oggraph;
    private final Map<Node, Point> positions = new HashMap<>();
    private Node vehicleNode = null;
    private List<Node> stops = new ArrayList<>();
    private Node start = null;
    private Node finish = null;
    private int state = 2;//0 - default, 1- choose stops, 2 - choose start, 3 - choose finish
    private static final int NODE_RADIUS = 10;
    private List<DVRframe> frames;
    private List<Node> path;
    private int frameIndex = 0;
    private double totalCost = 0;
    private String overlayText = "";
    

    public GraphPanel(Graph graph) {
        this.graph = graph;
        setBackground(Color.WHITE);
        createLayout();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Node n = findNode(e.getPoint());

                if (n != null) {
                    switch (state) {
                        case 1:
                            if (stops.contains(n))
                                stops.remove(n);
                            else
                                stops.add(n);
                            break;

                        case 2:
                            if (stops.contains(n)) {
                                if (!n.equals(start))
                                    start = n;
                                else
                                    start = null;
                            }
                            break;

                        case 3:
                            if (stops.contains(n)) {
                                if (!n.equals(finish))
                                    finish = n;
                                else
                                    finish = null;
                            }
                            break;
                    }
                    repaint();
                }
            }
        });
    }

    private void createLayout() {
        int w = 700;
        int h = 500;
        int cx = w / 10;
        int cy = h / 10;
        for (Node n : graph.nodes) {
            int x = (int) (cx + n.x);
            int y = (int) (cy + n.y);
            positions.put(n, new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        drawRoads(g2);
        drawNodes(g2);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(overlayText, 10, 20);
    }

    private void drawRoads(Graphics2D g2) {

        g2.setStroke(new BasicStroke(2));
        for (Road r : graph.roads) {

            Point a = positions.get(r.a);
            Point b = positions.get(r.b);

            double time = r.travelTime();
            double distance = r.distance;

            if (Double.isInfinite(time))
                g2.setColor(Color.RED);
            else
                g2.setColor(Color.GRAY);

            g2.drawLine(a.x, a.y, b.x, b.y);

            int mx = (a.x + b.x) / 2;
            int my = (a.y + b.y) / 2;

            String label = String.format("%.0f km; %.2f h", distance, time);

            g2.setColor(Color.BLACK);
            g2.drawString(label, mx + 4, my - 4);
        }
    }

    private void drawNodes(Graphics2D g2) {

        for (Node n : graph.nodes) {

            Point p = positions.get(n);

            g2.setColor(Color.BLUE);
            if(path != null)
                if(path.contains(n))
                    g2.setColor(Color.CYAN);
            if(stops != null)
                if(stops.contains(n))
                    g2.setColor(Color.ORANGE);
            if(n.equals(start))
                g2.setColor(Color.GREEN);
            if(n.equals(finish))
                g2.setColor(Color.MAGENTA);
            if(n.equals(vehicleNode))
                g2.setColor(Color.RED);

            g2.fillOval(
                    p.x - NODE_RADIUS,
                    p.y - NODE_RADIUS,
                    NODE_RADIUS * 2,
                    NODE_RADIUS * 2
            );

            g2.setColor(Color.BLACK);

            g2.drawString(
                    n.name,
                    p.x + NODE_RADIUS,
                    p.y - NODE_RADIUS
            );
        }
    }

    private Node findNode(Point click) {

        for (Node n : graph.nodes) {

            Point p = positions.get(n);

            double d = click.distance(p);

            if (d <= NODE_RADIUS)
                return n;
        }

        return null;
    }

    public void setVehicleNode(Node node) {

        this.vehicleNode = node;

        repaint();
    }
    
    public void setState(int state) {
        this.state = state;
    }

    public void load(){
        this.state = 0;
        this.oggraph = this.graph.copy();

        if(start == null || finish == null || stops == null || stops.isEmpty()){
            System.out.println("coś nie pykło");
            return;
        }
        
        RoadUpdate updater = new RoadUpdate(graph, 8, 8);
        
        Result result = null;

        try {
            result = NearestNeighborDVR.solve(start, finish, stops, graph, true, updater);

            this.frames = result.frames;
            showFrame(frameIndex);
            repaint(); 
        
        } catch(IllegalStateException e){
            JOptionPane.showMessageDialog(
                null,
                "Route cannot be completed.\n" + e.getMessage(),
                "Routing error",
                JOptionPane.ERROR_MESSAGE
            );
            this.stops = new ArrayList<>();
            start = null;
            finish = null;
            frameIndex = 0;
            this.graph = oggraph;
            repaint();
        }    
    }  

    private void showFrame(int index){

        if(frames == null || index < 0 || index >= frames.size())
            return;

        DVRframe f = frames.get(index);
        
        if(index+1 < frames.size()){
            DVRframe f1 = frames.get(index +1);
            this.path = f1.path;
        }else this.path = null;

        setVehicleNode(f.vehicle);
        this.graph = f.graph;
        this.stops = f.remaining;
        this.totalCost = f.totalCost;

        setOverlayText(
            String.format(
                "Frame: %d/%d, Cost: %.2f h",
                frameIndex,
                frames.size() - 1,
                this.totalCost
            )
        );

        repaint();
    }

    public void nextFrame(){

        if(frames == null) return;

        if(frameIndex < frames.size() - 1){
            frameIndex++;
            showFrame(frameIndex);
        }
    }
    public void prevFrame(){

        if(frames == null) return;

        if(frameIndex > 0){
            frameIndex--;
            showFrame(frameIndex);
        }
    }
    public void resetView(){
        this.graph = oggraph;
        setVehicleNode(null);
        start = null;
        finish = null;
        stops = new ArrayList<>();
        frames = null;
        path = null;
        frameIndex = 0;
        totalCost = 0;

        repaint();
    }

    public void setOverlayText(String text){
        this.overlayText = text;
        repaint();
    }
}
