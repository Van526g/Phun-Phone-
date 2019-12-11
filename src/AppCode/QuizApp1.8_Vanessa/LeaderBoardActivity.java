package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;


//import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity {
    private String username;
    Button result;
    Button postBtn;
    private EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        userInput = (EditText)findViewById(R.id.userInput);
        postBtn = (Button)findViewById(R.id.postBtn);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = userInput.getText().toString();

                showToast(username);
            }

        });
        result = findViewById(R.id.result); //start here
        result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openResultActivity();
            }
        });


   /*public void savePlayer() {
       User newPlayer = new User();

       username.name = userInput.getText().toString();
       username.score = getIntent().getIntExtra("leaderScore",0);

       pushToMongo(username);
   }

   public void pushToMongo(User username) {
       final StitchAppClient client =
               Stitch.initializeDefaultAppClient("terriertrivia-vykbt");

       final RemoteMongoClient mongoClient =
               client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");

       final RemoteMongoCollection<Document> coll =
               mongoClient.getDatabase("LeaderBoard").getCollection("players");

       client.getAuth().loginWithCredential(new AnonymousCredential()).continueWithTask(
               new Continuation<StitchUser, Task<RemoteUpdateResult>>() {

                   @Override
                   public Task<RemoteUpdateResult> then(@NonNull Task<StitchUser> task) throws Exception {
                       if (!task.isSuccessful()) {
                           Log.e("STITCH", "Login failed!");
                           throw task.getException();
                       }

                       final Document updateDoc = new Document("name", username.name);
                       updateDoc.put("score", username.score);
                       return coll.updateOne(null, updateDoc, new RemoteUpdateOptions().upsert(true));
                   }
               }
       ).continueWithTask(new Continuation<RemoteUpdateResult, Task<List<Document>>>() {
           @Override
           public Task<List<Document>> then(@NonNull Task<RemoteUpdateResult> task) throws Exception {
               if (!task.isSuccessful()) {
                   Log.e("STITCH", "Update failed!");
                   throw task.getException();
               }
               List<Document> docs = new ArrayList<>();
               return coll
                       .find(new Document("owner_id", client.getAuth().getUser().getId()))
                       .limit(100)
                       .into(docs);
           }
       }).addOnCompleteListener(new OnCompleteListener<List<Document>>() {
           @Override
           public void onComplete(@NonNull Task<List<Document>> task) {
               if (task.isSuccessful()) {
                   Log.d("STITCH", "Found docs: " + task.getResult().toString());
                   return;
               }
               Log.e("STITCH", "Error: " + task.getException().toString());
               task.getException().printStackTrace();
           }
       });

   } */

        //@Override
        //protected void onCreate(Bundle savedInstanceState) {
        //    super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_leaderboard);


    }

    public void openResultActivity(){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    } // Return

    private void showToast(String text) {
        Toast.makeText(LeaderBoardActivity.this, text, Toast.LENGTH_SHORT).show();
    }
    public void openLeaderBoardActivity(View view){
        Intent intent = new Intent(getApplicationContext(), LeaderBoardActivity.class);
        startActivity(intent); //Post
    }

}

