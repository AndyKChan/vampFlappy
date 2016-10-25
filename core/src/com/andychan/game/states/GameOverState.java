package com.andychan.game.states;

import com.andychan.game.FlappyDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andy on 10/15/2016.
 */

public class GameOverState extends State{

    Texture bg, gameover, menu;
    BitmapFont font, scoreText, mainMenu;
    Integer score;
    String spriteChosen;
    Vector3 touchPos;

    private Viewport viewport;
    public Stage stage; //** stage holds the Button **//
    private TextButton playButton, menuButton;
    private TextButton.TextButtonStyle playBtnStyle, menuButtonStyle;
    private TextureAtlas playBtnAtlas; //** image of buttons **//
    private Skin playBtnSkin, menuBtnSkin; //** images are used as skins of the button **//
    GameStateManager gsm;
    Drawable menuBtnIMG;

    public GameOverState(GameStateManager gsm1, Integer score, String spriteChosen1) {
        super(gsm1);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");

        viewport = new FitViewport(FlappyDemo.WIDTH, FlappyDemo.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, FlappyDemo.getBatch());       //** window is stage **//

        gameover = new Texture("gameover.png");
        font = new BitmapFont(Gdx.files.internal("text.fnt.txt"));
        scoreText = new BitmapFont(Gdx.files.internal("text.fnt.txt"));
        scoreText.setColor(Color.YELLOW);
        this.score = score;

        touchPos = new Vector3();
        menu = new Texture("mainbtn.png");
        mainMenu = new BitmapFont(Gdx.files.internal("text.fnt.txt"));

        playBtnAtlas = new TextureAtlas("playbutton.pack"); //** button atlas image **//
        playBtnSkin = new Skin();
        playBtnSkin.addRegions(playBtnAtlas); //** skins for on and off **//

        playBtnStyle = new TextButton.TextButtonStyle(); //** Button properties **//
        playBtnStyle.up = playBtnSkin.getDrawable("playbtn");
        playBtnStyle.over = playBtnSkin.getDrawable("playbtnFlipped");
        playBtnStyle.down = playBtnSkin.getDrawable("playbtnFlipped");
        playBtnStyle.font = font;

        playButton = new TextButton("", playBtnStyle);
        stage.addActor(playButton);
        playButton.setBounds((cam.position.x * 2 - playBtnSkin.getDrawable("playbtn").getMinWidth()), cam.position.y, 200 , 110);

        menuBtnIMG = new TextureRegionDrawable(new TextureRegion(new Texture("backArrow.png")));
        menuBtnSkin = new Skin();

        menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.up = menuBtnSkin.newDrawable(menuBtnIMG, Color.BLACK);
        menuButtonStyle.over = menuBtnSkin.newDrawable(menuBtnIMG, Color.BLACK);
        menuButtonStyle.down = menuBtnSkin.newDrawable(menuBtnIMG, Color.BLACK);
        menuButtonStyle.font = font;
        menuButton = new TextButton("", menuButtonStyle);
        stage.addActor(menuButton);
        menuButton.setBounds((cam.position.x * 2) - (menu.getWidth() + 18), (cam.position.y * 2) + 175, 30, 30);

        gsm = gsm1;
        spriteChosen = spriteChosen1;

        Gdx.input.setInputProcessor(stage); //** stage is responsive **//
        playButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new PlayState(gsm, spriteChosen));
                return true;
            }
        });

        menuButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new MenuState(gsm, FlappyDemo.getBatch()));
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
        sb.draw(bg , 0 ,0);

        font.getData().setScale(.2f, .2f);
        font.draw(sb, "Your Score was: " + score.toString() + "!", cam.position.x / 2, cam.position.y - 25);
        mainMenu.getData().setScale(.2f, .2f);
        mainMenu.draw(sb, "Return to Main Menu", 10 + cam.position.x - menu.getWidth() / 2, cam.position.y / 2 + 200);
        scoreText.getData().setScale(.2f, .2f);
        scoreText.draw(sb, "Highscore: " + PlayState.prefs.getInteger("highScore") + "!", cam.position.x / 2, cam.position.y - 5);
        sb.draw(gameover, cam.position.x - gameover.getWidth() / 2, cam.position.y);
        sb.end();

        playButton.getStage().draw();
        menuButton.getStage().draw();

    }

    @Override
    public void dispose() {
        bg.dispose();
        font.dispose();
        playBtnAtlas.dispose();
        playBtnSkin.dispose();
        menuBtnSkin.dispose();
        mainMenu.dispose();
        menu.dispose();
        scoreText.dispose();
        stage.dispose();
        gameover.dispose();

    }
}
