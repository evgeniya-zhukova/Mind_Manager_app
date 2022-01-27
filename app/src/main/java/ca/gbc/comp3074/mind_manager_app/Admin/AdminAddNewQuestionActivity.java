package ca.gbc.comp3074.mind_manager_app.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import ca.gbc.comp3074.mind_manager_app.GoogleMySQLConnectionHelper;
import ca.gbc.comp3074.mind_manager_app.Models.Answer;
import ca.gbc.comp3074.mind_manager_app.Models.Question;
import ca.gbc.comp3074.mind_manager_app.R;

public class AdminAddNewQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_question);

        //Database instance
        final GoogleMySQLConnectionHelper db = new GoogleMySQLConnectionHelper();
        final Connection connect = db.connectionclass();
        final TextView lblError = findViewById(R.id.lblError);

        //Button Submit add a new question
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQuestion = ((EditText) findViewById(R.id.lblNewQuestion)).getText().toString();
                String answer1 = ((EditText) findViewById(R.id.lblAnswer1)).getText().toString();
                String answer2 = ((EditText) findViewById(R.id.lblAnswer2)).getText().toString();
                String answer3 = ((EditText) findViewById(R.id.lblAnswer3)).getText().toString();

                String ans1Bored = ((EditText) findViewById(R.id.lblAns1Bored)).getText().toString();
                String ans1Energetic = ((EditText) findViewById(R.id.lblAns1Energ)).getText().toString();
                String ans1Happy = ((EditText) findViewById(R.id.lblAns1Happy)).getText().toString();
                String ans1Sad = ((EditText) findViewById(R.id.lblAns1Sad)).getText().toString();
                String ans1Tired = ((EditText) findViewById(R.id.lblAns1Tired)).getText().toString();

                String ans2Bored = ((EditText) findViewById(R.id.lblAns2Bored)).getText().toString();
                String ans2Energetic = ((EditText) findViewById(R.id.lblAns2Energ)).getText().toString();
                String ans2Happy = ((EditText) findViewById(R.id.lblAns2Happy)).getText().toString();
                String ans2Sad = ((EditText) findViewById(R.id.lblAns2Sad)).getText().toString();
                String ans2Tired = ((EditText) findViewById(R.id.lblAns2Tired)).getText().toString();

                String ans3Bored = ((EditText) findViewById(R.id.lblAns3Bored)).getText().toString();
                String ans3Energetic = ((EditText) findViewById(R.id.lblAns3Energ)).getText().toString();
                String ans3Happy = ((EditText) findViewById(R.id.lblAns3Happy)).getText().toString();
                String ans3Sad = ((EditText) findViewById(R.id.lblAns3Sad)).getText().toString();
                String ans3Tired = ((EditText) findViewById(R.id.lblAns3Tired)).getText().toString();

                //Execute the query, find if username input from user is existing in data base
                Question questionExist = db.getQuestionByText(connect, newQuestion);

                if(questionExist != null){
                    lblError.setText("This question is already exist");
                }
                else if (newQuestion.equals("") || answer1.equals("") || answer2.equals("")
                        || answer3.equals("") || ans1Bored.equals("") || ans1Energetic.equals("")
                        || ans1Happy.equals("") || ans1Sad.equals("") || ans1Tired.equals("")
                        || ans2Bored.equals("") || ans2Energetic.equals("") || ans2Happy.equals("")
                        || ans2Sad.equals("") || ans2Tired.equals("") || ans3Bored.equals("")
                        || ans3Energetic.equals("") || ans3Happy.equals("") || ans3Sad.equals("")
                        || ans3Tired.equals("")){
                    lblError.setText("All fields are required");
                } else{
                    final int ans1BoredNum = Integer.parseInt(ans1Bored);
                    final int ans1EnergeticNum = Integer.parseInt(ans1Energetic);
                    final int ans1HappyNum = Integer.parseInt(ans1Happy);
                    final int ans1SadNum = Integer.parseInt(ans1Sad);
                    final int ans1TiredNum = Integer.parseInt(ans1Tired);

                    final int ans2BoredNum = Integer.parseInt(ans2Bored);
                    final int ans2EnergeticNum = Integer.parseInt(ans2Energetic);
                    final int ans2HappyNum = Integer.parseInt(ans2Happy);
                    final int ans2SadNum = Integer.parseInt(ans2Sad);
                    final int ans2TiredNum = Integer.parseInt(ans2Tired);

                    final int ans3BoredNum = Integer.parseInt(ans3Bored);
                    final int ans3EnergeticNum = Integer.parseInt(ans3Energetic);
                    final int ans3HappyNum = Integer.parseInt(ans3Happy);
                    final int ans3SadNum = Integer.parseInt(ans3Sad);
                    final int ans3TiredNum = Integer.parseInt(ans3Tired);
                    //Insert new question
                    Question question = new Question(newQuestion+"");
                    db.addQuestion(connect, question);
                    questionExist = db.getQuestionByText(connect, question.getQuestionText()+"");
                    int questionID = questionExist.getID();
                    Answer ans1 = new Answer (questionID, answer1+"",
                            ans1BoredNum, ans1EnergeticNum, ans1HappyNum, ans1SadNum, ans1TiredNum);
                    Answer ans2 = new Answer (questionID, answer2+"",
                            ans2BoredNum, ans2EnergeticNum, ans2HappyNum, ans2SadNum, ans2TiredNum);
                    Answer ans3 = new Answer (questionID, answer3+"",
                            ans3BoredNum, ans3EnergeticNum, ans3HappyNum, ans3SadNum, ans3TiredNum);
                    db.addAnswer(connect, ans1);
                    db.addAnswer(connect, ans2);
                    db.addAnswer(connect, ans3);
                    backToAdminQuestionnaire();
                }
            }
        });

        //Button Cancel goes back to AdminQuestionnaireActivity
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToAdminQuestionnaire();
            }
        });
    }

    //function to start AdminQuestionnaireActivity
    private void backToAdminQuestionnaire(){
        Intent start = new Intent(getApplicationContext(), AdminQuestionnaireActivity.class);
        startActivity(start);
    }
}