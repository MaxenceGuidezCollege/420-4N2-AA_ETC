package com.pam.ex3_dessin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrawView extends View {

    private Paint touchPaint;
    private Paint longPressPaint;
    private Paint doubleTapPaint;
    private Paint linePaint;
    private Paint flingPaint;

    private List<Point> points = new ArrayList<>();
    private List<Dot> dots = new ArrayList<>();
    private List<Polyline> lines = new ArrayList<>();
    private Polyline current;
    private final GestureDetectorCompat detector;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initializePaints();

        detector = new GestureDetectorCompat(
                this.getContext(),
                new GestureDetector.SimpleOnGestureListener(){
                    @Override
                    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
                        dots.add(new Dot(e, touchPaint));
                        invalidate();

                        return true;
                    }

                    @Override
                    public void onLongPress(@NonNull MotionEvent e) {
                        dots.add(new Dot(e, longPressPaint));
                        invalidate();
                    }

                    @Override
                    public boolean onDoubleTap(@NonNull MotionEvent e) {
                        dots.add(new Dot(e, doubleTapPaint));
                        invalidate();

                        return true;
                    }
                }
        );
    }

    public void initializePaints(){
        touchPaint = new Paint();
        touchPaint.setColor(Color.BLUE);

        longPressPaint = new Paint();
        longPressPaint.setColor(Color.GREEN);

        doubleTapPaint = new Paint();
        doubleTapPaint.setColor(Color.MAGENTA);

        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(10);

        flingPaint = new Paint();
        flingPaint.setColor(Color.RED);
        flingPaint.setStrokeWidth(15);
    }

    public void clear() {
        lines.clear();
        points.clear();
        dots.clear();
        current = null;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(Dot p : dots){
            p.drawOn(canvas);
        }

        for(Polyline pl : lines){
            Point lastPoint = null;

            for(Point pointInLine : pl.points){
                if(pointInLine != pl.points.get(0)){
                    Paint re = new Paint();
                    re.setColor(Color.RED);
                    if(pl.getPaint().equals(re)){
                        canvas.drawLine(lastPoint.x, lastPoint.y, pointInLine.x, pointInLine.y, flingPaint);
                    }
                    else{
                        canvas.drawLine(lastPoint.x, lastPoint.y, pointInLine.x, pointInLine.y, linePaint);
                    }

                }
                lastPoint = pointInLine;
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean consumed = detector.onTouchEvent(event);

        if(!consumed){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    points.add(new Point(event));
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:
                    if(current == null){
                        Paint bl = new Paint();
                        bl.setColor(Color.BLACK);
                        current = new Polyline(event, bl);
                        lines.add(current);
                    }
                    else{
                        Point last = current.last();
                        Point actual = new Point(event);

                        if(actual.x >= last.x + 10 || actual.y >= last.y + 10){
                            Paint re = new Paint();
                            re.setColor(Color.RED);
                            current = new Polyline(event, re);
                            lines.add(current);
                        }
//                        else{
//                            Paint bl = new Paint();
//                            bl.setColor(Color.BLACK);
//                            current = new Polyline(event, bl);
//                            lines.add(current);
//                        }

                        current.add(event);
                    }
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    invalidate();

                    current = null;
                    break;
            }
        }

        return true;
    }
}
