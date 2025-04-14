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
			Environment environment = new Environment(0, 0, 1280, 780, "/images/environment/deathStar.png");
			Agent chonBota = new Agent(400, 390, 90, 65, 2, "/images/agents/lukeSkywalker.png");
			Agent chonBot1 = new Agent(920, 440, 90, 65, 1, "/images/agents/darthVader.png");
			Agent chonBot2 = new Agent(520, 440, 90, 65, 1, "/images/agents/darthVader.png");
			Agent chonBot3 = new Agent(120, 440, 90, 65, 1, "/images/agents/darthVader.png");
			Agent chonBot4 = new Agent(220, 440, 90, 65, 1, "/images/agents/darthVader.png");

			environment.setProtagonist(chonBota);
			environment.getAgents().add(chonBot1);
			environment.getAgents().add(chonBot2);
			environment.getAgents().add(chonBot3);
			environment.getAgents().add(chonBot4);


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
						environment.getProtagonist().move(input);
						environment.checkBorders();
					} 
					/* ChonBot's Automatic Movements */

					environment.getAgents().get(0).chase(environment.getProtagonist().getPosX(),
							environment.getProtagonist().getPosY());
					environment.getAgents().get(1).chase(environment.getProtagonist().getPosX(),
							environment.getProtagonist().getPosY());
					environment.getAgents().get(2).chase(environment.getProtagonist().getPosX(),
							environment.getProtagonist().getPosY());
					environment.getAgents().get(3).chase(environment.getProtagonist().getPosX(),
							environment.getProtagonist().getPosY());
							
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