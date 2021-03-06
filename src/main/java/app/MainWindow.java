package app;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Slf4j
public class MainWindow extends JFrame {

	public static void main(String[] args) {
		new MainWindow();
	}

	public MainWindow() {
		log.debug("MainWindow called");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize( 1200, 600);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Display Multi Layer Perceptron");
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		add(new MainComponent());
		setVisible(true);
	}
}
