package com.pam.ex3_dessin;

import android.view.MotionEvent;

class Point {
    float x;
    float y;

    Point(MotionEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }
}
