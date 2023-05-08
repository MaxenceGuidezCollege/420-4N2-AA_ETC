package com.pam.ex4taxes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button calc;
    private EditText montant;
    private TextView tps;
    private TextView tvq;
    private TextView total;

    private String current;

    private void calc() {
        double val = Double.parseDouble(montant.getText().toString());
        double _total = TaxCalculator.calcTotal(val);
        double _tps = TaxCalculator.calcTps(val);
        double _tvq = TaxCalculator.calcTvq(val);

        total.setText(_total + "");
        tps.setText(_tps + "");
        tvq.setText(_tvq + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = findViewById(R.id.button);
        montant = findViewById(R.id.montant);
        tps = findViewById(R.id.tps);
        tvq = findViewById(R.id.tvq);
        total = findViewById(R.id.total);

        calc.setOnClickListener(v -> {
            this.calc();
            current = montant.getText().toString();
        });

        montant.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                // Check if the input has more than 2 decimal places
                String input = s.toString().trim();
                int decimalIndex = input.indexOf(".");
                if (decimalIndex != -1 && input.length() - decimalIndex > 3) {
                    // Remove the extra decimal places
                    s.replace(decimalIndex + 3, s.length(), "");
                }
                calc.setEnabled(input.length() > 0);
                if (!montant.getText().toString().equals(current)) {

                }
            }
        });


        if (savedInstanceState != null) {
            String _montant = savedInstanceState.getString("montant");
            String _tps = savedInstanceState.getString("tps");
            String _tvq = savedInstanceState.getString("tvq");
            String _total = savedInstanceState.getString("total");
            montant.setText(_montant);
            tvq.setText(_tvq);
            tps.setText(_tps);
            total.setText(_total);
        } else {
            montant.setText("10");
            calc();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("montant", montant.getText().toString());
        outState.putString("tps", tps.getText().toString());
        outState.putString("tvq", tvq.getText().toString());
        outState.putString("total", total.getText().toString());
    }
}