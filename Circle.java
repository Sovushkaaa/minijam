package com.minijam.figures;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle implements Figure {
    @Override
    public void draw(Canvas canvas, Paint paint, int x, int y, int size) {
        canvas.drawCircle(x, y, size / 2f, paint);
    }
}
