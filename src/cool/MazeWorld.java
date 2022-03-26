package cool;

import helper.WorldBuilder;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class MazeWorld {

    public  static void main(String[] args){
        ActorWorld world = new ActorWorld(new BoundedGrid<>(30,30));
        world.add(new Location(1,0), new CoolBug());
        WorldBuilder.makaDaMaze(world);
        world.show();
    }
}
