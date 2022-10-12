package main.game.process;

import javax.swing.JFrame;

import main.game.game.GameModel;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame("Legend of Hagmar Cove");
		window.setSize(1152, 648);
		window.setResizable(true);
		window.setLocation(50, 25);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameModel game = new GameModel("hagmarcove");
		GameViewer panel = new GameViewer(game, window);
		new GameController(game, panel);
		panel.setSize(window.getSize());
		window.setContentPane(panel);
		window.setVisible(true);
		panel.requestFocusInWindow();
	}
}
