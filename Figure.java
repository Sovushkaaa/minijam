package com.minijam.figures;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Figure {
    void draw(Canvas canvas, Paint paint, int x, int y, int size);
}