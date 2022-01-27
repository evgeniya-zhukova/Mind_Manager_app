package ca.gbc.comp3074.mind_manager_app.Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import ca.gbc.comp3074.mind_manager_app.Admin.AdminSuggestionsActivity;
import ca.gbc.comp3074.mind_manager_app.R;

public class MoodArrayAdapter extends ArrayAdapter<Suggestion> {

    private final Context context;
    private final List<Suggestion> values;
    private final int[] categoryImages;
    private final String category;

    public MoodArrayAdapter(@NonNull Context context, @NonNull List<Suggestion> objects, int[] categoryImages, String category) {
        super(context, R.layout.row_layout_categories, objects);
        this.context = context;
        this.values = objects;
        this.categoryImages = categoryImages;
        this.category = category;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.row_layout_moods_for_category, parent, false);

        ImageView images = rowView.findViewById(R.id.categoryImage2);
        setImage(images, category);

        TextView mood = rowView.findViewById(R.id.lblMoods);
        mood.setText(values.get(position).getMood());
        final String currMood = values.get(position).getMood();

        TextView currCategory = rowView.findViewById(R.id.lblCurrentCategory);
        currCategory.setText(category);

        ImageButton btnView = rowView.findViewById(R.id.btn_edit);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSuggestionsForCertainCategory(category, currMood);
            }
        });

        return rowView;
    }

    private void setImage(ImageView images, String category){
        switch(category){
            case "Sport":
                images.setImageResource(categoryImages[0]);
                break;
            case "Reading":
                images.setImageResource(categoryImages[2]);
                break;
            case "Music":
                images.setImageResource(categoryImages[3]);
                break;
            case "Movie":
                images.setImageResource(categoryImages[4]);
                break;
            case "Games":
                images.setImageResource(categoryImages[5]);
                break;
            default:
                images.setImageResource(categoryImages[1]);
        }
    }

    private void openSuggestionsForCertainCategory(String category, String mood){
        Intent start = new Intent(context.getApplicationContext(), AdminSuggestionsActivity.class);
        start.putExtra("category", category);
        start.putExtra("mood", mood);
        context.startActivity(start);
    }
}