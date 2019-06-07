package game.main.model;

public enum TileBiome {
	DESERT, STONE, ICE, DRYLAND, PLAIN, FOREST, JUNGLE, SWAMP, TAIGA;

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
