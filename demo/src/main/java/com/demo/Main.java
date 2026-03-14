package com.demo;
import java.awt.BorderLayout;

import javax.swing.*;

import com.graphics.GraphPanel;

public class Main {

	public static void main(String[] args) {
		
		Graph graph = new Graph();

		SwingUtilities.invokeLater(() -> {

			GraphPanel panel = new GraphPanel(graph);

			JButton stopsBtn = new JButton("Stops");
			JButton startBtn = new JButton("Start");
			JButton finishBtn = new JButton("Finish");
			JButton loadBtn = new JButton("Load");

			stopsBtn.addActionListener(e -> panel.setState(1));
			startBtn.addActionListener(e -> panel.setState(2));
			finishBtn.addActionListener(e -> panel.setState(3));
			loadBtn.addActionListener(e -> panel.load());

			JPanel controls = new JPanel();
			controls.add(stopsBtn);
			controls.add(startBtn);
			controls.add(finishBtn);
			controls.add(loadBtn);

			JFrame frame = new JFrame();
			frame.setLayout(new BorderLayout());

			frame.add(panel, BorderLayout.CENTER);
			frame.add(controls, BorderLayout.SOUTH);

			frame.setSize(800,600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
        });
	}

}
