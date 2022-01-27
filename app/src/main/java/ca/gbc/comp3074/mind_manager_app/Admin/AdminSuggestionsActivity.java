package ca.gbc.comp3074.mind_manager_app.Admin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.sql.Connection;
import java.util.List;
import ca.gbc.comp3074.mind_manager_app.GoogleMySQLConnectionHelper;
import ca.gbc.comp3074.mind_manager_app.MainActivity;
import ca.gbc.comp3074.mind_manager_app.Models.AdminSuggestionArrayAdapter;
import ca.gbc.comp3074.mind_manager_app.Models.Suggestion;
import ca.gbc.comp3074.mind_manager_app.R;

public class AdminSuggestionsActivity extends ListActivity {

    List<Suggestion> suggestions;
    String categoryTitle = "";
    String moodTitle = "";
    int idEditedSuggestion = 0;
    String editSuggestion = "";
    String youtubeLink = "";

    TextView lblError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_suggestions);

        //accept variables "Category" and "Mood" from Admin Moods for Category page
        TextView title = findViewById(R.id.lblTitleSuggestionsForCategory);
        TextView txtSuggestion = findViewById(R.id.txtSuggestion);
        TextView txtEditSuggestion = findViewById(R.id.edittxt_suggestion);
        TextView txtEditYoutubeLink = findViewById(R.id.edittxt_youtubelink);

        lblError = findViewById(R.id.lblError);
        Intent intent = getIntent();
        categoryTitle = intent.getStringExtra("category");
        moodTitle = intent.getStringExtra("mood");
        idEditedSuggestion = intent.getIntExtra("id", 0);
        editSuggestion = intent.getStringExtra("suggestion");
        youtubeLink = intent.getStringExtra("youtubeLink");
        title.setText("Category: " + categoryTitle + "\n Mood: " + moodTitle);
        txtSuggestion.setText("New " + moodTitle + " " + categoryTitle + " Suggestion:");
        txtEditSuggestion.setText(editSuggestion);
        txtEditYoutubeLink.setText(youtubeLink);

        //Database instance
        final GoogleMySQLConnectionHelper db = new GoogleMySQLConnectionHelper();
        final Connection connect = db.connectionclass();

        printArray(connect, db);

        //button Submit
        Button btnSubmit = findViewById(R.id.btnAddSuggestion);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(connect, db);
            }
        });

        //button Logout
        Button btnLogOut = findViewById(R.id.btnLogoutAdmin);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogOut();
            }
        });
    }

    //print array of suggestions
    private void printArray(Connection connect, GoogleMySQLConnectionHelper db){
        suggestions = db.getAllSuggestionsOneCategoryOneMood(connect, moodTitle, categoryTitle);
        StringBuilder sb = new StringBuilder();
        int size = suggestions.size();
        boolean appendSeparator = false;
        for(int y=0; y < size; y++){
            if (appendSeparator)
                sb.append(','); // a comma
            appendSeparator = true;
            sb.append(suggestions.get(y));
        }
        ArrayAdapter<Suggestion> myAdapter = new AdminSuggestionArrayAdapter(this, suggestions);
        setListAdapter(myAdapter);
    }

    //function submit
    private void submit(Connection connect, GoogleMySQLConnectionHelper db) {
        String newSuggestion = ((EditText) findViewById(R.id.edittxt_suggestion)).getText().toString();
        String newYoutubeLink = ((EditText) findViewById(R.id.edittxt_youtubelink)).getText().toString();
        if (idEditedSuggestion == 0) {
            //Execute the query, find if suggestion input is existing in data base for this mood and category
            Suggestion suggestionExist = db.getExistedSuggestion(connect, moodTitle, categoryTitle, newSuggestion);

            if (suggestionExist != null) {
                lblError.setText("This suggestion is already exist");
            } else if (newSuggestion.equals("")) {
                lblError.setText("Field New " + moodTitle + " " + categoryTitle + " Suggestion should not be empty");
            } else {
                lblError.setText("");
                //Insert new suggestion
                Suggestion suggestion = new Suggestion(moodTitle, categoryTitle, newSuggestion, newYoutubeLink);
                db.addSuggestion(connect, suggestion);
                printArray(connect, db);
                ((EditText) findViewById(R.id.edittxt_suggestion)).setText("");
                ((EditText) findViewById(R.id.edittxt_youtubelink)).setText("");
            }
        } else {
            if (newSuggestion.equals("")) {
                lblError.setText("Field New " + moodTitle + " " + categoryTitle + " Suggestion should not be empty");
            } else {
                lblError.setText("");
                //Insert new suggestion
                Suggestion suggestion = new Suggestion(moodTitle, categoryTitle, newSuggestion, newYoutubeLink);
                db.editSuggestion(connect, suggestion, idEditedSuggestion);
                printArray(connect, db);
                ((EditText) findViewById(R.id.edittxt_suggestion)).setText("");
                ((EditText) findViewById(R.id.edittxt_youtubelink)).setText("");
            }
        }
    }

    //function LogOut
    private void openLogOut(){
        Intent start = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(start);
    }
}