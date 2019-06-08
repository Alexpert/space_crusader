package game.main.model;

import java.util.HashMap;

public enum TileBiome {
	DESERT("assets/terrain/desert.png"), STONE("assets/terrain/stone.png"),
	ICE("assets/terrain/ice.png"), DRYLAND("assets/terrain/dryland.png"),
	PLAIN("assets/terrain/plain.png"), FOREST("assets/terrain/forest.png"),
	JUNGLE("assets/terrain/jungle.png"), SWAMP("assets/terrain/swamp.png"),
	TAIGA("assets/terrain/taiga.png");
	
	private String texturePath;
	
	private TileBiome(String texturePath) {
		this.texturePath = texturePath;
		
	}
	
	public String getTexturePath() {
		return this.texturePath;
	}

	public static TileBiome getBiome(float temperature, float humidity) {
		if (temperature < 0.33) {
			if (humidity < 0.33) {
				return TileBiome.ICE;
			} else if (humidity < 0.66) {
				return TileBiome.STONE;
			} else {
				return TileBiome.DESERT;
			}
		} else if (temperature < 0.66) {
			if (humidity < 0.33) {
				return TileBiome.FOREST;
			} else if (humidity < 0.66) {
				return TileBiome.PLAIN;
			} else {
				return TileBiome.DRYLAND;
			}
		} else {
			if (humidity < 0.33) {
				return TileBiome.TAIGA;
			} else if (humidity < 0.66) {
				return TileBiome.SWAMP;
			} else {
				return TileBiome.JUNGLE;
			}
		}
	}
}
