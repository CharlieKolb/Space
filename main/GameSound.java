package main;

import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;

import java.lang.Runnable;


/*
 *  Implementation following: http://stackoverflow.com/questions/577724/trouble-playing-wav-in-java/577926#577926
 */
public class GameSound implements Runnable {
    private String[][] sound;
    private int actor;
    private int action;
    private int volumeAdd;
    
    private Clip clip;
    private boolean loop;
    
    public void run() {
    	try {
            playSound(actor, action);
        } catch (Exception e) {}
        
    }
    

    
    public GameSound(int Actor, int Action, int VolumeAdd) {
        actor = Actor;
        action = Action;
        volumeAdd = VolumeAdd;
        
        
        sound = new String[5][6];
        sound[0][0] = "/main/Resources/Sound/Background/level0.wav";
        sound[0][1] = "/main/Resources/Sound/Background/level1.wav";
        sound[0][2] = "/main/Resources/Sound/Background/level2.wav";
        sound[0][3] = "/main/Resources/Sound/Background/level3.wav";
        sound[0][4] = "/main/Resources/Sound/Background/level4.wav";
        sound[0][5] = "/main/Resources/Sound/Background/level5.wav";
        sound[2][0] = "/main/Resources/Sound/Projectiles/laser01.wav";
        sound[3][0] = "/main/Resources/Sound/Boss/background00.wav";
        sound[3][1] = "/main/Resources/Sound/Boss/background01.wav";
        sound[4][0] = "/main/Resources/Sound/Explosions/explosion0.wav";

    }
    
    public void loopMusic() {
    	loop = true;
    }
    
    public void stopMusic() {
    	clip.close();
    }

    public void playSound(int Actor, int Action) throws Exception {
        class AudioListener implements LineListener {
            private boolean done = false;
            @Override public synchronized void update(LineEvent event) {
              Type eventType = event.getType();
              if (eventType == Type.STOP || eventType == Type.CLOSE) {
                done = true;
                notifyAll();
              }
            }
            public synchronized void waitUntilDone() throws InterruptedException {
              while (!done) { wait(); }
            }
        }
        
        AudioListener listener = new AudioListener();
        AudioInputStream tempStream = AudioSystem.getAudioInputStream(this.getClass().getResource(sound[Actor][Action]));
        
        try {
            clip = AudioSystem.getClip(); 
            clip.addLineListener(listener);
            clip.open(tempStream);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(volumeAdd);
            try {
                if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY); 
                else clip.start();
                listener.waitUntilDone();
            }
            finally {
                clip.close();
            }
        } 
        finally {
            tempStream.close();
        }
    }
}