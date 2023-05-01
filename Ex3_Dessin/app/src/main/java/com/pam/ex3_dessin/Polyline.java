package com.pam.ex3_dessin;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

class Polyline implements CanvasElement {
    private Paint paint;

    List<Point> points = new ArrayList<>();

    Polyline(MotionEvent e1, Paint paint) {
        this.paint = paint;
        points.add(new Point(e1));
    }

    public void removeLast() {
        points.remove(points.size() - 1);
    }

    public Point last() {
        return points.get(points.size() - 1);
    }

    @Override
    public void drawOn(Canvas canvas) {
    }

    public void add(MotionEvent event) {
        points.add(new Point(event));
    }

    public void add(Point p) {
        points.add(p);
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }
}
