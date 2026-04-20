package com.minijam.figures;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Square implements Figure {
    @Override
    public void draw(Canvas canvas, Paint paint, int x, int y, int size) {
        canvas.drawRect(x - size/2f, y - size/2f, x + size/2f, y + size/2f, paint);
    }
}
