package sg.edu.rp.c346.id20026955.moremovieslesson12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btnPG13;
    ListView lv;
    CustomAdapter ca;
    ArrayList<Movies> filteredList;
    ArrayList<Movies> pg13List;
    Boolean clicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = findViewById(R.id.lv);
        btnPG13 = findViewById(R.id.btnPG13);

        filteredList = new ArrayList<Movies>();
        pg13List = new ArrayList<Movies>();

        ca = new CustomAdapter(this, R.layout.row, filteredList);
        lv.setAdapter(ca);

        DBHelper dbh = new DBHelper(SecondActivity.this);
        pg13List.clear();
        pg13List.addAll(dbh.getAllMovies());
        filteredList.addAll(dbh.getAllMovies());
        ca.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movies data = filteredList.get(position);
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = !clicked;
                filteredList.clear();
                if (clicked) {
                    btnPG13.setText("Display all movies");
                    for (Movies item : pg13List) {
                        if (item.getRating().equals("PG13")) {
                            filteredList.add(item);
                        }
                    }
                } else {
                    btnPG13.setText("Show all PG13 movies");
                    filteredList.addAll(pg13List);
                }
                ca.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(SecondActivity.this);
        pg13List.clear();
        filteredList.clear();
        pg13List.addAll(dbh.getAllMovies());
        filteredList.addAll(pg13List);
        ca.notifyDataSetChanged();
    }
}