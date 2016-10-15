package com.andychan.game.Scenes;

import com.andychan.game.FlappyDemo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andy on 10/14/2016.
 */

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private static Integer score;

    Label scoreLabel;

    public Hud(SpriteBatch sb){
        score = 0;

        viewport = new FitViewport(FlappyDemo.WIDTH, FlappyDemo.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreLabel.setScale(3);

        table.add(scoreLabel).expandX();

        stage.addActor(table);

    }
    public void addScore(int value){
            score += value;
            scoreLabel.setText(String.format("%06d", score));
    }

    public void dispose(){
        stage.dispose();
    }
}
