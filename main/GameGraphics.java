package main;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GameGraphics {
    
    private static String player[];
    private static String playerMini[];
    private static String minion[];
    private static String hpBar[];
    private static String boss[];
    private static String projectile[];
    private static String explosion[];
    private static String background[];
    private static String hud[];
    private static String obstacles[];
    private static String pause[];
    
    private static String error;
    
    
    static ImageIcon[][] Icons;
    static Image image;
    
    /*
     * Loading all images
     */
    public GameGraphics() {
        error = "/main/Resources/Assets/Error/null.png";
        
        player = new String[20];
        player[1] = "/main/Resources/Assets/Player/player.png";
        player[6] = "/main/Resources/Assets/Player/player2.png";
        player[11] = "/main/Resources/Assets/Player/player3.png";
        player[16] = "/main/Resources/Assets/Player/player4.png";
        
        playerMini = new String[4];
        playerMini[0] = "/main/Resources/Assets/Player/player_mini.png";
        playerMini[1] = "/main/Resources/Assets/Player/player2_mini.png";
        playerMini[2] = "/main/Resources/Assets/Player/player3_mini.png";
        playerMini[3] = "/main/Resources/Assets/Player/player4_mini.png"; 
        
        
        minion = new String[25];
        minion[1] = "/main/Resources/Assets/Minion/minion01.png";
        minion[2] = "/main/Resources/Assets/Minion/minion02.png";
        minion[3] = "/main/Resources/Assets/Minion/minion03.png";
        minion[4] = "/main/Resources/Assets/Minion/minion04.png";
        minion[5] = "/main/Resources/Assets/Minion/minion05.png";
        
        minion[6] = "/main/Resources/Assets/Minion/minion11.png";
        minion[7] = "/main/Resources/Assets/Minion/minion12.png";
        minion[8] = "/main/Resources/Assets/Minion/minion13.png";
        minion[9] = "/main/Resources/Assets/Minion/minion14.png";
        minion[10] = "/main/Resources/Assets/Minion/minion15.png";
        
        minion[11] = "/main/Resources/Assets/Minion/minion21.png";
        minion[12] = "/main/Resources/Assets/Minion/minion22.png";
        minion[13] = "/main/Resources/Assets/Minion/minion23.png";
        minion[14] = "/main/Resources/Assets/Minion/minion24.png";
        minion[15] = "/main/Resources/Assets/Minion/minion25.png";
        
        minion[16] = "/main/Resources/Assets/Minion/minion31.png";
        minion[17] = "/main/Resources/Assets/Minion/minion32.png";
        minion[18] = "/main/Resources/Assets/Minion/minion33.png";
        minion[19] = "/main/Resources/Assets/Minion/minion34.png";
        minion[20] = "/main/Resources/Assets/Minion/minion35.png";
        
        hpBar = new String[3];
        hpBar[0] = "/main/Resources/Assets/HPBar/blackBar.png";
        hpBar[1] = "/main/Resources/Assets/HPBar/greenBar.png";
        hpBar[2] = "/main/Resources/Assets/HPBar/greyBar.png";
        
        boss = new String[20];
        boss[0] = "/main/Resources/Assets/Boss/boss01.png";
        boss[1] = "/main/Resources/Assets/Boss/boss02.png";
        boss[2] = "/main/Resources/Assets/Boss/boss03.png";
        boss[3] = "/main/Resources/Assets/Boss/boss04.png";
        boss[4] = "/main/Resources/Assets/Boss/boss05.png";
        boss[5] = "/main/Resources/Assets/Boss/boss06.png";
        boss[6] = "/main/Resources/Assets/Boss/boss07.png";
        boss[7] = "/main/Resources/Assets/Boss/boss08.png";
        boss[8] = "/main/Resources/Assets/Boss/boss09.png";
        //boss[10] = "/main/Resources/Assets/Boss/boss10.png";
        //boss[11] = "/main/Resources/Assets/Boss/boss11.png";
        
        projectile = new String[20];
        projectile[0] = "/main/Resources/Assets/Projectiles/laser00.png";
        projectile[1] = "/main/Resources/Assets/Projectiles/laser01.png";
        projectile[2] = "/main/Resources/Assets/Projectiles/laser01_despawn.png";
        projectile[3] = "/main/Resources/Assets/Projectiles/laser02.png";
        projectile[4] = "/main/Resources/Assets/Projectiles/laser02_despawn.png";
        projectile[5] = "/main/Resources/Assets/Projectiles/laser03.png";
        projectile[6] = "/main/Resources/Assets/Projectiles/laser03_despawn.png";
        projectile[7] = "/main/Resources/Assets/Projectiles/laser00.png";
        projectile[10] = "/main/Resources/Assets/Projectiles/rainbow1.png";
        projectile[11] = "/main/Resources/Assets/Projectiles/rainbow2.png";
        projectile[12] = "/main/Resources/Assets/Projectiles/rainbow3.png";
        projectile[13] = "/main/Resources/Assets/Projectiles/rainbow4.png";
        projectile[14] = "/main/Resources/Assets/Projectiles/rainbow5.png";
        projectile[15] = "/main/Resources/Assets/Projectiles/rainbow6.png";
        projectile[16] = "/main/Resources/Assets/Projectiles/rainbow7.png";
        projectile[17] = "/main/Resources/Assets/Projectiles/laser05.png";
        projectile[18] = "/main/Resources/Assets/Projectiles/laser06.png";
        projectile[19] = "/main/Resources/Assets/Projectiles/laser07.png";
        
        explosion = new String[3];
        explosion[0] = "/main/Resources/Assets/Explosions/explosion1.png";
        
        background = new String[8];
        background[1] = "/main/Resources/Assets/Background/background01.png";
        background[2] = "/main/Resources/Assets/Background/background02.png";
        background[3] = "/main/Resources/Assets/Background/background03.png";
        background[4] = "/main/Resources/Assets/Background/background04.png";
        background[5] = "/main/Resources/Assets/Background/background05.png";
        background[6] = "/main/Resources/Assets/Background/background06.png";
        background[7] = "/main/Resources/Assets/Background/background07.png";
        
        hud = new String[8];
        hud[0] = "/main/Resources/Assets/Hud/header.png";
        hud[1] = "/main/Resources/Assets/Hud/HP_0.png";
        hud[2] = "/main/Resources/Assets/Hud/HP_1.png";
        hud[3] = "/main/Resources/Assets/Hud/HP_2.png";
        hud[4] = "/main/Resources/Assets/Hud/HP_3.png";
        hud[5] = "/main/Resources/Assets/Hud/HP_4.png";
        hud[6] = "/main/Resources/Assets/Hud/hp_green.png";
        hud[7] = "/main/Resources/Assets/Hud/hp_grey.png";
        
        obstacles = new String[10];
        obstacles[0] = "/main/Resources/Assets/Obstacles/obstacle00.png";
        obstacles[1] = "/main/Resources/Assets/Obstacles/obstacle01.png";
        obstacles[2] = "/main/Resources/Assets/Obstacles/obstacle01_destroyed01.png";
        obstacles[3] = "/main/Resources/Assets/Obstacles/obstacle02.png";
        obstacles[4] = "/main/Resources/Assets/Obstacles/obstacle02_destroyed01.png";
        
        pause = new String[8];
        
        pause[0] = "/main/Resources/Assets/Pause/pauseBackground.png";
        pause[1] = "/main/Resources/Assets/Pause/pauseButton.png";
        pause[2] = "/main/Resources/Assets/Pause/pauseButtonCurrent.png";
        pause[3] = "/main/Resources/Assets/Pause/pauseConfirm.png";
        pause[4] = "/main/Resources/Assets/Pause/pauseConfirmNoOff.png";
        pause[5] = "/main/Resources/Assets/Pause/pauseConfirmNoOn.png";
        pause[6] = "/main/Resources/Assets/Pause/pauseConfirmYesOff.png";
        pause[7] = "/main/Resources/Assets/Pause/pauseConfirmYesOn.png";
        
        Icons  = new ImageIcon[15][36];
        
        Icons[0][0] = new ImageIcon(this.getClass().getResource(error));
        Icons[0][1] = new ImageIcon(this.getClass().getResource(player[1]));
        Icons[0][6] = new ImageIcon(this.getClass().getResource(player[6]));
        Icons[0][11] = new ImageIcon(this.getClass().getResource(player[11]));
        Icons[0][16] = new ImageIcon(this.getClass().getResource(player[16]));
        
        Icons[1][1] = new ImageIcon(this.getClass().getResource(minion[1]));
        Icons[1][2] = new ImageIcon(this.getClass().getResource(minion[2]));
        Icons[1][3] = new ImageIcon(this.getClass().getResource(minion[3]));
        Icons[1][4] = new ImageIcon(this.getClass().getResource(minion[4]));
        Icons[1][5] = new ImageIcon(this.getClass().getResource(minion[5]));
        
        Icons[1][11] = new ImageIcon(this.getClass().getResource(minion[6]));
        Icons[1][12] = new ImageIcon(this.getClass().getResource(minion[7]));
        Icons[1][13] = new ImageIcon(this.getClass().getResource(minion[8]));
        Icons[1][14] = new ImageIcon(this.getClass().getResource(minion[9]));
        Icons[1][15] = new ImageIcon(this.getClass().getResource(minion[10]));
        
        Icons[1][21] = new ImageIcon(this.getClass().getResource(minion[11]));
        Icons[1][22] = new ImageIcon(this.getClass().getResource(minion[12]));
        Icons[1][23] = new ImageIcon(this.getClass().getResource(minion[13]));
        Icons[1][24] = new ImageIcon(this.getClass().getResource(minion[14]));
        Icons[1][25] = new ImageIcon(this.getClass().getResource(minion[15]));      
                
        Icons[1][31] = new ImageIcon(this.getClass().getResource(minion[16]));
        Icons[1][32] = new ImageIcon(this.getClass().getResource(minion[17]));
        Icons[1][33] = new ImageIcon(this.getClass().getResource(minion[18]));
        Icons[1][34] = new ImageIcon(this.getClass().getResource(minion[19]));
        Icons[1][35] = new ImageIcon(this.getClass().getResource(minion[20]));
        
        Icons[2][0] = new ImageIcon(this.getClass().getResource(boss[0]));
        Icons[2][1] = new ImageIcon(this.getClass().getResource(boss[1]));
        Icons[2][2] = new ImageIcon(this.getClass().getResource(boss[2]));
        Icons[2][3] = new ImageIcon(this.getClass().getResource(boss[3]));
        Icons[2][4] = new ImageIcon(this.getClass().getResource(boss[4]));
        Icons[2][5] = new ImageIcon(this.getClass().getResource(boss[5]));
        Icons[2][6] = new ImageIcon(this.getClass().getResource(boss[6]));
        Icons[2][7] = new ImageIcon(this.getClass().getResource(boss[7]));
        Icons[2][8] = new ImageIcon(this.getClass().getResource(boss[8]));
        //Icons[2][9] = new ImageIcon(this.getClass().getResource(boss[9]));
        //Icons[2][10] = new ImageIcon(this.getClass().getResource(boss[10]));
        
        Icons[3][0] = new ImageIcon(this.getClass().getResource(hpBar[0]));
        Icons[3][1] = new ImageIcon(this.getClass().getResource(hpBar[1]));
        Icons[3][2] = new ImageIcon(this.getClass().getResource(hpBar[2]));
        
        Icons[4][0]  = new ImageIcon(this.getClass().getResource(playerMini[0]));
        Icons[4][1]  = new ImageIcon(this.getClass().getResource(playerMini[1]));
        Icons[4][2]  = new ImageIcon(this.getClass().getResource(playerMini[2]));
        Icons[4][3]  = new ImageIcon(this.getClass().getResource(playerMini[3]));
        
        Icons[5][0] = new ImageIcon(this.getClass().getResource(pause[0]));
        Icons[5][1] = new ImageIcon(this.getClass().getResource(pause[1]));
        Icons[5][2] = new ImageIcon(this.getClass().getResource(pause[2]));
        Icons[5][3] = new ImageIcon(this.getClass().getResource(pause[3]));
        Icons[5][4] = new ImageIcon(this.getClass().getResource(pause[4]));
        Icons[5][5] = new ImageIcon(this.getClass().getResource(pause[5]));
        Icons[5][6] = new ImageIcon(this.getClass().getResource(pause[6]));
        Icons[5][7] = new ImageIcon(this.getClass().getResource(pause[7]));
        
        Icons[6][0] = new ImageIcon(this.getClass().getResource("/main/Resources/Assets/Menu/enter.png"));
        
        Icons[10][0] = new ImageIcon(this.getClass().getResource(projectile[0]));
        Icons[10][1] = new ImageIcon(this.getClass().getResource(projectile[1]));
        Icons[10][2] = new ImageIcon(this.getClass().getResource(projectile[2]));
        Icons[10][3] = new ImageIcon(this.getClass().getResource(projectile[3]));
        Icons[10][4] = new ImageIcon(this.getClass().getResource(projectile[4]));
        Icons[10][5] = new ImageIcon(this.getClass().getResource(projectile[5]));
        Icons[10][6] = new ImageIcon(this.getClass().getResource(projectile[6]));
        Icons[10][7] = new ImageIcon(this.getClass().getResource(projectile[7]));
        Icons[10][10] = new ImageIcon(this.getClass().getResource(projectile[10]));
        Icons[10][12] = new ImageIcon(this.getClass().getResource(projectile[11]));
        Icons[10][14] = new ImageIcon(this.getClass().getResource(projectile[12]));
        Icons[10][16] = new ImageIcon(this.getClass().getResource(projectile[13]));
        Icons[10][18] = new ImageIcon(this.getClass().getResource(projectile[14]));
        Icons[10][20] = new ImageIcon(this.getClass().getResource(projectile[15]));
        Icons[10][22] = new ImageIcon(this.getClass().getResource(projectile[16]));
        Icons[10][23] = new ImageIcon(this.getClass().getResource(projectile[17]));
        Icons[10][24] = Icons[10][2];
        Icons[10][25] = new ImageIcon(this.getClass().getResource(projectile[18]));
        Icons[10][26] = Icons[10][4];
        Icons[10][27] = new ImageIcon(this.getClass().getResource(projectile[19]));
        Icons[10][28] = Icons[10][6];
        
        Icons[11][1] = new ImageIcon(this.getClass().getResource(background[1]));
        Icons[11][2] = new ImageIcon(this.getClass().getResource(background[2]));       
        Icons[11][3] = new ImageIcon(this.getClass().getResource(background[3]));        
        Icons[11][4] = new ImageIcon(this.getClass().getResource(background[4]));
        Icons[11][5] = new ImageIcon(this.getClass().getResource(background[5]));        
        Icons[11][6] = new ImageIcon(this.getClass().getResource(background[6]));
        Icons[11][7] = new ImageIcon(this.getClass().getResource(background[7]));
        
        Icons[12][0] = new ImageIcon(this.getClass().getResource(hud[0]));
        Icons[12][1] = new ImageIcon(this.getClass().getResource(hud[1]));
        Icons[12][2] = new ImageIcon(this.getClass().getResource(hud[2]));
        Icons[12][3] = new ImageIcon(this.getClass().getResource(hud[3]));
        Icons[12][4] = new ImageIcon(this.getClass().getResource(hud[4]));
        Icons[12][5] = new ImageIcon(this.getClass().getResource(hud[5]));
        Icons[12][6] = new ImageIcon(this.getClass().getResource(hud[6]));
        Icons[12][7] = new ImageIcon(this.getClass().getResource(hud[7]));
        
        Icons[13][0] = new ImageIcon(this.getClass().getResource(obstacles[0]));
        Icons[13][1] = new ImageIcon(this.getClass().getResource(obstacles[1]));
        Icons[13][2] = new ImageIcon(this.getClass().getResource(obstacles[2]));
        Icons[13][3] = new ImageIcon(this.getClass().getResource(obstacles[3]));
        Icons[13][4] = new ImageIcon(this.getClass().getResource(obstacles[4]));
        
        Icons[14][0] = new ImageIcon(this.getClass().getResource(explosion[0]));
        
    }
    
    public static Image getImage(int actor, int action) {       
        try{
            image = Icons[actor][action].getImage();               
        }
        catch(Exception e) {
            System.err.println("Error: Bild " + actor + " | " + action + " konnte nicht gefunden werden");
        }
        if(image == null) image = Icons[0][0].getImage();
        return image;
    }
    
    public int getWidth(int Actor, int Action) {
        return Icons[Actor][Action].getIconWidth();
    }
    
    public int getHeight(int Actor, int Action) {
        return Icons[Actor][Action].getIconHeight();
    }
}