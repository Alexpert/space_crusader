package game.main.model;

import java.util.ArrayList;
import java.util.Random;

public class WorldBuilder {
	public static Tile[][] createTiles(int width, int height, World world) {
		Tile[][] tiles = new Tile[width][height];
		float[] temperature = ToreNoise.noise(width, height);
		float[] humidity = ToreNoise.noise(width, height);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tiles[j][i] = new Tile(j, i, 
					TileBiome.getBiome(temperature[i * width + j], humidity[i * width + j]), world);
			}
		}
		
		return tiles;
	}
	
	public ArrayList<Entity> generateEntities(Tile[][] tiles, int width, int height) {
		//TODO
		return new ArrayList<Entity>();
	}

}

class ToreNoise {
	private static final float INITIAL_VALUE = 0.5f;
	private static final float AMPLITUDE = 1;

	public static float[] noise(int dimX, int dimY) {
		float[] values = new float[dimX * dimY];
		Random random = new Random();

		values[0] = ToreNoise.INITIAL_VALUE;
		extrapolateHorizontalLine(values, 0, 0, dimX, dimX, random);
		extrapolateVerticalLine(values, 0, 0, dimY, dimY, random);
		generateArea(values, 0, 0, dimX, dimY, dimX, dimY, random);

		return values;
	}

	private static void generateArea(float[] values, int x, int y, int width, int height, int maxWidth, int maxHeight,
			Random random) {

		if (width <= 1 && height <= 1)
			return;
		float[] sides = new float[4];

//		sides[0] = values[y * maxWidth + x];
//		sides[1] = values[y * maxWidth + (x + width) % maxWidth];
//		sides[2] = values[((y + height) % maxHeight) * maxWidth + (x + width) % maxWidth];
//		sides[3] = values[((y + height) % maxHeight) * maxWidth + x];

		sides[0] = values[y * maxWidth + x + width / 2];
		sides[1] = values[(y + height / 2) * maxWidth + (x + width) % maxWidth];
		sides[2] = values[((y + height) % maxHeight) * maxWidth + x + width / 2];
		sides[3] = values[(y + height / 2) * maxWidth + x];

		values[(y + height / 2) * maxWidth + (x + width / 2)] = extrapolate(sides[0], sides[1], sides[2],
				sides[3], (float) Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)),
				(float) Math.sqrt(Math.pow(maxWidth, 2) + Math.pow(maxHeight, 2)), random);

		extrapolateHorizontalLine(values, x, y + height / 2, width / 2, maxWidth, random);
		extrapolateHorizontalLine(values, x + width / 2, y + height / 2, width - width / 2, maxWidth, random);

		extrapolateVerticalLine(values, x + width / 2, y, height / 2, maxHeight, random);
		extrapolateVerticalLine(values, x + width / 2, y + height / 2, height - height / 2, maxHeight, random);

		generateArea(values, x, y, width / 2, height / 2, maxWidth, maxHeight, random);
		generateArea(values, x + width / 2, y, width - width / 2, height / 2, maxWidth, maxHeight, random);
		generateArea(values, x + width / 2, y + height / 2, width - width / 2, height - height / 2, maxWidth, maxHeight,
				random);
		generateArea(values, x, y + height / 2, width / 2, height - height / 2, maxWidth, maxHeight, random);

	}

	private static void extrapolateHorizontalLine(float[] values, int x, int y, int length, int maxLength,
			Random random) {
		if (length <= 1)
			return;
		float[] extremities = new float[2];

		extremities[0] = values[y * maxLength + x];
		extremities[1] = values[y * maxLength + (x + length) % maxLength];

		values[y * maxLength + x + length / 2] = extrapolate(extremities[0], extremities[1], length, maxLength, random);

		extrapolateHorizontalLine(values, x, y, length / 2, maxLength, random);
		extrapolateHorizontalLine(values, x + length / 2, y, length - length / 2, maxLength, random);

	}

	private static void extrapolateVerticalLine(float[] values, int x, int y, int length, int maxLength,
			Random random) {
		if (length <= 1)
			return;

		int width = values.length / maxLength;
		float[] extremities = new float[2];

		extremities[0] = values[y * width + x];
		extremities[1] = values[((y + length) % maxLength) * width + x];

		values[(y + length / 2) * width + x] = extrapolate(extremities[0], extremities[1], length, maxLength, random);

		extrapolateVerticalLine(values, x, y, length / 2, maxLength, random);
		extrapolateVerticalLine(values, x, y + length / 2, length - length / 2, maxLength, random);
	}

	private static float extrapolate(float val1, float val2, float distance, float size, Random random) {
		float range = ToreNoise.AMPLITUDE * distance / size;

		float minimum = ((val1 + val2) / 2) - range / 2;

		float value = random.nextFloat() * range + minimum;

		if (value < 0)
			value = 0.0f;
		if (value > 1)
			value = 0.99f;

		return value;
	}

	private static float extrapolate(float val1, float val2, float val3, float val4, float distance, float size,
			Random random) {
		float range = ToreNoise.AMPLITUDE * distance / size;

		float minimum = ((val1 + val2 + val3 + val4) / 4) - range / 2;

		float value = random.nextFloat() * range + minimum;

		if (value < 0)
			value = 0.0f;
		if (value > 1)
			value = 0.99f;

		return value;
	}
}