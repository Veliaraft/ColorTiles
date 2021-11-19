package com.example.color_tiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button[][] buttons = new Button[3][3];
    boolean[][] numbs = new boolean[3][3];
    View lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        buttons[0][0] = findViewById(R.id.top_left);
        buttons[0][1] = findViewById(R.id.top_center);
        buttons[0][2] = findViewById(R.id.top_right);
        buttons[1][0] = findViewById(R.id.mid_left);
        buttons[1][1] = findViewById(R.id.mid_center);
        buttons[1][2] = findViewById(R.id.mid_right);
        buttons[2][0] = findViewById(R.id.bot_left);
        buttons[2][1] = findViewById(R.id.bot_center);
        buttons[2][2] = findViewById(R.id.bot_right);
        lay = findViewById(R.id.winImage);
        NewGame();
    }

    public void Restart(View view){
        lay.setVisibility(View.GONE);
        NewGame();
    }

    public void NewGame(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if((i+j)%2 == 0){
                    numbs[i][j] = true;
                } else {
                    numbs[i][j] = false;
                }
            }
        }
        colorSet();
    }

    public void colorSet(){
        for(int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++){
                if(numbs[i][j]) buttons[i][j].setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                else buttons[i][j].setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.black));
            }
    }

    public boolean check(){
        boolean c = true;
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if (numbs[i][j] != numbs[0][0]) c = false;
        return c;
    }

    public void clickEvent(int i, int j){
        numbs[i][j] = !numbs[i][j];
        if (i > 0){
            numbs[i-1][j] = !numbs[i-1][j];
        }
        if (j > 0){
            numbs[i][j-1] = !numbs[i][j-1];
        }
        if (i < 2){
            numbs[i+1][j] = !numbs[i+1][j];
        }
        if (j < 2){
            numbs[i][j+1] = !numbs[i][j+1];
        }
    }

    public void onClickGameKeybd(View view){
        switch(view.getId()){
            case R.id.top_left:
                clickEvent(0, 0);
                break;
            case R.id.top_center:
                clickEvent(0, 1);
                break;
            case R.id.top_right:
                clickEvent(0, 2);
                break;
            case R.id.mid_left:
                clickEvent(1, 0);
                break;
            case R.id.mid_center:
                clickEvent(1, 1);
                break;
            case R.id.mid_right:
                clickEvent(1, 2);
                break;
            case R.id.bot_left:
                clickEvent(2, 0);
                break;
            case R.id.bot_center:
                clickEvent(2, 1);
                break;
            case R.id.bot_right:
                clickEvent(2, 2);
                break;
        }
        colorSet();
        if(check()){
            lay.setVisibility(View.VISIBLE);
        }
    }

    public void Exit(View view) {
        this.finish();
    }
}