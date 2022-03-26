package cool;

import helper.SimpleBug;

public class CoolBug extends SimpleBug {
    @Override
    public void act() {
        if (this.isForwardMoveable()) {
            this.moveForward();
        } else {
            this.turnRight();
        }
    }
}
