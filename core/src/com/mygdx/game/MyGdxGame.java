package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.FirstGameScreen;
import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.SecondGameScreen;
import com.mygdx.game.screens.SettingsScreen;
import com.mygdx.game.screens.ThirdGameScreen;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	public MenuScreen menuScreen;
	public FirstGameScreen firstGameScreen;
	public SecondGameScreen secondGameScreen;
	public ThirdGameScreen thirdGameScreen;
	public SettingsScreen settingsScreen;
	public GameOverScreen gameOverScreen;
	public OrthographicCamera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(1920, 1080);
		camera.setToOrtho(false, 1920, 1080);

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
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
