package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/*import com.example.quizapp.Class.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;
import com.mongodb.stitch.core.services.mongodb.remote.RemoteUpdateOptions;
import com.mongodb.stitch.core.services.mongodb.remote.RemoteUpdateResult; */

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


//import org.bson.Document;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity {
    private String userName;
    Button result;
    Button postBtn;
    private EditText userInput;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        databaseUser= FirebaseDatabase.getInstance().getReference("user");
        userInput = findViewById(R.id.userInput);
        postBtn = findViewById(R.id.postBtn);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            // The name added by the user is stored in the 'username' variable.
            public void onClick(View view) {
                userName = userInput.getText().toString(); // User input is converted into a string.
                showToast(userName);
                addUser();
            }

        });
        // When return button is clicked, it takes user back to the "select year" menu to restart the trivia game.
        result = findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openResultActivity();
            }
        });

        // Link to Leaderboard Website - linkify class is used
        String wlink = "Click to access <a href=\"https://oconnoran17.github.io/TerrierTrivia/index.html\">leaderboard website</a>";
        TextView link = (TextView) findViewById(R.id.link);
        link.setText(Html.fromHtml(wlink));
        link.setMovementMethod(LinkMovementMethod.getInstance());


    }
    //This function is used to actual add the user to our database, which will add them to the website
    private void addUser(){
        //assigns the User's name to a variable username
        String username=userName;
        //gets the user's total score from the result activity
        int userScore=getIntent().getIntExtra("leaderScore",0);
        //Checks that the user actually entered a name
        if(!TextUtils.isEmpty(username)){
            //Creates an id for the database by getting a key
            String id =databaseUser.push().getKey();
            //Calls the user function to create a new user in a set structure
            User user = new User(id,username,userScore);
            //pushes the new user to be used by our database
            databaseUser.child(id).setValue(user);
            //Lets us know the function was executed
            Toast.makeText(this, "User & Score Added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "You should enter a Username", Toast.LENGTH_LONG).show();
        }
    }
    // Return button returns back to Start Activity
    public void openResultActivity(){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
    // User can insert username
    private void showToast(String text) {
        Toast.makeText(LeaderBoardActivity.this, text, Toast.LENGTH_SHORT).show();
    }
    // Post button allows user to uplaod their username to the wesbite's leader board
    public void openLeaderBoardActivity(View view){
        Intent intent = new Intent(getApplicationContext(), LeaderBoardActivity.class);
        startActivity(intent); //Post
    }

}

