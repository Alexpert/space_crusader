package game.main.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TextureProvider {
	private static TextureProvider instance = null;
	private HashMap<String, Image> textures;
	
	private TextureProvider() {
		this.textures = new HashMap<>();
	}
	
	public static TextureProvider getInstance() {
		if (instance == null)
			instance = new TextureProvider();
		
		return instance;
	}
	
	public Image getTexture(String path) {
		Image texture = this.textures.get(path);
		
		if (texture == null) {
			File textureFile = new File(path);
			try {
				texture = ImageIO.read(textureFile);
				this.textures.put(path, texture);
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}
		}
		
		return texture;
	}
}
