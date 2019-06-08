package com;
//import java.applet.Applet;
//import java.applet.AudioClip;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Sounds {
    private static Sounds instance;

    public static final String MENU = "src/snd/Prepare.wav";
    public static final String BUTTON_MOVEOVER = "src/snd/button_press.wav";
    public static final String BUTTON_CLICKED = "src/snd/confirm.wav";
    public static final String TAG_SOUND = "src/snd/Village.wav";
    private HashMap<String, Clip> audioMap;

    public Sounds() {
        audioMap = new HashMap<>();
        loadAllAudio();
    }

    public static Sounds getIstance() {
        if (instance == null) {
            instance = new Sounds();
        }

        return instance;
    }

    public void loadAllAudio() {
        putAudio(MENU);
        putAudio(BUTTON_MOVEOVER);
        putAudio(BUTTON_CLICKED);
        putAudio(TAG_SOUND);
    }

    public void stop() {
        getAudio(MENU).stop();
        getAudio(BUTTON_MOVEOVER).stop();
        getAudio(BUTTON_CLICKED).stop();
        getAudio(TAG_SOUND).stop();

    }

    public void putAudio(String name) {
//        AudioClip auClip = Applet.newAudioClip(Sounds.class.getResource(name));
//        audioMap.put(name, auClip);
        File file = new File(name);
        Clip clip;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            audioMap.put(name, clip);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }
    }

    public Clip getAudio(String name) {
        return audioMap.get(name);
    }
}