package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.screens.FirstGameScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.SecondGameScreen;
import com.mygdx.game.screens.SettingsScreen;
import com.mygdx.game.screens.ThirdGameScreen;
import com.mygdx.game.utils.CustomFont;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.utils.MemoryLoader;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	public MenuScreen menuScreen;
	public FirstGameScreen firstGameScreen;
	public SecondGameScreen secondGameScreen;
	public ThirdGameScreen thirdGameScreen;
	public SettingsScreen settingsScreen;
	public OrthographicCamera camera;
	public CustomFont defaultFont;
	public CustomFont gameFont1;
	public CustomFont gameFont2;
	public CustomFont bigBlueFont;
	public CustomFont gameFontLarge1;
	public CustomFont gameFontLarge2;
	public CustomFont largeFont;
	public CustomFont gameOverBlueFont;
	public CustomFont gameOverBlueSmallFont;
	public CustomFont gameBoldBlueFont;
	public Vector3 touch;
	public Music music;

	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		touch = new Vector3();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT);
		camera.setToOrtho(false, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT);

		int musicNum = MemoryLoader.loadActiveMusic();
		if (musicNum != 0) {
			music = Gdx.audio.newMusic(Gdx.files.internal("audio/music" + musicNum + ".mp3"));
			music.setLooping(true);
			music.play();
		}

		defaultFont = new CustomFont(40, "fonts/arnamu.ttf", new Color(1, 1, 1, 1));
		gameFont1 = new CustomFont(100, "fonts/Kodchasan-Light.ttf", new Color(1, 1, 1, 1));
		gameFont2 = new CustomFont(80, "fonts/Kodchasan-Light.ttf", new Color(1, 1, 1, 1));
		gameFontLarge1 = new CustomFont(200, "fonts/Kodchasan-Light.ttf", new Color(1, 1, 1, 1));
		gameFontLarge2 = new CustomFont(130, "fonts/Kodchasan-Light.ttf", new Color(1, 1, 1, 1));
		largeFont = new CustomFont(70, "fonts/arnamu.ttf", new Color(1, 1, 1, 1));
		bigBlueFont = new CustomFont(100, "fonts/Kodchasan-Light.ttf", new Color(0, (float) 0.74, 1, 1));
		gameOverBlueFont = new CustomFont(80, "fonts/Kodchasan-Light.ttf", new Color(0, (float) 0.74, 1, 1));
		gameOverBlueSmallFont = new CustomFont(60, "fonts/Kodchasan-Light.ttf", new Color(0, (float) 0.74, 1, 1));
		gameBoldBlueFont = new CustomFont(100, "fonts/Kodchasan-SemiBold.ttf", new Color(0, (float) 0.74, 1, 1));

		menuScreen = new MenuScreen(this);
		firstGameScreen = new FirstGameScreen(this);
		secondGameScreen = new SecondGameScreen(this);
		thirdGameScreen = new ThirdGameScreen(this);
		settingsScreen = new SettingsScreen(this);

		setScreen(menuScreen);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}
}
