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

    public void turnRight() {
        this.setDirection(this.getDirection() + 90);
    }
    public void turnLeft() {
        this.setDirection(this.getDirection() - 90);
    }

    public void moveForward() {
        this.getNextPos().ifPresent(this::moveTo);
    }
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
    public boolean isForwardClear() {
        Optional<Location> next = this.getNextPos();
        if(!next.isPresent()) {
            return false;
        }
        Actor neighbor = this.getGrid().get(next.get());
        return neighbor == null;
    }
    public boolean isForwardFlower() {
        return getFlowerInFront().isPresent();
    }
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
