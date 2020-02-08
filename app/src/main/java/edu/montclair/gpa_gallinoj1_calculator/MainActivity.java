package edu.montclair.gpa_gallinoj1_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText grade1;
    EditText grade2;
    EditText grade3;
    EditText grade4;
    EditText grade5;

    int[] gradeValues = new int[5];
    float GPAvalue;


    ConstraintLayout layout;
    TextView calculatedGPA;
    Button bigbutton;

    public boolean fieldsAreFull() {
        if(grade1.getText().toString().isEmpty() || grade2.getText().toString().isEmpty() ||
                grade3.getText().toString().isEmpty() || grade4.getText().toString().isEmpty()
                || grade5.getText().toString().isEmpty()){

            Toast.makeText(MainActivity.this, "Cannot have empty fields!", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    public void fillGradearray(){
        gradeValues[0] = Integer.parseInt(grade1.getText().toString());
        gradeValues[1] = Integer.parseInt(grade2.getText().toString());
        gradeValues[2] = Integer.parseInt(grade3.getText().toString());
        gradeValues[3] = Integer.parseInt(grade4.getText().toString());
        gradeValues[4] = Integer.parseInt(grade5.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grade1 = (EditText) findViewById(R.id.grade1);
        grade1.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});

        grade2 = (EditText) findViewById(R.id.grade2);
        grade2.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});

        grade3 = (EditText) findViewById(R.id.grade3);
        grade3.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});

        grade4 = (EditText) findViewById(R.id.grade4);
        grade4.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});

        grade5 = (EditText) findViewById(R.id.grade5);
        grade5.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});

        layout = (ConstraintLayout) findViewById(R.id.layout);
        calculatedGPA = (TextView) findViewById(R.id.calculatedGPA);
        bigbutton = (Button) findViewById(R.id.bigbutton);

        final String btnCalc = getString(R.string.bigButton);
        final String btnClear = getString(R.string.bigButton2);

        bigbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bigbutton.getText().toString().equalsIgnoreCase(btnCalc)){
                    if (fieldsAreFull()) {
                        fillGradearray();
                        GPAvalue = 0;
                        for (int i=0; i<5; i++){
                            GPAvalue += gradeValues[i];
                        }
                        GPAvalue /= 5;
                        changeColor(Math.round(GPAvalue));
                        String GPAstring = String.valueOf(Math.round(GPAvalue)+"%");
                        calculatedGPA.setText(GPAstring);
                        bigbutton.setText(R.string.bigButton2);
                    };
                }
                else if(bigbutton.getText().toString().equalsIgnoreCase(btnClear)){
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                    calculatedGPA.setText("0%");
                    grade1.setText("");
                    grade2.setText("");
                    grade3.setText("");
                    grade4.setText("");
                    grade5.setText("");
                    bigbutton.setText(R.string.bigButton);
                }
            }

            private void changeColor(int gpa) {
                if(gpa >= 80){
                    layout.setBackgroundColor(getResources().getColor(R.color.green));
                }
                else if(gpa < 80 & gpa >60){
                    layout.setBackgroundColor(getResources().getColor(R.color.yellow));
                }
                else if (gpa < 60){
                    layout.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });
    }
}
