package sg.edu.rp.c346.id20026955.moremovieslesson12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etGenre;
    EditText etYear;
    Spinner spinRating;
    Button btnInsert;
    Button btnShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spinRating = findViewById(R.id.spinner);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTitle.getText().toString().isEmpty() || etGenre.getText().toString().isEmpty() || etYear.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all field", Toast.LENGTH_SHORT).show();
                } else {
                    String title = etTitle.getText().toString();
                    String genre = etGenre.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    String rating = spinRating.getSelectedItem().toString();
                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long inserted_id = dbh.insertMovie(title, genre, year, rating);
                    if (inserted_id != -1) {
                        Toast.makeText(MainActivity.this, "Insert successful",
                                Toast.LENGTH_SHORT).show();
                    }
                    etTitle.setText("");
                    etGenre.setText("");
                    etYear.setText("");
                    spinRating.setSelection(0);
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}