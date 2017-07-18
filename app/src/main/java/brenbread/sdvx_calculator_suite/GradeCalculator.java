package brenbread.sdvx_calculator_suite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by Bren Bread on 7/16/2017.
 */

public class GradeCalculator extends Fragment implements View.OnClickListener{

    public EditText totalChain;

    public Button calcButton;
    public Button resetButton;

    public TextView outS;
    public TextView outAAAplus;
    public TextView outAAA;
    public TextView outAAplus;
    public TextView outAA;
    public TextView outAplus;
    public TextView outA;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grade_calculator, container, false);
        totalChain = (EditText) view.findViewById(R.id.totalChain);
        outS = (TextView) view.findViewById(R.id.outS);
        outAAAplus = (TextView) view.findViewById(R.id.outAAAplus);
        outAAA = (TextView) view.findViewById(R.id.outAAA);
        outAAplus = (TextView) view.findViewById(R.id.outAAplus);
        outAA = (TextView) view.findViewById(R.id.outAA);
        outAplus = (TextView) view.findViewById(R.id.outAplus);
        outA = (TextView) view.findViewById(R.id.outA);

        calcButton = (Button) view.findViewById(R.id.calcButton);
        resetButton = (Button) view.findViewById(R.id.resetButton);

        calcButton.setOnClickListener(GradeCalculator.this);
        resetButton.setOnClickListener(GradeCalculator.this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.calcButton:
                String totalChainIn = totalChain.getText().toString(); //user input

                if (totalChainIn=="") //checks if input is empty
                {
                    outS.setText("Input each field");
                    outAAAplus.setText("");
                    outAAA.setText("");
                    outAAplus.setText("");
                    outAA.setText("");
                    outAplus.setText("");
                    outA.setText("");
                } else
                { //there is input in all fields
                    int totalChainInt = Integer.parseInt(totalChainIn); //parse string to int

                    //grade S
                    int S_value = gradeValue(9900000, totalChainInt);
                    int S_errValue = S_value/2;
                    outS.setText(output(S_value, S_errValue));

                    //grade AAA+
                    int AAAplus_value = gradeValue(9800000, totalChainInt);
                    int AAAplus_errValue = AAAplus_value/2;
                    outAAAplus.setText(output(AAAplus_value, AAAplus_errValue));

                    //grade AAA
                    int AAA_value = gradeValue(9700000, totalChainInt);
                    int AAA_errValue = AAA_value/2;
                    outAAA.setText(output(AAA_value, AAA_errValue));

                    //grade AA+
                    int AAplus_value = gradeValue(9500000, totalChainInt);
                    int AAplus_errValue = AAplus_value/2;
                    outAAplus.setText(output(AAplus_value, AAplus_errValue));

                    //grade AA
                    int AA_value = gradeValue(9300000, totalChainInt);
                    int AA_errValue = AA_value/2;
                    outAA.setText(output(AA_value, AA_errValue));

                    //grade A+
                    int Aplus_value = gradeValue(9000000, totalChainInt);
                    int Aplus_errValue = Aplus_value/2;
                    outAplus.setText(output(Aplus_value, Aplus_errValue));

                    //grade A
                    int A_value = gradeValue(8700000, totalChainInt);
                    int A_errValue = A_value/2;
                    outA.setText(output(A_value, A_errValue));

                }
                break;

            case R.id.resetButton:
                totalChain.setText("");
                outS.setText("");
                outAAAplus.setText("");
                outAAA.setText("");
                outAAplus.setText("");
                outAA.setText("");
                outAplus.setText("");
                outA.setText("");
                break;
        }
    }

    //Input grade-score floor. 9900000 = S, 9800000 = AAA+, etc.
    //returns max near count for certain chain
    int gradeValue (int grade, int totalNotesIn) {
        BigDecimal totalNotes = new BigDecimal(totalNotesIn);
        BigDecimal maxScore = new BigDecimal(10000000);
        BigDecimal criticalValue = maxScore.divide(totalNotes); //critical value. 10mil/totalNotes
        BigDecimal nearValue = criticalValue.divide(new BigDecimal(2)); //half of critical value
        BigDecimal near_BD_Val = (maxScore.subtract(new BigDecimal(grade))).divide(nearValue); //(10mil - grade score)/nearValue

        return near_BD_Val.intValue();

    }

    String output (int nearValue, int errorValue) {
        return (nearValue + " nears, " + errorValue + " errors");
    }
}
