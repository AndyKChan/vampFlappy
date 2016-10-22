package com.andychan.game.states;

import com.andychan.game.FlappyDemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andy on 10/13/2016.
 */

public class MenuState extends State {

    private Texture background, playBtn, dragonite, charizard, pidgeoto;
    private TextButton flappyButton, dragoniteButton, charizardButton, pidgeotoButton;

    public Stage stage; //** stage holds the Button **//

    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//

    private Viewport viewport;
    private TextButton.TextButtonStyle style;

    Vector3 touchPos;
    BitmapFont font;
    Boolean touchDown = false;
    GameStateManager gsm1;

    public MenuState(GameStateManager gsm, SpriteBatch sb) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);
        background = new Texture("bg.png");
        dragonite = new Texture("dragonite1.png");
        charizard = new Texture("charizard.png");
        pidgeoto = new Texture("pidgeoto1.png");

        touchPos = new Vector3();

        playBtn = new Texture("playbtn.png");
        buttonsAtlas = new TextureAtlas("button.pack"); //** button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
        font = new BitmapFont(Gdx.files.internal("text.fnt.txt"),false); //** font **//

        viewport = new FitViewport(FlappyDemo.WIDTH, FlappyDemo.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);       //** window is stage **//
        style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("button");
        style.over = buttonSkin.getDrawable("buttonPressed");
        style.down = buttonSkin.getDrawable("buttonPressed");
        style.font = font;

        flappyButton = new TextButton("f", style);
        stage.addActor(flappyButton);
        flappyButton.setBounds(50,50,50,50);

        dragoniteButton = new TextButton("d", style);
        stage.addActor(dragoniteButton);
        dragoniteButton.setBounds(100,50,50,50);

        charizardButton = new TextButton("c", style);
        stage.addActor(charizardButton);
        charizardButton.setBounds(150,50,50,50);

        pidgeotoButton = new TextButton("p", style);
        stage.addActor(pidgeotoButton);
        pidgeotoButton.setBounds(200,50,50,50);

        gsm1 = gsm;
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//
        flappyButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm1.set(new PlayState(gsm1, "birdanimation.png"));
                touchDown = true;
                return true;
            }
        });

        dragoniteButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm1.set(new PlayState(gsm1, "dragonite.png"));
                touchDown = true;
                return true;
            }
        });

        charizardButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm1.set(new PlayState(gsm1, "charizardanimation.png"));
                touchDown = true;
                return true;
            }
        });

        pidgeotoButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm1.set(new PlayState(gsm1, "pidgeoto.png"));
                touchDown = true;
                return true;
            }
        });

    }

    @Override
    public void handleInput() {
//        if(Gdx.input.justTouched()){
//
//
//
//            touchPos.set((FlappyDemo.WIDTH - Gdx.input.getX())/2, (FlappyDemo.HEIGHT -  Gdx.input.getY())/2, 0);
//
//            //flappy bird chosen
//            if(touchPos.x > cam.position.x * 2 - playBtn.getWidth() / 2 && touchPos.x < cam.position.x * 2 + playBtn.getWidth() / 2){
//                if(touchPos.y > cam.position.y * 2 - playBtn.getHeight() / 2 && touchPos.y < cam.position.y * 2+ playBtn.getHeight()){
//                    gsm.set(new PlayState(gsm, spriteChosen));
//                }
//            }
//
//            //dragonite chosen
//            if(touchPos.x > cam.position.x - (100 + dragonite.getWidth() / 2) && touchPos.x < cam.position.x - (100 - dragonite.getWidth() / 2)){
//                if(touchPos.y > cam.position.y - 100 - dragonite.getHeight() / 2 && touchPos.y < cam.position.y - 100 + dragonite.getHeight()){
//                    spriteChosen = "pidgeoto.png";
//                    gsm.set(new PlayState(gsm, spriteChosen));
//                }
//            }
//            //charizard chosen
//            if(touchPos.x > cam.position.x - (30 + charizard.getWidth()/ 2) && touchPos.x < cam.position.x - (30 - charizard.getWidth() / 2)){
//                if(touchPos.y > cam.position.y - 100 - charizard.getHeight() / 2 && touchPos.y < cam.position.y - 100 + charizard.getHeight()){
//                    spriteChosen = "charizardanimation.png";
//                    gsm.set(new PlayState(gsm, spriteChosen));
//                }
//            }
//            //pidgeoto chosen
//            if(touchPos.x > cam.position.x + (60 - pidgeoto.getWidth()/2) && touchPos.x < cam.position.x + 60 + pidgeoto.getWidth()/2){
//                if(touchPos.y > cam.position.y - 100 - pidgeoto.getHeight() / 2 && touchPos.y < cam.position.y - 100 + pidgeoto.getHeight()){
//                    spriteChosen = "dragonite.png";
//                    gsm.set(new PlayState(gsm, spriteChosen));
//                }
//            }
//
//        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0 , 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        font.getData().setScale(.2f, .2f);
        font.setColor(Color.CYAN);
        font.draw(sb, "Choose Your Character!", (cam.position.x - 45) / 2, cam.position.y - 25);
        sb.draw(dragonite, cam.position.x - 100, cam.position.y - 100);
        sb.draw(charizard, cam.position.x - 30, cam.position.y - 100);
        sb.draw(pidgeoto, cam.position.x + 60, cam.position.y - 100);
        sb.end();


        flappyButton.getStage().draw();
        dragoniteButton.getStage().draw();
        charizardButton.getStage().draw();
        pidgeotoButton.getStage().draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        dragonite.dispose();
        charizard.dispose();
        pidgeoto.dispose();
        buttonSkin.dispose();
        buttonsAtlas.dispose();
        font.dispose();
        stage.dispose();
    }
}
