package sg.edu.rp.c346.id20026955.moremovieslesson12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movies> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle1);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre1);
        TextView tvYear = rowView.findViewById(R.id.tvYear1);
        ImageView iv = rowView.findViewById(R.id.imageView);

        Movies currentMovie = movieList.get(position);

        tvTitle.setText(currentMovie.getTitle());
        tvGenre.setText(currentMovie.getGenre());
        tvYear.setText(currentMovie.getYear() + "");

        String rating = currentMovie.getRating();

        if (rating.equalsIgnoreCase("G")){
            iv.setImageResource(R.drawable.rating_g);
        } else if (rating.equalsIgnoreCase("PG")) {
            iv.setImageResource(R.drawable.rating_pg);
        } else if (rating.equalsIgnoreCase("PG13")) {
            iv.setImageResource(R.drawable.rating_pg13);
        } else if (rating.equalsIgnoreCase("NC16")) {
            iv.setImageResource(R.drawable.rating_nc16);
        } else if (rating.equalsIgnoreCase("M18")) {
            iv.setImageResource(R.drawable.rating_m18);
        } else {
            iv.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }
}
