package com.andychan.game.states;

import com.andychan.game.FlappyDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Andy on 10/15/2016.
 */

public class GameOverState extends State{

    Texture bg, playbtn, gameover, menu;
    BitmapFont font, scoreText, mainMenu;
    Integer score;
    String spriteChosen;
    Vector3 touchPos;

    public GameOverState(GameStateManager gsm, Integer score, String spriteChosen) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");
        playbtn = new Texture("playbtn.png");
        gameover = new Texture("gameover.png");
        font = new BitmapFont(Gdx.files.internal("text.fnt.txt"));
        scoreText = new BitmapFont(Gdx.files.internal("text.fnt.txt"));
        scoreText.setColor(Color.YELLOW);
        this.score = score;
        this.spriteChosen = spriteChosen;
        touchPos = new Vector3();
        menu = new Texture("mainbtn.png");
        mainMenu = new BitmapFont(Gdx.files.internal("text.fnt.txt"));
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            touchPos.set((FlappyDemo.WIDTH - Gdx.input.getX())/2, (FlappyDemo.HEIGHT -  Gdx.input.getY())/2, 0);

            if(touchPos.x > cam.position.x - playbtn.getWidth() / 2 && touchPos.x < cam.position.x + playbtn.getWidth() / 2){
                if(touchPos.y > cam.position.y  / 2- playbtn.getHeight() / 2 && touchPos.y < cam.position.y /2 + playbtn.getHeight()){
                    gsm.set(new PlayState(gsm, spriteChosen));
                }
            }

            if(touchPos.x > cam.position.x - menu.getWidth() / 2 && touchPos.x < cam.position.x + menu.getWidth() / 2){
                if(touchPos.y > cam.position.y / 2 + 200 - menu.getHeight() / 2 && touchPos.y < cam.position.y / 2 + 200 + menu.getHeight() / 2){
                    gsm.set(new MenuState(gsm, FlappyDemo.getBatch()));
                }
            }


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
        font.draw(sb, "Your Score was: " + score.toString() + "!", cam.position.x / 2, cam.position.y - 25);
        mainMenu.getData().setScale(.2f, .2f);
        mainMenu.draw(sb, "Return to Main Menu", 10 + cam.position.x - menu.getWidth() / 2, cam.position.y / 2 + 200);
        scoreText.getData().setScale(.2f, .2f);
        scoreText.draw(sb, "Highscore: " + PlayState.prefs.getInteger("highScore") + "!", cam.position.x / 2, cam.position.y - 5);
        sb.draw(gameover, cam.position.x - gameover.getWidth() / 2, cam.position.y);
        sb.end();


    }

    @Override
    public void dispose() {
        bg.dispose();
        playbtn.dispose();
        gameover.dispose();
        menu.dispose();
    }
}
