package com.andychan.game.states;

import com.andychan.game.FlappyDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Andy on 10/15/2016.
 */

public class GameOverState extends State{

    Texture bg, playbtn, gameover;
    BitmapFont font, scoreText;
    String score;

    public GameOverState(GameStateManager gsm, String score) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");
        playbtn = new Texture("playbtn.png");
        gameover = new Texture("gameover.png");
        font = new BitmapFont(Gdx.files.internal("text.fnt.txt"));
        scoreText = new BitmapFont(Gdx.files.internal("text.fnt.txt"));
        this.score = score;
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg , 0 ,0);
        sb.draw(playbtn, cam.position.x - playbtn.getWidth() / 2, cam.position.y / 2);
        font.getData().setScale(.2f, .2f);
        font.draw(sb, "Your Score was: " + score + "!", cam.position.x / 2, cam.position.y - 15);
        sb.draw(gameover, cam.position.x - gameover.getWidth() / 2, cam.position.y);
        sb.end();


    }

    @Override
    public void dispose() {

    }
}
