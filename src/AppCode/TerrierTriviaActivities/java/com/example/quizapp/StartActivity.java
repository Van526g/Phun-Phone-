package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void  startQuiz(View view){
        int quizCategory=0;//category all
        switch(view.getId()){
            case R.id.freshmen:
            quizCategory=1;
            break;
            case R.id.sophomore:
                quizCategory=2;
                break;
            case R.id.junior:
                quizCategory=3;
                break;
            case R.id.senior:
                quizCategory=4;
                break;

        }
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("QUIZ_CATEGORY",quizCategory);
        startActivity(intent);
    }
}
