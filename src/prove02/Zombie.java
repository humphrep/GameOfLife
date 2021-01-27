package prove02;

import java.awt.*;
import java.util.Random;

/**
 *  Zombies move from left to right, and eat anything except plants. They are represented by blue squares.
 * <p>
 * @author  Paul Humphreys
 * @version 1.0
 * @since   2021-1-15
 * @see Creature
 */
public class Zombie extends Creature implements Movable, Aggressor {

    /**
     * Creates an Zombie with 1 health point.
     */
    public Zombie() {
        _health = 1;
    }

    //Extends Creature
    @Override
    Shape getShape() {
        return Shape.Square;
    }

    @Override
    Color getColor() {
        return new Color(0, 0, 255);
    }

    @Override
    Boolean isAlive() {
        return _health > 0;
    }

    //Implements Aggressor
    @Override
    public void attack(Creature target) {
        // Zombies eat anything but plants. Give 10 damage
        // and increase our health by one.
        if((target != null) && !(target instanceof Plant)) {
            target.takeDamage(10);
            _health++;
        }
    }

    //Implements Movable
    @Override
    public void move() {
        //Zombies will only move from Left to Right
        _location.x++;

    }
}
