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
import ca.gbc.comp3074.mind_manager_app.Admin.AdminMoodsForCategoryActivity;
import ca.gbc.comp3074.mind_manager_app.R;

public class CategoryArrayAdapter extends ArrayAdapter<Suggestion> {

    private final Context context;
    private final List<Suggestion> values;
    private final int[] categoryImages;

    public CategoryArrayAdapter(@NonNull Context context, @NonNull List<Suggestion> objects, int[] categoryImages) {
        super(context, R.layout.row_layout_categories, objects);
        this.context = context;
        this.values = objects;
        this.categoryImages = categoryImages;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.row_layout_categories, parent, false);

        ImageView images = rowView.findViewById(R.id.categoryImage2);
        final String currCategory = values.get(position).getCategoryName();
        setImage(images, currCategory);

        TextView category = rowView.findViewById(R.id.lblCategories);
        category.setText(values.get(position).getCategoryName());

        ImageButton btnView = rowView.findViewById(R.id.btn_edit);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMoodsForCertainCategory(currCategory);
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

    private void openMoodsForCertainCategory(String currCategory){
        Intent start = new Intent(context.getApplicationContext(), AdminMoodsForCategoryActivity.class);
        start.putExtra("category", currCategory);
        context.startActivity(start);
    }
}