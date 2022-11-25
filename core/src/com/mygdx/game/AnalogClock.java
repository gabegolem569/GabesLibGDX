package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class AnalogClock {
    private final float centerX = Gdx.graphics.getWidth()/2;
    private final float centerY = Gdx.graphics.getHeight()/2;
    private long secondsSinceMidnight;
    private double hoursSinceMidnight;
    private double minutesSinceLastHour;
    private int secondsSinceLastMinute;

    public AnalogClock() {
    }

    public void updateTimes() {
        secondsSinceMidnight = (System.currentTimeMillis()/1000)%86400;
        hoursSinceMidnight = (secondsSinceMidnight/3600F);
        minutesSinceLastHour = ((secondsSinceMidnight%3600)/60F);
        secondsSinceLastMinute = (int) ((secondsSinceMidnight%3600)%60);
        System.out.println(hoursSinceMidnight + " " + minutesSinceLastHour + " " + secondsSinceLastMinute);
    }

    public static float[] rotatePoint(float centerX, float centerY, float angle, float lineLength) {
        double angleInRadians = (angle+90) * (Math.PI / 180);
        float endX = (float) (centerX + lineLength * Math.cos(angleInRadians));
        float endY = (float) (centerY + lineLength * Math.sin(angleInRadians));
        return new float[] {endX, endY};
    }


    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch, BitmapFont font) {
        updateTimes();

        batch.begin();
        for (int i=12; i>0; i--) {
            float[] r = rotatePoint(centerX, centerY, (float) -i * 30, centerX - 40);
            font.draw(batch, Integer.toString(i), r[0], r[1]);
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle( (int) centerX, (int) centerY, (int) centerX);

        shapeRenderer.setColor(1, 0, 1, 1);
        float[] hourHandCoords  = rotatePoint(centerX, centerY, (float) hoursSinceMidnight*-30, centerX-80);
        shapeRenderer.line(centerX, centerY, hourHandCoords[0], hourHandCoords[1]);

        shapeRenderer.setColor(0, 1, 1, 1);
        float[] minuetHandCoords = rotatePoint(centerX, centerY, (float) minutesSinceLastHour*-6, centerX-20);
        shapeRenderer.line(centerX, centerY, minuetHandCoords[0], minuetHandCoords[1]);

        shapeRenderer.setColor(1, 0, 0, 1);
        float[] secondHandCoords = rotatePoint(centerX, centerY, (float) secondsSinceLastMinute*-6, centerX);
        shapeRenderer.line(centerX, centerY, secondHandCoords[0], secondHandCoords[1]);

        shapeRenderer.end();
    }

}
