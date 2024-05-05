package com.valex.eztag;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {

    EditText asset_input, make_input, model_input, current_user_input;
    Button update_button, delete_button;
    String id, asset, make, model, current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        asset_input = findViewById(R.id.asset_input2);
        make_input = findViewById(R.id.make_input2);
        model_input = findViewById(R.id.model_input2);
        current_user_input = findViewById(R.id.current_user_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        //Call First
        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call second
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                asset = asset_input.getText().toString().trim();
                make = make_input.getText().toString().trim();
                model = model_input.getText().toString().trim();
                current_user = current_user_input.getText().toString().trim();
                db.updateData(id, asset, make, model, current_user);
                finish();
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("asset") && getIntent().hasExtra("make") && getIntent().hasExtra("model") && getIntent().hasExtra("current_user")) {
            //Getting Data
            id = getIntent().getStringExtra("id");
            asset = getIntent().getStringExtra("asset");
            make = getIntent().getStringExtra("make");
            model = getIntent().getStringExtra("model");
            current_user = getIntent().getStringExtra("current_user");

            //Setting Data
            asset_input.setText(asset);
            make_input.setText(make);
            model_input.setText(model);
            current_user_input.setText(current_user);
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete asset " + asset + "?");
        builder.setMessage("Are you sure you want to delete asset " + asset + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }


}