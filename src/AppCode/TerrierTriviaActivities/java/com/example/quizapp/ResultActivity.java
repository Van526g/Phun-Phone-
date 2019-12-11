package com.example.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel= findViewById(R.id.resultLabel);
        TextView totalScoreLabel= findViewById(R.id.totalScoreLabel);
        TextView highScoreLabel= findViewById(R.id.highScoreLabel);
        //Pulls the passed intents and places them in variables to be used in this scope
        int score=getIntent().getIntExtra("Right_Answer_Count",0);
        int questionCount=getIntent().getIntExtra("questionCount",1);
        SharedPreferences settings=getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore=settings.getInt("totalScore",0);
        //Pulls the highScore from the phones settings to be compared against the current high score
        int highScore=settings.getInt("HIGH_SCORE",0);
        totalScore=score;
        int quizCategory=getIntent().getIntExtra("QUIZ_CATEGORY",0);
        resultLabel.setText(score+"/" +questionCount);
        //Checks the quiz category to determine how many points per question should be administered to the total score
        if (quizCategory==0){
            totalScore=totalScore*250;
        }
        else if(quizCategory==1){
            totalScore=totalScore*100;
        }
        else if(quizCategory==2){
            totalScore=totalScore*200;
        }
        else if(quizCategory==3){
            totalScore=totalScore*300;
        }
        else{
            totalScore=totalScore*400;
        }
        //Displays total score to result page
        totalScoreLabel.setText(("Total Score: "+totalScore));
        //Passes total score to the Leaderboard activity
        Intent intent = new Intent(getApplicationContext(),LeaderBoardActivity.class);
        intent.putExtra("leaderScore",totalScore);
        startActivity(intent);
        //checks if the total score is higher than the current highscore if so total score is commited to the phones
        //memory as the new high score
        if(totalScore>highScore){
            highScoreLabel.setText("High Score: "+totalScore);
            SharedPreferences.Editor editor=settings.edit();
            editor.putInt("HIGH_SCORE", totalScore);
            editor.commit();
        } else{
            highScoreLabel.setText("High Score : "+highScore);
        }
        //Update Total Score
        SharedPreferences.Editor editor=settings.edit();
        editor.putInt("totalScore",totalScore);
        editor.commit();
    }

    // Return button returns back to Start Activity
    public void returnTop(View view){
        Intent intent=new Intent(getApplicationContext(),StartActivity.class);
        startActivity(intent);
    }
    // Post button takes user to the Leader Board page
    public void openLeaderBoardActivity(View view){
        Intent intent = new Intent(getApplicationContext(), LeaderBoardActivity.class);
        startActivity(intent);
    }

}
