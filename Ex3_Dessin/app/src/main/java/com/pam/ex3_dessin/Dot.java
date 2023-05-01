package com.pam.ex3_dessin;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

class Dot extends Point implements CanvasElement {
    private Paint paint;

    public Dot(MotionEvent e, Paint p) {
        super(e);
        this.paint = p;
    }

    public void drawOn(Canvas canvas) {
        canvas.drawCircle(x, y, 30, paint);
    }
}
