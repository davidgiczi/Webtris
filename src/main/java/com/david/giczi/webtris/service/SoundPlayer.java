package com.david.giczi.webtris.service;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

	private Long id;
	private Clip audioClip; 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void start() {
		audioClip.start();
	}
	
	public void stop() {
		audioClip.stop();
	}
	
	public void playing() {
		  
	        File audioFile = new File("./sound/Saltarello.wav");
	 
	        try {
	            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	 
	            AudioFormat format = audioStream.getFormat();
	 
	            DataLine.Info info = new DataLine.Info(Clip.class, format);
	 
	            Clip audioClip = (Clip) AudioSystem.getLine(info);
	            
	            audioClip.open(audioStream);
	             
	            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
	                    
	        } catch (UnsupportedAudioFileException ex) {
	            System.out.println("The specified audio file is not supported.");
	            ex.printStackTrace();
	        } catch (LineUnavailableException ex) {
	            System.out.println("Audio line for playing back is unavailable.");
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            System.out.println("Error playing the audio file.");
	            ex.printStackTrace();
	        }
	         
	    }
	  
}
