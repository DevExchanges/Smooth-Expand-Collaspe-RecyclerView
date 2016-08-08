package info.devexchanges.smoothexpandcollapserecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SampleActivity extends AppCompatActivity {

    private TextView txt_help_gest;
    private TextView txtTitle;
    private Animation animationUp;
    private Animation animationDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        txt_help_gest = (TextView) findViewById(R.id.txt_help_gest);
        txtTitle = (TextView) findViewById(R.id.help_title_gest);
        txt_help_gest.setVisibility(View.GONE);
        animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle_contents(view);
            }
        });
    }

    public void toggle_contents(View v){
        if(txt_help_gest.isShown()){
            txt_help_gest.setVisibility(View.GONE);
            txt_help_gest.startAnimation(animationUp);
        }
        else{
            txt_help_gest.setVisibility(View.VISIBLE);
            txt_help_gest.startAnimation(animationDown);
        }
    }

    public static void slide_down(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }
}
