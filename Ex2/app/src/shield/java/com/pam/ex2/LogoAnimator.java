package com.pam.ex2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class LogoAnimator {
    private static boolean ccw = true;

    public static void playAnimation(View target){
        ObjectAnimator rot = new ObjectAnimator();
        rot.setPropertyName("rotation");
        rot.setFloatValues(0, 360);
        rot.setTarget(target);

        float[] pivotsX = new float[100];
        float[] pivotsY = new float[100];

        float centerX = target.getWidth() / 2;
        float centerY = target.getHeight() / 2;
        float radius = target.getWidth() / 2;
        for (int i = 0; i < pivotsX.length; i++) {
            float angle = (float) (i * 2 * Math.PI /  pivotsX.length);
            if(ccw) angle = -angle;
            float x = centerX + radius * (float) Math.cos(angle);
            float y = centerY + radius * (float) Math.sin(angle);
            pivotsX[i] = x;
            pivotsY[i] = y;
        }

        ObjectAnimator pivX = new ObjectAnimator();
        pivX.setPropertyName("pivotX");
        pivX.setFloatValues(pivotsX);
        pivX.setTarget(target);

        ObjectAnimator pivY = new ObjectAnimator();
        pivY.setPropertyName("pivotY");
        pivY.setFloatValues(pivotsY);
        pivY.setTarget(target);


        AnimatorSet anim = new AnimatorSet();
        anim.setDuration(1000);
        anim.play(rot).with(pivX).with(pivY);
        anim.start();

        ccw = !ccw;
    }
}
