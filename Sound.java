package app;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public void play(String path, int n) {
			try {
				File file = new File(path);
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
				clip.loop(n);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}
			}
}