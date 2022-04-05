package model;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound {
	Clip clip;
	String namefile[] = new String[30];

	public Sound() {
		namefile[0] = "audio\\backgroundmusic.wav";
		namefile[1] = "audio\\startgame.wav";
		namefile[2] = "audio\\move.wav";
		namefile[3] = "audio\\casteling.wav";
		namefile[4] = "audio\\endgame.wav";
	}
	public void setFile(int i) {
		try {
			File file = new File(namefile[i]);
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			this.clip = AudioSystem.getClip();
			this.clip.open(ais);
		} catch (Exception e) {

		}
	}
	public void play() {
		this.clip.start();
	}
	public void loop() {
		this.clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		this.clip.stop();
	}
}
