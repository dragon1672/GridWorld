package cool;

import helper.WorldBuilder;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MazeWorld {

    public  static void main(String[] args) throws IOException {
        ActorWorld world = new ActorWorld(new UnboundedGrid<>());
        world.add(new Location(1,0), new CoolBug());
        /*
        WorldBuilder.makaDaMaze(world);
        /*/
        BufferedImage img = ImageIO.read(
                //new File("smallmaze.png")
                new File("big-maze-solved.png")
        );
        WorldBuilder.makeMazeFromFile(world, img, new Color(0, 0, 0));
        //*/
        world.show();
    }
}
