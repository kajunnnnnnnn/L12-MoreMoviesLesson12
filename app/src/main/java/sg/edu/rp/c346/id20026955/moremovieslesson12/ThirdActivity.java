package sg.edu.rp.c346.id20026955.moremovieslesson12;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etId, etTitle2, etGenre2, etYear2;
    Spinner spinUpdate;
    Button btnUpdate, btnDelete, btnCancel;
    Movies data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        etId = findViewById(R.id.etId);
        etTitle2 = findViewById(R.id.etTitle2);
        etGenre2 = findViewById(R.id.etGenre2);
        etYear2 = findViewById(R.id.etYear2);
        spinUpdate = findViewById(R.id.spinner2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        String id = String.valueOf(data.getId());

        etId.setText(id);
        etTitle2.setText(data.getTitle());
        etGenre2.setText(data.getGenre());
        etYear2.setText(data.getYear() + "");

        for (int x = 0; x < spinUpdate.getCount(); x++) {
            if (spinUpdate.getItemAtPosition(x).toString().equalsIgnoreCase(data.getRating())) {
                spinUpdate.setSelection(x);
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setTitle(etTitle2.getText().toString());
                data.setGenre(etGenre2.getText().toString());
                data.setYear(Integer.parseInt(etYear2.getText().toString()));
                data.setRating(spinUpdate.getSelectedItem().toString());
                dbh.updateMovie(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie " + data.getTitle());
                myBuilder.setCancelable(false);

                //Configure the "positive" button
                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbh.deleteMovie(data.getId());
                        finish();
                    }
                });
                myBuilder.setPositiveButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want discard the changes");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Do not discard", null);
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog mydialog = myBuilder.create();
                mydialog.show();
            }
        });
    }
}
