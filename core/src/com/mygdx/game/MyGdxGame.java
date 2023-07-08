package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.FirstGameScreen;
import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.SecondGameScreen;
import com.mygdx.game.screens.SettingsScreen;
import com.mygdx.game.screens.ThirdGameScreen;
import com.mygdx.game.utils.CustomFont;

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
	public CustomFont accentFont;
	public CustomFont largeFont;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(1920, 1080);
		camera.setToOrtho(false, 1920, 1080);

//		defaultFont = new CustomFont(40, "fonts/arnamu.ttf", new Color(1, 1, 1, 1));
//		accentFont = new CustomFont(40, "fonts/arnamubi.ttf", new Color(1, 1, 1, 1));
//		largeFont = new CustomFont(70, "fonts/arnamu.ttf", new Color(1, 1, 1, 1));

		menuScreen = new MenuScreen(this);
		firstGameScreen = new FirstGameScreen(this);
		secondGameScreen = new SecondGameScreen(this);
		thirdGameScreen = new ThirdGameScreen(this);
		settingsScreen = new SettingsScreen(this);
		gameOverScreen = new GameOverScreen(this);

		setScreen(menuScreen);
	}

	@Override
	public void render () {
//		rgba(253, 233, 16, 1)
		ScreenUtils.clear((float) 0.99, (float) 0.91, (float) 0.06, 1);
		batch.begin();

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
