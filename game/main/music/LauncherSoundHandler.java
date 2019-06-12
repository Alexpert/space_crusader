package game.main.music;

import game.main.view.Launcher;

public class LauncherSoundHandler {

	Launcher launcher;
	
	public LauncherSoundHandler(Launcher l) {
		this.launcher = l;
	}
	
	public void mute(boolean mute) {
		this.launcher.getMusic().mute(mute);
	}
	
	public void setLevel(float level) {
		this.launcher.getMusic().setLevel(level);
	}
	
	public void start() {
		this.launcher.getMusic().start();
	}
	
	public void stop() {
		this.launcher.getMusic().stop();
	}
}
