package prove02;


/**
 *  Spawner creatures have the ability to spawn.
 * <p>
 * @author  Paul Humphreys
 * @version 1.0
 * @since   2021-01-15
 * @see Creature
 */
public interface Spawner {

    /**
     * Spawns a new {@link Creature}.
     */
    public Creature spawnNewCreature();

}
