package ca.gbc.comp3074.mind_manager_app.Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.sql.Connection;
import java.util.List;
import ca.gbc.comp3074.mind_manager_app.Admin.AdminAnswersActivity;
import ca.gbc.comp3074.mind_manager_app.Admin.AdminSuggestionsActivity;
import ca.gbc.comp3074.mind_manager_app.GoogleMySQLConnectionHelper;
import ca.gbc.comp3074.mind_manager_app.R;

public class AdminSuggestionArrayAdapter extends ArrayAdapter<Suggestion> {

    private final Context context;
    private final List<Suggestion> values;

    public AdminSuggestionArrayAdapter(@NonNull Context context, @NonNull List<Suggestion> objects) {
        super(context, R.layout.row_layout_admin_suggestoins, objects);
        this.context = context;
        this.values = objects;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.row_layout_admin_suggestoins, parent, false);

        final int ID = values.get(position).getID();
        final String category = values.get(position).getCategoryName();
        final String mood = values.get(position).getMood();

        TextView question = rowView.findViewById(R.id.lblSuggestions);
        question.setText(values.get(position).getSuggestionName());

        ImageButton btnView = rowView.findViewById(R.id.btn_edit);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSuggestion(position, ID, category, mood);
            }
        });

        ImageButton btnDelete = rowView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSuggestions(ID, category, mood);
            }
        });

        return rowView;
    }

    //function edit Suggestions
    private void editSuggestion(int position, int id, String category, String mood){
        Intent start = new Intent(context.getApplicationContext(), AdminSuggestionsActivity.class);
        start.putExtra("category", category);
        start.putExtra("mood", mood);
        start.putExtra("id", id);
        start.putExtra("suggestion", values.get(position).getSuggestionName());
        start.putExtra("youtubeLink", values.get(position).getYoutubeLink());
        context.startActivity(start);
    }

    //function delete Suggestions
    private void deleteSuggestions(int id, String category, String mood){
        //Database instance
        final GoogleMySQLConnectionHelper db = new GoogleMySQLConnectionHelper();
        final Connection connect = db.connectionclass();
        Intent start = new Intent(context.getApplicationContext(), AdminSuggestionsActivity.class);
        start.putExtra("category", category);
        start.putExtra("mood", mood);
        db.deleteSuggestion(connect, id);
        context.startActivity(start);
    }
}