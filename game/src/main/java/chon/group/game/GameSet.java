package chon.group.game;

import java.util.ArrayList;
import java.util.List;

import chon.group.game.core.agent.Agent;
import chon.group.game.core.agent.Object;
import chon.group.game.core.environment.Environment;
import chon.group.game.core.environment.Level;
import chon.group.game.core.weapon.CloseWeapon;
import chon.group.game.core.weapon.Panel;
import chon.group.game.core.weapon.Weapon;
import chon.group.game.domain.weapon.DL44;
import chon.group.game.domain.weapon.LightSaber;

public class GameSet {

    private int canvasWidth;
    private int canvasHeight;
    private Environment environment;
    private static  int character = 2; // 1 for Han Solo, 2 for Luke Skywalker
    private Panel panel;

    public static int getCharacter() {
        return character;
    }
    public static void setCharacter(int character) {
        GameSet.character = character;
    }
    public GameSet() {
        this.load();
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    private void load() {
        /* Define some size properties for both Canvas and Environment */
        this.canvasHeight = 1080;
        this.canvasWidth = 1920;

        /** Define a general panel for life, energy, points, and objects. */
        panel = new Panel(
                240,
                110);

        /* Initialize the game environment, levels, agents and weapons */
        Level level1 = new Level(
                0,
                0,
                canvasHeight ,
                3200,
                "/images/environment/insideOfTheDeathStar.png");

        Level level2 = new Level(
                0,
                0,
                canvasHeight,
                1920,
                "/images/environment/palpatinesRoom.png");

        environment = new Environment(
                this.canvasHeight,
                level1.getWidth(),
                this.canvasWidth,
                panel);


        if(character==1){
            Agent hansolo = new Agent(400, 390, 120, 85, 3, 1000, "/images/agents/hansolo.png", false, false);
            Weapon blaster = new DL44(400, 390, 0, 0, 3, 0, 0.05, "", false);
            hansolo.setWeapon(blaster);
            environment.setProtagonist(hansolo);
        } if(character==2){
           Agent luke = new Agent(400, 390, 120, 85, 5, 1000, "/images/agents/luke.png", false, false);
           CloseWeapon lightSaber = new LightSaber(400, 390, 0, 0, 3, 0, "", false);
           luke.setCloseWeapon(lightSaber);
           environment.setProtagonist(luke);
       }

        Agent stormTrooper1 = new Agent(520, 440, 90, 65, 1, 500, "/images/agents/stormtrooper.png", true, true);
        level1.getAgents().add(stormTrooper1);
        Agent stormTrooper2 = new Agent(920, 440, 90, 65, 1, 500, "/images/agents/stormtrooper.png", true, true);
        level1.getAgents().add(stormTrooper2);
        Agent stormTrooper3 = new Agent(1200, 440, 90, 65, 1, 500, "/images/agents/stormtrooper.png", true, true);
        level1.getAgents().add(stormTrooper3);

        Agent darthVader = new Agent(520, 440, 130, 65, 2, 1000, "/images/agents/darthVader.png", true, true);
       level2.getAgents().add(darthVader);




        environment.setPauseImage("/images/environment/pause.png");
        environment.setGameOverImage("/images/environment/gameover.png");



        /* Set up some collectable objects */
        List<Object> objects = new ArrayList<>();
        objects.add(new Object(200, 350, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(400, 380, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(1000, 600, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(1400, 380, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(1800, 650, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(2000, 580, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(2300, 380, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(2600, 500, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(2900, 380, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(3200, 400, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(4100, 500, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(5000, 380, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));
        objects.add(new Object(6200, 400, 32, 32, 0, 0, "/images/agents/coin.png", false, false, true, false));

        // Register objects into the environment and count total collectibles
        level1.setObjects(objects);
        level1.countCollectibles();
        environment.getLevels().add(level1);
        environment.getLevels().add(level2);
        environment.setCurrentLevel(level1);
    

    }

}