package ca.gbc.comp3074.mind_manager_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.sql.Connection;
import ca.gbc.comp3074.mind_manager_app.Movies.MovieMain;
import ca.gbc.comp3074.mind_manager_app.Models.Suggestion;
import ca.gbc.comp3074.mind_manager_app.Models.SuggestionArrayAdapter;
import ca.gbc.comp3074.mind_manager_app.Music.VideoMain;
import ca.gbc.comp3074.mind_manager_app.Reading.BookDisplayActivity;
import ca.gbc.comp3074.mind_manager_app.Games.CrosswordGameActivity;

public class SuggestionsActivity extends AppCompatActivity{

    ListView listView;
    ArrayList<Suggestion> categories = new ArrayList<>();
    ArrayList<Suggestion> suggestions = new ArrayList<>();
    String moodTitle = "";
    String username = "";
    String firstName = "";

    //Database instance
    final GoogleMySQLConnectionHelper db = new GoogleMySQLConnectionHelper();
    final Connection connect = db.connectionclass();

    //array of category images
    int[] images = {R.drawable.sports, R.drawable.outdoors, R.drawable.reading, R.drawable.music, R.drawable.movies, R.drawable.games};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        listView = findViewById(R.id.listView);

        //accept variables "Mood" and "username" from Welcome page and set title of the current page
        TextView title = findViewById(R.id.lblTitle);
        Intent intent = getIntent();
        moodTitle = intent.getStringExtra("mood");
        username = intent.getStringExtra("username");
        firstName = intent.getStringExtra("firstName");
        if (firstName != null) {
            title.setText(firstName + "! Here are your suggestions for being more " + moodTitle);
        }else {
            title.setText("Here are your suggestions for being more " + moodTitle);
        }

