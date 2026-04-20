package com.minijam.figures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle implements Figure {
    @Override
    public void draw(Canvas canvas, Paint paint, int x, int y, int size) {
        Path path = new Path();
        path.moveTo(x, y - size/2f);
        path.lineTo(x + size/2f, y + size/2f);
        path.lineTo(x - size/2f, y + size/2f);
        path.close();
        canvas.drawPath(path, paint);
    }
}
