package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.FirstGameScreen;
import com.mygdx.game.screens.GameOverScreen;
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
	public GameOverScreen gameOverScreen;
	public OrthographicCamera camera;
	public CustomFont defaultFont;
	public CustomFont gameFont1;
	public CustomFont gameFont2;
	public CustomFont gameFontLarge1;
	public CustomFont gameFontLarge2;
	public CustomFont largeFont;
	public CustomFont gameOverBlueFont;
	public Vector3 touch;
	public Music music;

	@Override
	public void create () {
		touch = new Vector3();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT);
		camera.setToOrtho(false, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT);

		music = Gdx.audio.newMusic(Gdx.files.internal("audio/music" + MemoryLoader.loadActiveMusic() + ".mp3"));
		music.setLooping(true);
		music.play();

		defaultFont = new CustomFont(40, "fonts/arnamu.ttf", new Color(1, 1, 1, 1));
		gameFont1 = new CustomFont(100, "fonts/Kodchasan-Light.ttf", new Color(1, 1, 1, 1));
		gameFont2 = new CustomFont(80, "fonts/Kodchasan-Light.ttf", new Color(1, 1, 1, 1));
		gameFontLarge1 = new CustomFont(200, "fonts/Kodchasan-Light.ttf", new Color(1, 1, 1, 1));
		gameFontLarge2 = new CustomFont(130, "fonts/Kodchasan-Light.ttf", new Color(1, 1, 1, 1));
//		accentFont = new CustomFont(40, "fonts/arnamubi.ttf", new Color(1, 1, 1, 1));
		largeFont = new CustomFont(70, "fonts/arnamu.ttf", new Color(1, 1, 1, 1));
		gameOverBlueFont = new CustomFont(80, "fonts/Kodchasan-Light.ttf", new Color(0, (float) 0.74, 1, 1));

		menuScreen = new MenuScreen(this);
		firstGameScreen = new FirstGameScreen(this);
		secondGameScreen = new SecondGameScreen(this);
		thirdGameScreen = new ThirdGameScreen(this);
		settingsScreen = new SettingsScreen(this);
		gameOverScreen = new GameOverScreen(this);

		setScreen(menuScreen);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}
}
