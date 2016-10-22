//package com.andychan.game.Scenes;
//
//
//import com.andychan.game.FlappyDemo;
//
//import com.andychan.game.states.GameStateManager;
//import com.andychan.game.states.PlayState;
//import com.badlogic.gdx.Gdx;
//
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//
///**
// * Created by Andy on 10/18/2016.
// */
//
//public class MyButton{
//    public Stage stage; //** stage holds the Button **//
//    private BitmapFont font; //** same as that used in Tut 7 **//
//    private TextureAtlas buttonsAtlas; //** image of buttons **//
//    private Skin buttonSkin; //** images are used as skins of the button **//
//    private TextButton button; //** the button - the only actor in program **//
//    private Viewport viewport;
//    private TextButtonStyle style;
//    private String sprite;
//    GameStateManager gsm1;
//
//    public MyButton(SpriteBatch sb, GameStateManager gsm) {
//        buttonsAtlas = new TextureAtlas("button.pack"); //** button atlas image **//
//        buttonSkin = new Skin();
//        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
//        font = new BitmapFont(Gdx.files.internal("text.fnt.txt"),false); //** font **//
//
//        viewport = new FitViewport(FlappyDemo.WIDTH, FlappyDemo.HEIGHT, new OrthographicCamera());
//        stage = new Stage(viewport, sb);       //** window is stage **//
//        style = new TextButtonStyle(); //** Button properties **//
//        style.up = buttonSkin.getDrawable("button");
//        style.over = buttonSkin.getDrawable("buttonPressed");
//        style.down = buttonSkin.getDrawable("buttonPressed");
//        style.font = font;
//        this.button = new TextButton("i", style);
//        stage.addActor(this.button);
//
//
//        gsm1 = gsm;
//        sprite = "birdanimation.png";
//
//    }
//
//    public void setButton(String spriteChosen){
//
////        button = new TextButton("i", style); //** Button text and style **//
//        sprite = spriteChosen;
////        button.addListener(new InputListener() {
////            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
////                gsm1.set(new PlayState(gsm1, sprite));
////                return true;
////            }
////        });
//
////        stage.addActor(button);
//    }
//
//    public void addListener(){
//        Gdx.input.setInputProcessor(stage); //** stage is responsive **//
//        this.button.addListener(new InputListener() {
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                gsm1.set(new PlayState(gsm1, sprite));
//                return true;
//            }
//        });
//        stage.addActor(this.button);
//    }
//
//    public String getSprite(){
//        return sprite;
//    }
//
//    public void setBounds(int x, int y, int width, int height){
//        button.setBounds(x,y,width,height);
//    }
//
//    public void dispose() {
//        buttonSkin.dispose();
//        buttonsAtlas.dispose();
//        font.dispose();
//        stage.dispose();
//    }
//}