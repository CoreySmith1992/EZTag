package com.valex.eztag;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    EditText asset_input, make_input, model_input, current_user_input;
    Button add_asset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        asset_input = findViewById(R.id.asset_input);
        make_input = findViewById(R.id.make_input);
        model_input = findViewById(R.id.model_input);
        current_user_input = findViewById(R.id.current_user_input);
        add_asset = findViewById(R.id.add_asset);
        add_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                DatabaseHelper myDB = new DatabaseHelper(AddActivity.this);
                myDB.addAsset(Integer.valueOf(asset_input.getText().toString().trim()),
                        make_input.getText().toString().trim(),
                        model_input.getText().toString().trim(),
                        current_user_input.getText().toString().trim());

            }
        });
    }
}