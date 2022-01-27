package ca.gbc.comp3074.mind_manager_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class WelcomeActivity extends AppCompatActivity {

    String username = "";
    String firstName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        firstName = intent.getStringExtra("firstName");
        TextView welcomeText = findViewById(R.id.txtWelcome);
        if (firstName != null) {
            welcomeText.setText("Hi " + firstName + "! Welcome to the");
        }

        //Button "I don't know" goes to questionnaire page answer questions to determine their mood(QuestionActivity)
        Button btnIdk = findViewById(R.id.btnIdk);
        btnIdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIDontKnow();
            }
        });

        //Button Moody goes to the suggestions page (SuggestionsActivity)
        Button Moody = findViewById(R.id.btnMoody);
        Moody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMoody();
            }
        });

        //Button Happier goes to the suggestions page (SuggestionsActivity)
        Button Happier = findViewById(R.id.btnHappier);
        Happier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHappier();
            }
        });

        //Button Energetic goes to the suggestions page (SuggestionsActivity)
        Button Energetic = findViewById(R.id.btnEnergetic);
        Energetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnergetic();
            }
        });

        //Button Calmer goes to the suggestions page (SuggestionsActivity)
        Button Calmer = findViewById(R.id.btnCalmer); //
        Calmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalmer();
            }
        });

        //Button Relaxed goes to the suggestions page (SuggestionsActivity)
        Button Relaxed = findViewById(R.id.btnRelaxed);
        Relaxed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRelaxed();
            }
        });

    }


    //function to start QuestionsActivity
    private void openIDontKnow(){
        Intent start = new Intent(getApplicationContext(), QuestionsActivity.class);
        start.putExtra("username", username);
        start.putExtra("firstName", firstName);
        startActivity(start);
    }

    // Open Calmer suggestions
    private void openCalmer(){
        Intent start = new Intent(getApplicationContext(), SuggestionsActivity.class);
        String mood = "Calmer";
        start.putExtra("mood", mood);
        start.putExtra("username", username);
        start.putExtra("firstName", firstName);
        startActivity(start);
    }

    // Open Energetic suggestions
    private void openEnergetic(){
        Intent start = new Intent(getApplicationContext(), SuggestionsActivity.class);
        String mood = "Energetic";
        start.putExtra("mood", mood);
        start.putExtra("username", username);
        start.putExtra("firstName", firstName);
        startActivity(start);
    }

    // Open Happier suggestions
    private void openHappier(){
        Intent start = new Intent(getApplicationContext(), SuggestionsActivity.class);
        String mood = "Happier";
        start.putExtra("mood", mood);
        start.putExtra("username", username);
        start.putExtra("firstName", firstName);
        startActivity(start);
    }

    // Open Moody suggestions
    private void openMoody(){
        Intent start = new Intent(getApplicationContext(), SuggestionsActivity.class);
        String mood = "Moody";
        start.putExtra("mood", mood);
        start.putExtra("username", username);
        start.putExtra("firstName", firstName);
        startActivity(start);
    }

    // Open Tired suggestions
    private void openRelaxed(){
        Intent start = new Intent(getApplicationContext(), SuggestionsActivity.class);
        String mood = "Relaxed";
        start.putExtra("mood", mood);
        start.putExtra("username", username);
        start.putExtra("firstName", firstName);
        startActivity(start);
    }

    //function to start AboutActivity
    private void openAbout(){
        Intent start = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(start);
    }

    private void openlogin(){
        Intent start = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(start);
    }

    private void openDisclaimer(){
        Intent start = new Intent(getApplicationContext(), Disclaimer.class);
        startActivity(start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            openAbout();
            return true;
        }
        if (item.getItemId() == R.id.menu_main) {
            openlogin();
            return true;
        }
        if (item.getItemId() == R.id.disclaimer) {
            openDisclaimer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}