package prove02;

import java.awt.*;

/**
 *  Wolf Eating Plants sit there and wait for wolves to come by, which they will attack.  Once they attack,
 *  they will spawn another wolf eating plant.  They are represented by red circles.
 * <p>
 * @author  Paul Humphreys
 * @version 1.0
 * @since   2021-1-15
 * @see Creature
 */
public class WolfEatingPlant extends Creature implements Aggressor, Spawner{


    boolean ableToSpawn;

    /**
     * Creates an Wolf Eating Plant with 1 health point,
     *  and an initial ability to spawn set to false.
     */
    public WolfEatingPlant() {
        _health = 1;
        this.ableToSpawn = false;
    }

    //Implements Aggressor
    @Override
    public void attack(Creature target) {
        // Zombies eating plants only eat Zombies. Gives 50 damage
        // and increase our health by one.
        if(target instanceof Wolf) {
            target.takeDamage(50);
            _health++;

            //Sets the ability to spawn to true
            this.ableToSpawn = true;
        }
    }

    //Extends Creature Class
    @Override
    Shape getShape() {
        return Shape.Circle;
    }

    @Override
    Color getColor() {
        return new Color(255,0,0);
    }

    @Override
    Boolean isAlive() {
        return _health > 0;
    }

    //Implements Spawner
    @Override
    public Creature spawnNewCreature() {
        if (this.ableToSpawn) {
            Wolf spawn = new Wolf();
            Point newLocation = new Point(this.getLocation().x - 1, this.getLocation().y);
            spawn.setLocation(newLocation);

            //Sets the ability to spawn to false, since it just spawned
            this.ableToSpawn = false;
            return spawn;
        } else {
            return null;
        }
    }
}
