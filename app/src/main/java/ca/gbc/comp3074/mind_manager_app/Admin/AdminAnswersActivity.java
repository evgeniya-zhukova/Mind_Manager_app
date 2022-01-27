package ca.gbc.comp3074.mind_manager_app.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.util.List;
import ca.gbc.comp3074.mind_manager_app.GoogleMySQLConnectionHelper;
import ca.gbc.comp3074.mind_manager_app.Models.Answer;
import ca.gbc.comp3074.mind_manager_app.Models.Question;
import ca.gbc.comp3074.mind_manager_app.R;

public class AdminAnswersActivity extends AppCompatActivity {
    Question question;
    List<Answer> answers;
    TextView lblError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_answers);

        //Database instance
        final GoogleMySQLConnectionHelper db = new GoogleMySQLConnectionHelper();
        final Connection connect = db.connectionclass();
        final TextView lblError = findViewById(R.id.lblError);

        final Intent intent = getIntent();
        final int questionID = intent.getIntExtra("id", 1);

        final TextView question_text = findViewById(R.id.lblNewQuestion);

        final TextView answer1 = findViewById(R.id.lblAnswer1);
        final TextView answer2 = findViewById(R.id.lblAnswer2);
        final TextView answer3 = findViewById(R.id.lblAnswer3);

        final TextView ans1Bored = findViewById(R.id.lblAns1Bored);
        final TextView ans1Energetic = findViewById(R.id.lblAns1Energ);
        final TextView ans1Happy = findViewById(R.id.lblAns1Happy);
        final TextView ans1Sad = findViewById(R.id.lblAns1Sad);
        final TextView ans1Tired = findViewById(R.id.lblAns1Tired);

        final TextView ans2Bored = findViewById(R.id.lblAns2Bored);
        final TextView ans2Energetic = findViewById(R.id.lblAns2Energ);
        final TextView ans2Happy = findViewById(R.id.lblAns2Happy);
        final TextView ans2Sad = findViewById(R.id.lblAns2Sad);
        final TextView ans2Tired = findViewById(R.id.lblAns2Tired);

        final TextView ans3Bored = findViewById(R.id.lblAns3Bored);
        final TextView ans3Energetic = findViewById(R.id.lblAns3Energ);
        final TextView ans3Happy = findViewById(R.id.lblAns3Happy);
        final TextView ans3Sad = findViewById(R.id.lblAns3Sad);
        final TextView ans3Tired = findViewById(R.id.lblAns3Tired);

        //Print text of question, answers and ratings for chosen question
        question = db.getQuestionByID(connect, questionID);
        question_text.setText(question.getQuestionText());

        answers = db.getAnswers(connect, questionID);
        answer1.setText(answers.get(0).getText());
        answer2.setText(answers.get(1).getText());
        answer3.setText(answers.get(2).getText());

        ans1Bored.setText(answers.get(0).getBoredRating()+"");
        ans1Energetic.setText(answers.get(0).getEnergeticRating()+"");
        ans1Happy.setText(answers.get(0).getHappyRating()+"");
        ans1Sad.setText(answers.get(0).getSadRating()+"");
        ans1Tired.setText(answers.get(0).getTiredRating()+"");

        ans2Bored.setText(answers.get(1).getBoredRating()+"");
        ans2Energetic.setText(answers.get(1).getEnergeticRating()+"");
        ans2Happy.setText(answers.get(1).getHappyRating()+"");
        ans2Sad.setText(answers.get(1).getSadRating()+"");
        ans2Tired.setText(answers.get(1).getTiredRating()+"");

        ans3Bored.setText(answers.get(2).getBoredRating()+"");
        ans3Energetic.setText(answers.get(2).getEnergeticRating()+"");
        ans3Happy.setText(answers.get(2).getHappyRating()+"");
        ans3Sad.setText(answers.get(2).getSadRating()+"");
        ans3Tired.setText(answers.get(2).getTiredRating()+"");

        //Button Submit add a new question
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question_text = ((EditText) findViewById(R.id.lblNewQuestion)).getText().toString();
                String answer1 = ((EditText) findViewById(R.id.lblAnswer1)).getText().toString();
                String answer2 = ((EditText) findViewById(R.id.lblAnswer2)).getText().toString();
                String answer3 = ((EditText) findViewById(R.id.lblAnswer3)).getText().toString();

               if (question_text.equals("") || answer1.equals("") || answer2.equals("") || answer3.equals("")
                        || ans1Bored.getText().toString().equals("") || ans1Energetic.getText().toString().equals("")
                        || ans1Happy.getText().toString().equals("") || ans1Sad.getText().toString().equals("")
                        || ans1Tired.getText().toString().equals("") || ans2Bored.getText().toString().equals("")
                        || ans2Energetic.getText().toString().equals("") || ans2Happy.getText().toString().equals("")
                        || ans2Sad.getText().toString().equals("") || ans2Tired.getText().toString().equals("")
                        || ans3Bored.getText().toString().equals("") || ans3Energetic.getText().toString().equals("")
                        || ans3Happy.getText().toString().equals("") || ans3Sad.getText().toString().equals("")
                        || ans3Tired.getText().toString().equals("")){
                    lblError.setText("All fields are required");
                } else{
                    final int ans1Bored = Integer.parseInt(((EditText) findViewById(R.id.lblAns1Bored)).getText().toString());
                    final int ans1Energetic = Integer.parseInt(((EditText) findViewById(R.id.lblAns1Energ)).getText().toString());
                    final int ans1Happy = Integer.parseInt(((EditText) findViewById(R.id.lblAns1Happy)).getText().toString());
                    final int ans1Sad = Integer.parseInt(((EditText) findViewById(R.id.lblAns1Sad)).getText().toString());
                    final int ans1Tired = Integer.parseInt(((EditText) findViewById(R.id.lblAns1Tired)).getText().toString());

                    final int ans2Bored = Integer.parseInt(((EditText) findViewById(R.id.lblAns2Bored)).getText().toString());
                    final int ans2Energetic = Integer.parseInt(((EditText) findViewById(R.id.lblAns2Energ)).getText().toString());
                    final int ans2Happy = Integer.parseInt(((EditText) findViewById(R.id.lblAns2Happy)).getText().toString());
                    final int ans2Sad = Integer.parseInt(((EditText) findViewById(R.id.lblAns2Sad)).getText().toString());
                    final int ans2Tired = Integer.parseInt(((EditText) findViewById(R.id.lblAns2Tired)).getText().toString());

                    final int ans3Bored = Integer.parseInt(((EditText) findViewById(R.id.lblAns3Bored)).getText().toString());
                    final int ans3Energetic = Integer.parseInt(((EditText) findViewById(R.id.lblAns3Energ)).getText().toString());
                    final int ans3Happy = Integer.parseInt(((EditText) findViewById(R.id.lblAns3Happy)).getText().toString());
                    final int ans3Sad = Integer.parseInt(((EditText) findViewById(R.id.lblAns3Sad)).getText().toString());
                    final int ans3Tired = Integer.parseInt(((EditText) findViewById(R.id.lblAns3Tired)).getText().toString());
                    //Insert new question
                    Question questionEdited = new Question(question_text+"");
                    db.editQuestion(connect, questionEdited, questionID);
                    Answer ans1 = new Answer (questionID, answer1+"",
                            ans1Bored, ans1Energetic, ans1Happy, ans1Sad, ans1Tired);
                    Answer ans2 = new Answer (questionID, answer2+"",
                            ans2Bored, ans2Energetic, ans2Happy, ans2Sad, ans2Tired);
                    Answer ans3 = new Answer (questionID, answer3+"",
                            ans3Bored, ans3Energetic, ans3Happy, ans3Sad, ans3Tired);
                    db.editAnswer(connect, ans1, answers.get(0).getId());
                    db.editAnswer(connect, ans2, answers.get(1).getId());
                    db.editAnswer(connect, ans3, answers.get(2).getId());
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