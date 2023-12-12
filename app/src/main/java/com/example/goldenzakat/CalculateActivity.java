package com.example.goldenzakat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar calcToolbar;
    EditText etWeight, etGoldValuePerGram;
    RadioButton radioKeep, radioWear;  // Separate RadioButtons
    Button btnCalculate, btnReset;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        calcToolbar = findViewById(R.id.calc_toolbar);
        setSupportActionBar(calcToolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        etWeight = findViewById(R.id.editTextNumberDecimal);
        etGoldValuePerGram = findViewById(R.id.editTextNumberDecimal2);
        radioKeep = findViewById(R.id.radioKeep);
        radioWear = findViewById(R.id.radioWear);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);
        tvOutput = findViewById(R.id.tvOutput);

        btnCalculate.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalculate) {
            try {
                String weightText = etWeight.getText().toString();
                String goldValuePerGramText = etGoldValuePerGram.getText().toString();

                if (weightText.isEmpty() || goldValuePerGramText.isEmpty() ||
                        (!radioKeep.isChecked() && !radioWear.isChecked())) {
                    throw new NumberFormatException("Please enter all required information.");
                }

                double weight = Double.parseDouble(weightText);
                double goldValuePerGram = Double.parseDouble(goldValuePerGramText);

                String typeOfGold;
                double X;

                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId == R.id.radioKeep) {
                    typeOfGold = "keep";
                    X = 85.0; // Set the keep weight
                } else if (selectedRadioButtonId == R.id.radioWear) {
                    typeOfGold = "wear";
                    X = 200.0; // Set the wear weight
                } else {
                    throw new IllegalStateException("Unexpected radio button state.");
                }

                // Calculate the total value of the gold
                double totalValue = weight * goldValuePerGram;

                // Calculate the total gold value that is zakat payable
                double totalGoldValueZakatPayable = (weight - X) * goldValuePerGram;

                // Calculate the total zakat
                double zakat = 0.025 * totalGoldValueZakatPayable;

                String result = String.format("Total Value of Gold: %.2f\n" +
                        "Total Gold Value Zakat Payable: %.2f\n" +
                        "Total Zakat: %.2f", totalValue, totalGoldValueZakatPayable, zakat);
                tvOutput.setText(result);
                tvOutput.setTextColor(getResources().getColor(android.R.color.black));

}catch (NumberFormatException nfe) {
                Toast.makeText(getApplicationContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == R.id.btnReset) {
            etWeight.setText("");
            etGoldValuePerGram.setText("");
            tvOutput.setText("");
            radioKeep.setChecked(false);
            radioWear.setChecked(false);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }

        if (item.getItemId() == R.id.item_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("type/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Visit my GitHub - https://github.com/kamalinannuar/GoldenZakapApp");
            startActivity(Intent.createChooser(shareIntent, null));
            return true;
        } else if (item.getItemId() == R.id.item_about) {
            Intent aboutIntent = new Intent (this, AboutActivity.class);
            startActivity(aboutIntent);
        }
        return false;
    }


}

