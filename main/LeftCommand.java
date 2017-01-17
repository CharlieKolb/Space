package main;

public class LeftCommand implements Command {

    public void execute(Actor actor) {
        actor.noCommand();
        actor.moveLeft();
    }
}