package com.pam.ex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String STATUS = "status";
    private TextView text;
    private Button btn;
    private Boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.text = findViewById(R.id.textView);
        this.btn = findViewById(R.id.button);

        if(savedInstanceState != null){
            status = savedInstanceState.getBoolean(STATUS);
        }else{
            status = null;
        }

        if (Boolean.TRUE.equals(status)){
            this.btn.setRotation(20);
            this.text.setText(R.string.on);
        }
        else if(Boolean.FALSE.equals(status)){
            this.btn.setRotation(-20);
            this.text.setText(R.string.off);
        }
        else{
            this.btn.setRotation(0);
            this.text.setText(R.string.indeter);
        }

        this.btn.setOnClickListener(getOnClickListenerBtn());
    }

    /**
     * Retourne un OnClickListener pour pour le bouton "send".
     */
    private View.OnClickListener getOnClickListenerBtn(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Boolean.TRUE.equals(status)){
                    text.setText("");
                    animerBtn(R.string.off);
                    status = false;
                }
                else if(Boolean.FALSE.equals(status)){
                    text.setText("");
                    animerBtn(R.string.on);
                    status = true;
                }
                else{
                    text.setText("");
                    animerBtn(R.string.off);
                    status = false;
                }
            }
        };
    }

    private void animerBtn(Integer intText){

        AnimatorSet sequence = new AnimatorSet();
        ObjectAnimator animRot = new ObjectAnimator();
        ObjectAnimator animScaleX = new ObjectAnimator();
        ObjectAnimator animScaleY = new ObjectAnimator();

        if (Boolean.TRUE.equals(status)){
            animRot.setPropertyName("rotation");
            animRot.setFloatValues(20, -20);

            animScaleX.setPropertyName("scaleX");
            animScaleX.setFloatValues(1, 0.8f, 1);

            animScaleY.setPropertyName("scaleY");
            animScaleY.setFloatValues(1, 0.8f, 1);

        }
        else if(Boolean.FALSE.equals(status)){

            animRot.setPropertyName("rotation");
            animRot.setFloatValues(-20, 20);

            animScaleX.setPropertyName("scaleX");
            animScaleX.setFloatValues(1, 0.8f, 1);

            animScaleY.setPropertyName("scaleY");
            animScaleY.setFloatValues(1, 0.8f, 1);
        }
        else{

            animRot.setPropertyName("rotation");
            animRot.setFloatValues(0, -20);

            animScaleX.setPropertyName("scaleX");
            animScaleX.setFloatValues(1, 0.8f, 1);

            animScaleY.setPropertyName("scaleY");
            animScaleY.setFloatValues(1, 0.8f, 1);
        }

        Animator.AnimatorListener afterAnim = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                text.setText(intText);
            }
        };

        sequence.addListener(afterAnim);

        sequence.setDuration(600);
        sequence.setInterpolator(new BounceInterpolator());
        sequence.playTogether(animRot, animScaleX, animScaleY);

        sequence.setTarget(btn);
        sequence.start();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(STATUS, status);
    }
}