        //functionality for btnFilter
        //create a list of items for the spinner (dropdown list for Filter button)
        Spinner dropdown = findViewById(R.id.spinner);
        final String[] items = new String[]{"All categories", "Sport", "Outdoors", "Reading", "Music", "Movie", "Games"};
        //set adapter for the spinner (dropdown list for Filter button)
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(SuggestionsActivity.this, android.R.layout.simple_spinner_dropdown_item, items){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // this part is needed for hiding the original view
                View view = super.getView(position, convertView, parent);
                view.setVisibility(View.GONE);

                return view;
            }
        };

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(spinnerAdapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (items[position]) {
                    case "All categories":
                        //set suggestion to each unique category
                        getSetOfDifferentSuggestions(connect, moodTitle, db, username);
                        break;
                    case "Sport":
                        //add only one category (user choose it by Filter button)
                        categories.add(0, new Suggestion("Sport"));
                        //set 6 different suggestions for only one category and only one mood (by user choice)
                        suggestions = db.getSetOfSuggestionsFromOneCategory(connect, moodTitle, categories.get(0).getCategoryName()+"", username);
                        break;
                    case "Outdoors":
                        //add only one category (user choose it by Filter button)
                        categories.add(0, new Suggestion("Outdoors"));
                        //set 6 different suggestions for only one category and only one mood (by user choice)
                        suggestions = db.getSetOfSuggestionsFromOneCategory(connect, moodTitle, categories.get(0).getCategoryName()+"", username);
                        break;
                    case "Reading":
                        //add only one category (user choose it by Filter button)
                        categories.add(0, new Suggestion("Reading"));
                        //set 6 different suggestions for only one category and only one mood (by user choice)
                        suggestions = db.getSetOfSuggestionsFromOneCategory(connect, moodTitle, categories.get(0).getCategoryName()+"", username);
                        break;
                    case "Music":
                        //add only one category (user choose it by Filter button)
                        categories.add(0, new Suggestion("Music"));
                        //set 6 different suggestions for only one category and only one mood (by user choice)
                        suggestions = db.getSetOfSuggestionsFromOneCategory(connect, moodTitle, categories.get(0).getCategoryName()+"", username);
                        break;
                    case "Movie":
                        //add only one category (user choose it by Filter button)
                        categories.add(0, new Suggestion("Movie"));
                        //set 6 different suggestions for only one category and only one mood (by user choice)
                        suggestions = db.getSetOfSuggestionsFromOneCategory(connect, moodTitle, categories.get(0).getCategoryName()+"", username);
                        break;
                    default:
                        //add only one category (user choose it by Filter button)
                        categories.add(0, new Suggestion("Games"));
                        //set 6 different suggestions for only one category and only one mood (by user choice)
                        suggestions = db.getSetOfSuggestionsFromOneCategory(connect, moodTitle, categories.get(0).getCategoryName()+"", username);
                }

                //print result
                SuggestionArrayAdapter adapter = new SuggestionArrayAdapter(SuggestionsActivity.this, suggestions, images);
                listView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //functionality for btnRandom
        ImageButton btnRandom = findViewById(R.id.btnRandom);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set new suggestion to each unique category
                getSetOfDifferentSuggestions(connect, moodTitle, db, username);
                //print result
                SuggestionArrayAdapter adapter = new SuggestionArrayAdapter(SuggestionsActivity.this, suggestions, images);
                listView.setAdapter(adapter);
            }
        });

        displayPopup();


        //Move to next pages by clicking on any suggestion
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (suggestions.get(position).getCategoryName().equals("Sport")){
                    Intent start = new Intent(getApplicationContext(), MapActivity_Sports.class);
                    String sportTitle = suggestions.get(position).getYoutubeLink();
                    start.putExtra("sportTitle", sportTitle);
                    startActivity(start);
                }
                if (suggestions.get(position).getCategoryName().equals("Outdoors")){
                    Intent start = new Intent(getApplicationContext(), MapActivity_Outdoors.class);
                    String outdoorTitle = suggestions.get(position).getYoutubeLink();
                    start.putExtra("outdoorTitle", outdoorTitle);
                    startActivity(start);
                }
                if (suggestions.get(position).getCategoryName().equals("Reading")){
                    Intent start = new Intent(getApplicationContext(), BookDisplayActivity.class);
                    String bookTitle = suggestions.get(position).getSuggestionName();
                    start.putExtra("bookTitle", bookTitle);
                    startActivity(start);
                }
                if (suggestions.get(position).getCategoryName().equals("Music")){
                    Intent start = new Intent(SuggestionsActivity.this, VideoMain.class);
                    String YoutubeLink = suggestions.get(position).getYoutubeLink();
                    start.putExtra("MyParameter", YoutubeLink);
                    startActivity(start);
                }
                if (suggestions.get(position).getCategoryName().equals("Movie")){
                    Intent start = new Intent(SuggestionsActivity.this, MovieMain.class);
                    String YoutubeLink = suggestions.get(position).getYoutubeLink();
                    start.putExtra("MyParameter1", YoutubeLink);
                    startActivity(start);
                }
                if (suggestions.get(position).getCategoryName().equals("Games")){
                    Intent start = new Intent(SuggestionsActivity.this, CrosswordGameActivity.class);
                    start.putExtra("game", moodTitle);
                    startActivity(start);
                }
            }
        });
    }


    public void displayPopup(){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rootLayout),R.string.warning, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    // Receive set of 6 different suggestions for specific mood (it's function for Random button)
    private void getSetOfDifferentSuggestions(Connection connect, String moodTitle, GoogleMySQLConnectionHelper db, String username){
        //delete all suggestions
        suggestions.clear();
        //find all unique categories
        if (username == null) {
            categories = db.getRandomCategories(connect);
        } else {
            categories = db.getAllCategories(connect);
        }
        int size = categories.size();
        //set new suggestion to each unique category
        for (int i = 0; i < size; i++) {
            Suggestion suggestion = db.getSuggestion(connect, moodTitle, categories.get(i).getCategoryName() + "");
            suggestions.add(suggestion);
        }
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