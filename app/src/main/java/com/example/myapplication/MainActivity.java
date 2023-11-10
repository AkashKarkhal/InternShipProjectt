package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    TextView result_textview,solution_tv;
    MaterialButton btnc,btnopen_bracket,btn_closedbracket,btn_divide,btn_7,btn_8,btn_9,btn_mutli,btn_4,btn_5,btn_6,btn_add,
            btn_1,btn_2,btn_3,btn_sub,btn_AC,btn_0,btn_dot,btn_equals;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_textview = findViewById(R.id.result_textview);
        solution_tv = findViewById(R.id.solution_tv);

        assignId(btnc,R.id.btnC);
        assignId(btnopen_bracket,R.id.btn_openbracket);
        assignId(btn_closedbracket,R.id.btn_closedbracket);
        assignId(btn_divide,R.id.btn_divide);
        assignId(btn_7,R.id.btn_7);
        assignId(btn_8,R.id.btn_8);
        assignId(btn_9,R.id.btn_9);
        assignId(btn_mutli,R.id.btn_multi);
        assignId(btn_4,R.id.btn_4);
        assignId(btn_5,R.id.btn_5);
        assignId(btn_6,R.id.btn_6);
        assignId(btn_add,R.id.btn_add);
        assignId(btn_1,R.id.btn_1);
        assignId(btn_2,R.id.btn_2);
        assignId(btn_3,R.id.btn_3);
        assignId(btn_sub,R.id.btn_sub);
        assignId(btn_AC,R.id.btn_AC);
        assignId(btn_0,R.id.btn_0);
        assignId(btn_dot,R.id.btn_dot);
        assignId(btn_equals,R.id.btn_equals);



    }

    void assignId(MaterialButton btn , int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if (buttonText.equals(("AC"))){
            solution_tv.setText("");
            result_textview.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solution_tv.setText(result_textview.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        //dataToCalculate = dataToCalculate+buttonText;

        solution_tv.setText(dataToCalculate);

        String finalresult = getresult(dataToCalculate);

        if (!finalresult.equals("error")){
            result_textview.setText(finalresult);
        }


    }
    String getresult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
           String finalresult =  context.evaluateString(scriptable,data,"javascript",1,null).toString();
           return  finalresult;
        }catch (Exception e){
            return "error";
        }
    }
}