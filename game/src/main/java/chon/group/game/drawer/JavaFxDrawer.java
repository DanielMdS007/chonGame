package chon.group.game.drawer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class JavaFxDrawer {

    /** The graphics context used to render the environment. */
    private final GraphicsContext gc;
    private final EnvironmentDrawer mediator;

    /**
     * Constructor to initialize the JavaFx Drawer.
     *
     * @param gc the GraphicsContext instance
     */
    public JavaFxDrawer(GraphicsContext gc, EnvironmentDrawer mediator) {
        this.gc = gc;
        this.mediator = mediator;
    }

    /**
     * Clears the canvas area, removing previously drawn elements.
     */
    public void clearScreen(int width, int height) {
        this.gc.clearRect(0, 0, width, height);
    }

    /**
     * Renders the environment's background on the graphics context.
     */
    public void drawImage(Image image, int posX, int posY, int width, int height) {
        this.gc.drawImage(image, posX, posY, width, height);
    }

    /**
     * Renders the Protagonist's Life Bar.
     */

    public void drawLifeBar(int health, int fullHealth, int width, int posX, int posY) {
        int borderThickness = 2;
        int barHeight = 5;
        int lifeSpan = Math.round((float) ((health * 100 / fullHealth) * width) / 100);
        int barY = 15; // altura padrão acima do agente
    
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(posX,
                posY - barY,
                width,
                barHeight + (borderThickness * 2));
    
        this.gc.setFill(Color.RED); // Cor da barra de vida
        this.gc.fillRect(posX + borderThickness,
                posY - (barY - borderThickness),
                (lifeSpan - (borderThickness * 2)),
                barHeight);
    }    

    public void drawEnergyBar(int energy, int fullEnergy, int width, int posX, int posY) {
        int borderThickness = 2;
        int barHeight = 5;
        int energySpan = Math.round((float) ((energy * 100 / fullEnergy) * width) / 100);
        
        // Menor deslocamento para posicionar logo abaixo da barra de vida
        int barY = 5;
        int offsetY = 5; // distância da barra de vida
    
        this.gc.setFill(Color.BLACK);
        this.gc.fillRect(posX,
                posY - barY + offsetY,
                width,
                barHeight + (borderThickness * 2));
    
        this.gc.setFill(Color.BLUE); // Cor da barra de energia
        this.gc.fillRect(posX + borderThickness,
                posY - (barY - borderThickness) + offsetY,
                (energySpan - (borderThickness * 2)),
                barHeight);
    }    

    /**
     * Displays a status panel showing the protagonist's coordinates.
     *
     * @param agent the protagonist whose information will be displayed
     */
    public void drawStatusPanel(int posX, int posY) {
        Font theFont = Font.font("Verdana", FontWeight.BOLD, 14);
        this.gc.setFont(theFont);
        this.gc.setFill(Color.BLACK);
        this.gc.fillText("X: " + posX, posX + 10, posY - 40);
        this.gc.fillText("Y: " + posY, posX + 10, posY - 25);
    }

    /**
     * Renders the Game Paused Screen.
     */
    public void drawPauseScreen(Image image, int imageWidth, int imageHeight, int width, int height) {
        if (image != null && this.gc != null) {
            double centerX = (width - imageWidth) / 2;
            double centerY = (height - imageHeight) / 2;
            /* Draw image on the center of screen */
            this.gc.drawImage(image, centerX, centerY);
        }
    }

}
