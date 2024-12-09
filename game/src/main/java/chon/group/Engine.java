package chon.group;

import java.util.ArrayList;

import chon.group.game.domain.agent.Agent;
import chon.group.game.domain.environment.Environment;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class Engine extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage theStage) {
		try {
			Environment environment = new Environment(0, 0, 1280, 780, "/images/environment/castle.png");
			Agent chonBota = new Agent(400, 390, 90, 65, 20, "/images/agents/chonBota.png");
			Agent chonBot = new Agent(920, 440, 90, 65, 10, "/images/agents/chonBot.png");
			environment.setProtagonist(chonBota);
			environment.getAgents().add(chonBot);

			Canvas canvas = new Canvas(environment.getWidth(), environment.getHeight());
			GraphicsContext gc = canvas.getGraphicsContext2D();
			environment.setGc(gc);

			StackPane root = new StackPane();
			Scene scene = new Scene(root, environment.getWidth(), environment.getHeight());
			theStage.setTitle("Chon: The Learning Game");
			theStage.setScene(scene);

			root.getChildren().add(canvas);
			theStage.show();

			ArrayList<String> input = new ArrayList<String>();
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code = e.getCode().toString();
					input.clear();

					System.out.println("Pressed: " + code);
					input.add(code);
				}
			});

			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code = e.getCode().toString();
					System.out.println("Released: " + code);
					input.remove(code);
				}
			});

			new AnimationTimer() {

				@Override
				public void handle(long arg0) {
					/* ChonBota Only Moves if the Player Press Something */
					if (!input.isEmpty()) {
						/* ChonBota's Movements */
                        environment.getProtagonist().setUp("UP");
                        environment.getProtagonist().setDown("DOWN");
                        environment.getProtagonist().setLeft("LEFT");
                        environment.getProtagonist().setRight("RIGHT");

						environment.getProtagonist().move(input);
						environment.checkBorders();
					} 
                    environment.getAgents().get(0).setUp("W");
                    environment.getAgents().get(0).setDown("S");
                    environment.getAgents().get(0).setLeft("A");
                    environment.getAgents().get(0).setRight("D");
                    environment.getAgents().get(0).move(input);
					/* ChonBot's Automatic Movements */
					//environment.getAgents().get(0).chase(environment.getProtagonist().getPosX(),
					//		environment.getProtagonist().getPosY());
					/* Rendering Objects */
					environment.drawBackground();
					environment.drawAgents();
				}
			}.start();
			theStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
