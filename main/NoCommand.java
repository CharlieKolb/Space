package main;

public class NoCommand implements Command {

    public void execute(Actor actor) {
        actor.noCommand();
    }
}