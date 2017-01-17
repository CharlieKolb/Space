package main;

public class LeftShootCommand implements Command {

    public void execute(Actor actor) {
        actor.moveLeft();
        actor.shoot();
    }
}