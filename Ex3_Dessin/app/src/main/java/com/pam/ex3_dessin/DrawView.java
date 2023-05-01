package com.pam.ex3_dessin;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View {

    private Paint linePaint;
    private Paint longPressPaint;
    private Paint touchPaint;
    private Paint flingPaint;
    private Paint doubleTapPaint;

    public void clear() {
        elements.clear();
        current = null;
    }

    List<CanvasElement> elements = new ArrayList<>();
    Polyline current;


    public DrawView(Context context) {
        super(context);
    }
}
