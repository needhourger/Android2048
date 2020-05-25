package com.example.game2048;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int score=0;
    private TextView tvScore;
    private TextView tvBestScore;
    private ImageButton btnNewGame;
    private GameView gameView;
    private AnimLayer animLayer=null;
    private LinearLayout root=null;
    private static MainActivity mainActivity=null;

    public static final String SP_KEY_BEST_SCORE="bestScore";

    public MainActivity(){
        mainActivity=this;
    }

    public static MainActivity getMainActivity(){
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root=(LinearLayout)findViewById(R.id.container);
        root.setBackgroundColor(0xfffaf8ef);

        tvScore=(TextView)findViewById(R.id.tvScore);
        tvBestScore=(TextView)findViewById(R.id.tvBestScore);

        gameView=(GameView)findViewById(R.id.gameView);

        btnNewGame=(ImageButton) findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.startGame();
            }
        });

        animLayer=(AnimLayer)findViewById(R.id.animLayer);
    }

    public void clearScore(){
        score=0;
        showScore();
    }

    public void showScore(){
        tvScore.setText(""+score);
    }

    public void addScore(int s){
        score+=s;
        showScore();

        if (score>getBestScore()){
            saveBestScore(score);
            showBestScore(score);
        }
    }

    public void saveBestScore(int s){
        SharedPreferences.Editor e=getPreferences(MODE_PRIVATE).edit();
        e.putInt(SP_KEY_BEST_SCORE,s);
        e.commit();
    }

    public int getBestScore(){
        return getPreferences(MODE_PRIVATE).getInt(SP_KEY_BEST_SCORE,0);
    }

    public void showBestScore(int s){
        tvBestScore.setText(""+s);
    }

    public AnimLayer getAnimLayer(){
        return animLayer;
    }

}
