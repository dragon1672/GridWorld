package org.learning.gridworld;

import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

public class Test {
    public  static void main(String[] args){
        ActorWorld world = new ActorWorld(new UnboundedGrid<>());
        world.add(new Location(0,0), new Bug());
        world.add(new Location(0,10), new Flower());
        world.add(new Location(0, 11), new Rock());
        world.show();
    }
}
