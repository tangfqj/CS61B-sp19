package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import javax.naming.directory.DirContext;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;
public class Clorus extends  Creature{
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Cloruses should always return the color red = 34, green = 0, blue = 231
     */
    @Override
    public Color color() {
        return color(r, g, b);
    }

    /**
     * If a Clorus attacks another creature, it should gain that creature's energy.
     * You don't need to worry about making sure the attacked creature dies.
     */
    @Override
    public void attack(Creature c) {
        // do nothing.
        energy += c.energy();
    }

    /**
     * Clorus should lose 0.30 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    @Override
    public void move() {
        // TODO
        energy -= 0.30;
        if(energy < 0){
            energy = 0;
        }
    }


    /**
     * Clorus lose 0.01 energy when staying due to photosynthesis.
     */
    @Override
    public void stay() {
        // TODO
        energy -= 0.01;
        if(energy < 0){
            energy = 0;
        }
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    @Override
    public Plip replicate() {
        energy = 0.5 * energy;
        Plip offSpring = new Plip(energy);
        return offSpring;
    }

    @Override
    public String name(){
        return super.name();
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for(Direction key : neighbors.keySet()){
            if(neighbors.get(key).name().equals("empty")){
                emptyNeighbors.add(key);
            } else if(neighbors.get(key).name().equals("plip")){
                plipNeighbors.add(key);
            }
        }
        if(emptyNeighbors.size() == 0){
            return new Action(Action.ActionType.STAY);
        } else if(plipNeighbors.size() > 0){
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        } else if(energy >= 1.0){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else{
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }
}
