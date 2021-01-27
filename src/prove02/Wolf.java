package prove02;

import java.awt.*;
import java.util.Random;

import static prove02.Direction.*;

/**
 *  Wolves roam around in a random initial direction, they are able to sense other animals and
 *   will change their direction to move towards an animal, which they will attack.  Once they attack,
 *  they will spawn another wolf.  They are represented by grey squares.
 * <p>
 * @author  Paul Humphreys
 * @version 1.0
 * @since   2021-1-15
 * @see Creature
 */
public class Wolf extends Creature implements Movable, Aware, Aggressor, Spawner {

    Direction preferredDirection;
    boolean ableToSpawn;

    /**
     * Creates an wolf with 1 health point.
     */
    public Wolf() {

        this.preferredDirection = getPreferredDirection();
        _health = 1;
        this.ableToSpawn = false;
    }

    /**
     * Defines the direction the Wolf will move unless something else
     * changes it.
     * @return The preferred direction of the Wolf
     */
    private Direction getPreferredDirection() {
        Random rand = new Random();
        int direction;
        direction = rand.nextInt(4);

        switch(direction) {
            case 0:
                return Right;

            case 1:
                return Left;

            case 2:
                return Up;

            case 3:
                return Down;

            default:
                return null;

        }
    }

    //Implements Aggressor
    @Override
    public void attack(Creature target) {
        // Wolves only eat animals. Give the animal 5 points damage
        // and increase our health by one. Also enable the wolf to spawn.
        if(target instanceof Animal) {
            target.takeDamage(5);
            _health++;
            this.ableToSpawn = true;
        }
    }

    //Implements Aware
    @Override
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
       // Set the preferred direction based on the first Animal found in adjacent cells
        switch(this.preferredDirection) {
            case Right:
                if (!(right instanceof Animal)){
                     if (below instanceof Animal){
                         this.preferredDirection = Down;
                     } else if (left instanceof Animal) {
                         this.preferredDirection = Left;
                     } else if (above instanceof Animal) {
                         this.preferredDirection = Up;
                     }
                }
                break;
            case Left:
                if (!(left instanceof Animal)){
                    if (above instanceof Animal){
                        this.preferredDirection = Up;
                    } else if (right instanceof Animal) {
                        this.preferredDirection = Right;
                    } else if (below instanceof Animal) {
                        this.preferredDirection = Down;
                    }
                }
                break;
            case Up:
                if (!(above instanceof Animal)){
                    if (right instanceof Animal){
                        this.preferredDirection = Right;
                    } else if (below instanceof Animal) {
                        this.preferredDirection = Down;
                    } else if (left instanceof Animal) {
                        this.preferredDirection = Left;
                    }
                }
                break;
            case Down:
                if (!(below instanceof Animal)){
                    if (left instanceof Animal){
                        this.preferredDirection = Left;
                    } else if (above instanceof Animal) {
                        this.preferredDirection = Up;
                    } else if (right instanceof Animal) {
                        this.preferredDirection = Right;
                    }
                }
                break;
            default:
                break;
        }

    }

    //Implements Creature
    @Override
    Shape getShape() {
        return Shape.Square;
    }

    @Override
    Color getColor() {
        return new Color(153,153,153);
    }

    @Override
    Boolean isAlive() {
        return _health > 0;
    }

    //Extends Movable
    @Override
    public void move() {
        // Move in the preferred direction.
        switch(this.preferredDirection) {
            case Right:
                _location.x++;
                break;
            case Left:
                _location.x--;
                break;
            case Up:
                _location.y--;
                break;
            case Down:
                _location.y++;
                break;
            default:
                break;
        }
    }

    //Extends Spawner
    @Override
    public Creature spawnNewCreature() {
        if (this.ableToSpawn) {
            Wolf spawn = new Wolf();
            Point newLocation = new Point(this.getLocation().x - 1, this.getLocation().y);
            spawn.setLocation(newLocation);
            this.ableToSpawn = false;
            return spawn;
        } else {
            return null;
        }
    }
}
