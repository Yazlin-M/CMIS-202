package com.company;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
public class AudioFile {
    private String description;
    private String title;

    //private File seletedSong;

    //Constuctors:

    public AudioFile() {
        this.description = "";
        this.title = "";
    }

    public AudioFile(String description, String title) {
        this.description = description;
        this.title = title;
    }

    //Mutators:

    public void setFilename(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //Accessors:

    public String getFilename() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        String result = "Filename: " + getFilename() + "\n";
        result += "Title: " + getTitle() + "\n";
        return result;
    }

    public static File SelectASong(){

         JFileChooser fileChooser = new JFileChooser();

            fileChooser.showOpenDialog(null); //select file to open
            fileChooser.setMultiSelectionEnabled(true); //allows users to select more than one file

            //fileChooser.addChoosableFileFilter(true);
            Clip clip = null;

            File seletedSong = fileChooser.getSelectedFile();

            if (seletedSong.exists()){
                if (seletedSong.getAbsolutePath().contains("wav")){

                    AudioInputStream audioStream = null;
                    try {
                        audioStream = AudioSystem.getAudioInputStream(seletedSong);
                    } catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        clip.open(audioStream);
                    } catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }


                String response = "";

                //textfield or keylistener
                while(!response.equals("Q")){

                    System.out.println("P = play, S=pause, R=reset, Q=quit");
                    System.out.print("Enter your choice: ");

                    Scanner scanner = new Scanner(System.in);
                    response = scanner.next();
                    response = response.toUpperCase(Locale.ROOT);

                    switch(response){
                        case ("P"): clip.start();
                            break;

                        case ("S"): clip.stop();
                            break;

                        case ("R"): clip.setMicrosecondPosition(0); //same as reset
                            break;

                        case ("Q"): clip.close();
                            break;

                        default:
                            String ex = "Not a valid response ";
                            JOptionPane.showInputDialog(ex);
                    }



                }
                System.out.println("end");
            }
                return seletedSong;
            }



    public static File ChooseDefaultSong() {
        AudioInputStream audioStream = null;
        Clip clip = null;
        File  defaultSong = new File("Audio.wav");
        return defaultSong;
    }




}//AudioFile class
