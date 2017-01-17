package main;


public class InputHandler{
    //Commands to move left, right or shoot
    private Command noCommand;
    private Command vk_up;
    private Command vk_left;
    private Command vk_right;
    private Command vk_left_up;
    private Command vk_right_up;
    private Command vk_down;
    private Command vk_down_up;
    private Command vk_enter;
    private Command vk_escape;
    
    
    public InputHandler() {
        noCommand = new NoCommand();
        vk_up = new ShootCommand();
        vk_left = new LeftCommand();
        vk_right = new RightCommand();
        vk_left_up = new LeftShootCommand();
        vk_right_up = new RightShootCommand();
        vk_down = new DownCommand();
        vk_down_up = new DownShootCommand();
        vk_enter = new EnterCommand();
        vk_escape = new EscapeCommand();
    }
    
    /*
       gets Input from input as int, and depending on int returns Command to gameboard
       
       */
    public Command handleInput(int input) {
        switch(input) {
            case 0: return noCommand;
            case 1: return vk_left;
            case 2: return vk_right;
            case 3: return vk_up;
            case 4: return vk_right_up;
            case 5: return vk_left_up;
            case 6: return vk_down;
            case 7: return vk_down_up;
            case 8: return vk_enter;
            case 9: return vk_escape;
        
        }
        return null;
    }   
}