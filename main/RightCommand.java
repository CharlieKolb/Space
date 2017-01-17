package main;

public class RightCommand implements Command {

    public void execute(Actor actor) {
        actor.noCommand();
        actor.moveRight();
    }
}