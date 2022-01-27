package ca.gbc.comp3074.mind_manager_app.Admin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.sql.Connection;
import java.util.List;
import ca.gbc.comp3074.mind_manager_app.GoogleMySQLConnectionHelper;
import ca.gbc.comp3074.mind_manager_app.MainActivity;
import ca.gbc.comp3074.mind_manager_app.Models.MoodArrayAdapter;
import ca.gbc.comp3074.mind_manager_app.Models.Suggestion;
import ca.gbc.comp3074.mind_manager_app.R;

public class AdminMoodsForCategoryActivity extends ListActivity {

    List<Suggestion> moods;
    String categoryTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_moods_for_category);

        //accept variable "Category" from Admin Category page
        TextView title = findViewById(R.id.lblTitleMoodsForCategory);
        Intent intent = getIntent();
        categoryTitle = intent.getStringExtra("category");
        title.setText("Category " + categoryTitle + " \n sorted by moods");

        //Database instance
        final GoogleMySQLConnectionHelper db = new GoogleMySQLConnectionHelper();
        final Connection connect = db.connectionclass();

        //array of category images
        int[] images = {R.drawable.sports, R.drawable.outdoors, R.drawable.reading, R.drawable.music, R.drawable.movies, R.drawable.games};

        printArray(connect, db, images);

        Button btnLogOut = findViewById(R.id.btnLogoutAdminCategories);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogOut();
            }
        });
    }

    //print array of all moods for certain category
    private void printArray(Connection connect, GoogleMySQLConnectionHelper db, int[] images){
        moods = db.getAllMoods(connect);
        StringBuilder sb = new StringBuilder();
        int size = moods.size();
        boolean appendSeparator = false;
        for(int y=0; y < size; y++){
            if (appendSeparator)
                sb.append(','); // a comma
            appendSeparator = true;
            sb.append(moods.get(y));
        }
        ArrayAdapter<Suggestion> myAdapter = new MoodArrayAdapter(AdminMoodsForCategoryActivity.this, moods, images, categoryTitle);
        setListAdapter(myAdapter);
    }

    //function LogOut
    private void openLogOut(){
        Intent start = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(start);
    }
}
