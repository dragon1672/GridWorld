package helper;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.Optional;
import java.util.Random;

public class SimpleBug extends Actor {
    private static final Random rand = new Random();
    public SimpleBug() {
        this.setColor(Color.RED);
    }

    @Override
    public void act() {}

    /** Rotate Right */
    public void turnRight() {
        this.setDirection(this.getDirection() + 90);
    }
    /** Rotate left */
    public void turnLeft() {
        this.setDirection(this.getDirection() - 90);
    }

    /** Attempt to move forward. Does nothing if cannot move */
    public void moveForward() {
        this.getNextPos().ifPresent(this::moveTo);
    }
    /** Plants a flower infront. Will break if there is something already there */
    public void plantFlowerInFront(int num) {
        Optional<Location> next = getNextPos();
        if(next.isPresent()) {
            if (getGrid().get(next.get()) != null) {
                throw new IllegalArgumentException("Cannot plant a flower on existing thing");
            }
            Flower flower = new Flower(new Color(num, true));
            flower.putSelfInGrid(this.getGrid(), next.get());
        }
    }
    /** Will optional with flower */
    public Optional<Flower> getFlowerInFront() {
        Optional<Location> next = this.getNextPos();
        if(!next.isPresent()) {
            return Optional.empty();
        }
        Actor neighbor = this.getGrid().get(next.get());
        if (neighbor instanceof Flower) {
            return Optional.of((Flower)neighbor);
        }
        return Optional.empty();
    }
    /** Will return null if there is no flower */
    public Integer getFlowerInFrontValue() {
        Optional<Location> next = this.getNextPos();
        if(!next.isPresent()) {
            return null;
        }
        Actor neighbor = this.getGrid().get(next.get());
        if (neighbor instanceof Flower) {
            return neighbor.getColor().getRGB();
        }
        return null;
    }
    /** check if the front has nothing in it */
    public boolean isForwardClear() {
        Optional<Location> next = this.getNextPos();
        if(!next.isPresent()) {
            return false;
        }
        Actor neighbor = this.getGrid().get(next.get());
        return neighbor == null;
    }
    /** check if there is a flower infront */
    public boolean isForwardFlower() {
        return getFlowerInFront().isPresent();
    }
    /** check if you can move forward, (note you can trample over flowers) */
    public boolean isForwardMoveable() {
        return isForwardClear() || isForwardFlower();
    }

    private Optional<Location> getNextPos() {
        Grid<Actor> gr = this.getGrid();
        if (gr == null) {
            return Optional.empty();
        }
        Location loc = this.getLocation();
        Location next = loc.getAdjacentLocation(this.getDirection());
        if (!gr.isValid(next)) {
            return Optional.empty();
        }
        return Optional.of(next);
    }

    /**
     * Returns a random number between 0 -> maxExclusive.
     * @param maxExclusive the number of values to return
     * @return Returns a random number between 0 -> maxExclusive.
     */
    public int randomInt(int maxExclusive) {
        return rand.nextInt(maxExclusive);
    }
}
