package com.andychan.game.states;

import com.andychan.game.FlappyDemo;

import com.andychan.game.Scenes.Hud;
import com.andychan.game.sprites.Bird;
import com.andychan.game.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Andy on 10/13/2016.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Hud hud;
    private String spriteChosen;

    private Array<Tube> tubes;
    public static Preferences prefs;

    private Boolean firstTouch;

    public PlayState(GameStateManager gsm, String sprite) {
        super(gsm);
        firstTouch = false;
        spriteChosen = sprite;
        bird = new Bird(30, 300, sprite);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        hud = new Hud(FlappyDemo.getBatch());

        prefs = Gdx.app.getPreferences("VampFlappy");

        if(!prefs.contains("highScore")){
            prefs.putInteger("highScore", 0);
        }

        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }


    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched() && firstTouch) {
            bird.jump();
        }
        firstTouch = true;
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                tube.isScored = false;
            }

            if(tube.collides(bird.getBounds())) {
                if(hud.getScore() > getHighScore()){
                    setHighScore(hud.getScore());
                }
                gsm.set(new GameOverState(gsm, hud.getScore(), spriteChosen));
            }

            if(isCounted(tube)){
                hud.addScore(1);
                tube.isScored = true;
            }
        }



        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            if (hud.getScore() > getHighScore()) {
                setHighScore(hud.getScore());
            }
            gsm.set(new GameOverState(gsm, hud.getScore(), spriteChosen));
        }

        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

        sb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        hud.dispose();

        for(Tube tube : tubes){
            tube.dispose();
        }
    }

    public Boolean isCounted(Tube tube){
        if(!tube.getIsScored() && (tube.getPosTopTube().x + tube.getTopTube().getWidth() / 2 + TUBE_SPACING - 20 < bird.getPosition().x + bird.getWidth())){
           return true;
        }
        else { return false;}
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2 - 10, 0);
        }
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2 - 10, 0);
        }
    }

    public static void setHighScore(int highScore){
        prefs.putInteger("highScore", highScore);
        prefs.flush();
    }

    public static int getHighScore(){
        return prefs.getInteger("highScore");
    }
}
