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

    private Texture background;

    private Viewport viewport;

    public Stage stage; //** stage holds the Button **//
    private TextButton flappyButton, dragoniteButton, charizardButton, pidgeotoButton;
    private TextButton.TextButtonStyle flappyStyle, dragoniteStyle, charizardStyle, pidgeotoStyle;
    private TextureAtlas buttonsAtlas, playBtnAtlas; //** image of buttons **//
    private Skin buttonSkin, playBtnSkin; //** images are used as skins of the button **//
    BitmapFont font;

    Vector3 touchPos;
    GameStateManager gsm1;

    public MenuState(GameStateManager gsm, SpriteBatch sb) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);
        background = new Texture("bg.png");

        touchPos = new Vector3();

        buttonsAtlas = new TextureAtlas("spriteChoice.pack"); //** button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
        font = new BitmapFont(Gdx.files.internal("text.fnt.txt"),false); //** font **//

        viewport = new FitViewport(FlappyDemo.WIDTH, FlappyDemo.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);       //** window is stage **//

        playBtnAtlas = new TextureAtlas("playbutton.pack"); //** button atlas image **//
        playBtnSkin = new Skin();
        playBtnSkin.addRegions(playBtnAtlas); //** skins for on and off **//

        flappyStyle = new TextButton.TextButtonStyle(); //** Button properties **//
        flappyStyle.up = playBtnSkin.getDrawable("playbtn");
        flappyStyle.over = playBtnSkin.getDrawable("playbtnFlipped");
        flappyStyle.down = playBtnSkin.getDrawable("playbtnFlipped");
        flappyStyle.font = font;

        flappyButton = new TextButton("", flappyStyle);
        stage.addActor(flappyButton);
        flappyButton.setBounds((cam.position.x * 2 - playBtnSkin.getDrawable("playbtn").getMinWidth()), cam.position.y * 2, 200 , 110);

        dragoniteStyle = new TextButton.TextButtonStyle();
        dragoniteStyle.up = buttonSkin.getDrawable("dragonite");
        dragoniteStyle.over = buttonSkin.getDrawable("dragonite");
        dragoniteStyle.down = buttonSkin.getDrawable("dragonite");
        dragoniteStyle.font = font;

        dragoniteButton = new TextButton("", dragoniteStyle);
        stage.addActor(dragoniteButton);
        dragoniteButton.setBounds(cam.position.x * 2 - 200, cam.position.y * 2 - 200,90,90);

        charizardStyle = new TextButton.TextButtonStyle();
        charizardStyle.up = buttonSkin.getDrawable("charizard");
        charizardStyle.over = buttonSkin.getDrawable("charizard");
        charizardStyle.down = buttonSkin.getDrawable("charizard");
        charizardStyle.font = font;

        charizardButton = new TextButton("", charizardStyle);
        stage.addActor(charizardButton);
        charizardButton.setBounds((cam.position.x * 2) - buttonSkin.getDrawable("charizard").getMinWidth() / 2 - 10, (cam.position.y * 2) - 200, 90,90);

        pidgeotoStyle = new TextButton.TextButtonStyle();
        pidgeotoStyle.up = buttonSkin.getDrawable("pidgeoto");
        pidgeotoStyle.over = buttonSkin.getDrawable("pidgeoto");
        pidgeotoStyle.down = buttonSkin.getDrawable("pidgeoto");
        pidgeotoStyle.font = font;

        pidgeotoButton = new TextButton("", pidgeotoStyle);
        stage.addActor(pidgeotoButton);
        pidgeotoButton.setBounds(cam.position.x * 2 + 120, cam.position.y * 2 - 200, 90 , 90);

        gsm1 = gsm;
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//
        flappyButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm1.set(new PlayState(gsm1, "birdanimation.png"));
                return true;
            }
        });

        dragoniteButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm1.set(new PlayState(gsm1, "dragonite.png"));
                return true;
            }
        });

        charizardButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm1.set(new PlayState(gsm1, "charizardanimation.png"));
                return true;
            }
        });

        pidgeotoButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm1.set(new PlayState(gsm1, "pidgeoto.png"));
                return true;
            }
        });

    }

    @Override
    public void handleInput() {
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
        font.getData().setScale(.2f, .2f);
        font.setColor(Color.CYAN);
        font.draw(sb, "Choose Your Character!", (cam.position.x - 45) / 2, cam.position.y - 25);
        sb.end();

        flappyButton.getStage().draw();
        dragoniteButton.getStage().draw();
        charizardButton.getStage().draw();
        pidgeotoButton.getStage().draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        buttonSkin.dispose();
        buttonsAtlas.dispose();
        playBtnSkin.dispose();
        playBtnAtlas.dispose();
        font.dispose();
        stage.dispose();
    }
}
