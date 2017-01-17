package main;

public class RightShootCommand implements Command {

    public void execute(Actor actor) {
        actor.moveRight();
        actor.shoot();
    }
}