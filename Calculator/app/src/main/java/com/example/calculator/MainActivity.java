package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        TextView t = (TextView) findViewById(R.id.EditText);
        String res = "";
        res = t.getText().toString();
        switch (view.getId()) {
            case R.id.b0:
                res += "0";
                t.setText(res);
                break;
            case R.id.b1:
                res += "1";
                t.setText(res);
                break;
            case R.id.b2:
                res += "2";
                t.setText(res);
                break;
            case R.id.b3:
                res += "3";
                t.setText(res);
                break;
            case R.id.b4:
                res += "4";
                t.setText(res);
                break;
            case R.id.b5:
                res += "5";
                t.setText(res);
                break;
            case R.id.b6:
                res += "6";
                t.setText(res);
                break;
            case R.id.b7:
                res += "7";
                t.setText(res);
                break;
            case R.id.b8:
                res += "8";
                t.setText(res);
                break;
            case R.id.b9:
                res += "9";
                t.setText(res);
                break;
            case R.id.bmul:
                res += "*";
                t.setText(res);
                break;
            case R.id.bdiv:
                res += "/";
                t.setText(res);
                break;
            case R.id.bsub:
                res += "-";
                t.setText(res);
                break;
            case R.id.badd:
                res += "+";
                t.setText(res);
                break;
            case R.id.bd:
                res += ".";
                t.setText(res);
                break;
            case R.id.buttonclear:
                res = "";
                t.setText(res);
                TextView r = (TextView) findViewById(R.id.result);
                r.setText(res);
                break;
            case R.id.buttondel:
                if(res.length() > 0){
                    res = res.substring(0, res.length()-1);
                    t.setText(res);
                }
                break;
        }
    }
    public float calculate(Character operator, float op1, float op2){
        if(operator == '+') {
            return op1 + op2;
        }
        else if(operator == '-') {
            return op1 - op2;
        }
        else if(operator == '*') {
            return op1 * op2;
        }
        else if(operator == '/') {
            if (op2 != 0) {
                return op1/op2;
            } else {
                return 0;
            }
        }
        else {
            return 0;
        }
    }
    public boolean isValidOperator(Character c){
        if ( c == '+'){
            return true;
        } else if( c == '-'){
            return true;
        }  else if( c == '*'){
            return true;
        }  else if( c == '/'){
            return true;
        } else {
            return false;
        }
    }

    public String reverseString(String s){
        String n = "";
        for(int i = s.length()-1 ; i>=0; --i){
            n += s.charAt(i);
        }
        return n;
    }
    //Function to check for Expression Validity
    public boolean checkExpression(String s){
        if(s.length() == 0 || isValidOperator(s.charAt(s.length()-1)) || isValidOperator(s.charAt(0))){
            return false;
        }
        for(int i=0;i < s.length(); ++i){
            if(isValidOperator(s.charAt(i))){
                if(isValidOperator(s.charAt(i-1) ) || isValidOperator(s.charAt(i+1)) ){
                    return false;
                }
            }
        }
        return true;
    }
    public void renderResult(Float result){
        TextView t = (TextView) findViewById(R.id.result);
        String res = "";
        res += "Res : " + String.valueOf(result);
        t.setText(res);
    }
    public void onEqual(View view){
        TextView t = (TextView) findViewById(R.id.EditText);
        String expression = "";
        expression = t.getText().toString().trim();
        if(checkExpression(expression)){
            {
                float op1, op2;
                Character exp;
                Stack<Float> numStack = new Stack<>();
                Stack<Character> oprStack = new Stack<>();
                for (int i = expression.length() - 1; i >= 0; i--) {
                    if ((expression.charAt(i) >= 48 && expression.charAt(i) <= 57) || expression.charAt(i) == '.') {
                        //                    int count = 0;
                        //                    float num = Float.parseFloat(String.valueOf(expression.charAt(i)));
                        int k = Integer.parseInt(String.valueOf(expression.charAt(i)));
                        String f = "";
                        f += String.valueOf(k);
                        while ((i - 1 >= 0) && !isValidOperator(expression.charAt(i - 1))) {
                            f += String.valueOf(expression.charAt(i - 1));
                            i--;
                        }
                        f = reverseString(f);
                        numStack.push(Float.valueOf(f));
                    } else {
                        oprStack.push(expression.charAt(i));
                    }
                }
                System.out.println("Stack length: " + numStack.size());
                System.out.println("Operator length: " + oprStack.size());
                while (!oprStack.isEmpty()) {
                    float expressionResult = 0;
                    op1 = numStack.pop();
                    op2 = numStack.pop();
                    exp = oprStack.pop();
                    System.out.println(op1 + " || " + exp + " || " + op2);
                    expressionResult = calculate(exp, op1, op2);
                    System.out.println("Expression Result: " + expressionResult);
                    numStack.push(expressionResult);
                }
                renderResult(numStack.pop());
                if (numStack.isEmpty()) {
                    System.out.println("Num Array is Empty !!!");
                }

                System.out.println("STack lenght: " + numStack.size());
                System.out.println("Operator length: " + oprStack.size());

                for (Float i : numStack) {
                    System.out.println(i);
                }
                for (Character i : oprStack) {
                    System.out.println(i);
                }
            }
        }
        else {
            Toast tx = Toast.makeText(getApplicationContext(), "Error : Wrong Expression", Toast.LENGTH_SHORT);
            tx.show();
        }
    }

   public void onExit(View view){
       finish();
    }

    }
