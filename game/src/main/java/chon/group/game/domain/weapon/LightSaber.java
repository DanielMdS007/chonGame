package chon.group.game.domain.weapon;

import java.util.List;

import chon.group.game.core.weapon.CloseWeapon;
import chon.group.game.core.weapon.Slash;
import chon.group.game.messaging.Message;

public class LightSaber extends CloseWeapon {

    public LightSaber(int posX, int posY, int height, int width, int speed, int health, String pathImage,
            boolean flipped) {
        super(posX, posY, height, width, speed, health, pathImage, flipped);
    }

    @Override
    protected Slash createSlash(int posX, int posY, String direction) {
        if (direction.equals("RIGHT"))
            posX += 55 + 10;
        else
            posX -= 55 + 10;
        return new SaberSlash(posX,
                posY,
                100,
                100,
                3,
                0,
                "/images/weapons/lightsaber/saberattack.png",
                false,
                200,
                direction);
    }
    @Override
    public void takeDamage(int damage, List<Message> messages) {

    }

}
