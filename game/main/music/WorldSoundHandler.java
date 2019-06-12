package game.main.music;

import game.main.model.World;

public class WorldSoundHandler {

	World world;

	public WorldSoundHandler(World w) {
		this.world = w;
	}

	public void mute(boolean mute) {
		this.world.getMusic().mute(mute);
	}

	public void setLevel(float level) {
		this.world.getMusic().setLevel(level);
	}

	public void start() {
		this.world.getMusic().start();
	}

	public void stop() {
		this.world.getMusic().stop();
	}

}
