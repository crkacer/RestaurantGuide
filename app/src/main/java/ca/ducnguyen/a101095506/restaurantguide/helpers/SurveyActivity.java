package ca.ducnguyen.a101095506.restaurantguide.helpers;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import android.content.Context;
import java.io.OutputStreamWriter;
import java.io.File;

public class SurveyActivity extends AppCompatActivity {

    Button btnContinue;
    TextView tv_question;
    EditText et_answer;


    List<Survey_Item> list_questions = new ArrayList<Survey_Item>();


    Survey_Item item = new Survey_Item("", "");
    List<Survey_Item> questions;



    int curQuestion = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        list_questions.add(new Survey_Item(SurveyHelper.questions[0], SurveyHelper.answers[0]));
        list_questions.add(new Survey_Item(SurveyHelper.questions[1], SurveyHelper.answers[1]));
        list_questions.add(new Survey_Item(SurveyHelper.questions[2], SurveyHelper.answers[2]));
        list_questions.add(new Survey_Item(SurveyHelper.questions[3], SurveyHelper.answers[3]));
        list_questions.add( new Survey_Item(SurveyHelper.questions[4], SurveyHelper.answers[4]));
        list_questions.add(new Survey_Item(SurveyHelper.questions[5], SurveyHelper.answers[5]));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnContinue = (Button) findViewById(R.id.btnContinue);
        tv_question = (TextView) findViewById(R.id.tv_question);
        et_answer = (EditText) findViewById(R.id.et_answer);




        btnContinue.setVisibility(View.INVISIBLE);

        questions = new ArrayList<>();



        //adding set of questions to survey

        for (int i = 0; i < SurveyHelper.questions.length; i++) {
            questions.add(new Survey_Item(SurveyHelper.questions[i], SurveyHelper.answers[i]));
        }

        tv_question.setText(questions.get(curQuestion).getQuestion());




        et_answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                btnContinue.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (curQuestion < SurveyHelper.questions.length - 1){
                    curQuestion++;
                    tv_question.setText(questions.get(curQuestion).getQuestion());
                    btnContinue.setVisibility(View.VISIBLE );

                    et_answer.setText("");


                } else {
                    setContentView(R.layout.activity_survey_end);

                }

                writeToFile(new Date().toString() +"\t"+ et_answer.getText().toString()+"\n", getApplicationContext());

            }


        });
    }

    private void writeToFile(String data,Context context) {
        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("survey.txt", Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        catch (IOException e) {
            System.out.print("File write failed:  " + e.toString());
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
