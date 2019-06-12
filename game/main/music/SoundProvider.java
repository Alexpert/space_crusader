package game.main.music;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SoundProvider {
	private static SoundProvider instance = null;
	private HashMap<String, Music> sounds;
	
	private SoundProvider() {
		this.sounds = new HashMap<>();
	}
	
	public static SoundProvider getInstance() {
		if (instance == null)
			instance = new SoundProvider();
		
		return instance;
	}
	
	public Music getSound(String path) {
		Music song = this.sounds.get(path);
		
		if (song == null) {
			File soundFile = new File(path);
			try {
				song = new Music(soundFile);
				this.sounds.put(path, song);
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
			
		}
		
		return song;
	}
}
