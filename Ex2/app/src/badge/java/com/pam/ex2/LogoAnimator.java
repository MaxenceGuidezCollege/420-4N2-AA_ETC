package com.pam.ex2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class LogoAnimator {
    private static boolean left = true;

    public static void playAnimation(View target){

        float trans = 400;

        if(left) {
            trans = - trans;
        }

        ObjectAnimator rotOut = new ObjectAnimator();
        rotOut.setPropertyName("rotation");
        rotOut.setFloatValues(0, 90, 45, 56, 0);
        rotOut.setTarget(target);

        ObjectAnimator rotIn = new ObjectAnimator();
        rotIn.setPropertyName("rotation");
        rotIn.setFloatValues(0, -45, 0);
        rotIn.setTarget(target);


        ObjectAnimator movOut = new ObjectAnimator();
        movOut.setPropertyName("translationX");
        movOut.setFloatValues(0, trans);
        movOut.setTarget(target);

        ObjectAnimator movIn = new ObjectAnimator();
        movIn.setPropertyName("translationX");
        movIn.setFloatValues(trans, 0);
        movIn.setTarget(target);

        AnimatorSet anim = new AnimatorSet();
        anim.setDuration(1000);
        anim.play(rotOut).with(movOut);
        anim.play(rotIn).with(movIn);
        anim.play(movIn).after(movOut);
        anim.start();

        left = !left;
    }
}
