package main;

public class ShootCommand implements Command {
    
    public void execute(Actor actor) {
        actor.noCommand();
        actor.shoot();
    }
}