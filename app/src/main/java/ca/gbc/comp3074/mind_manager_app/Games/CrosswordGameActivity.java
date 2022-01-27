package ca.gbc.comp3074.mind_manager_app.Games;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.share.widget.ShareButton;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ca.gbc.comp3074.mind_manager_app.AboutActivity;
import ca.gbc.comp3074.mind_manager_app.MainActivity;
import ca.gbc.comp3074.mind_manager_app.R;

public class CrosswordGameActivity extends AppCompatActivity {

    int counter = 4;
    int addToArrayIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_crossword);

        // Arrays with the images

        int[] calmerArray = {R.drawable.crossword_calmer,
                R.drawable.crossword_calmer2,
                R.drawable.crossword_calmer3};

        int[] happierArray = {R.drawable.crossword_happier,
                R.drawable.crossword_happier2,
                R.drawable.crossword_happier3};

        int[] energeticArray = {R.drawable.crossword_energetic,
                R.drawable.crossword_energetic2,
                R.drawable.crossword_energetic3};

        int[] moodyArray = {R.drawable.crossword_moody,
                R.drawable.crossword_moody2,
                R.drawable.crossword_moody3};

        int[] relaxedArray = {R.drawable.crossword_relaxed,
                R.drawable.crossword_relaxed2,
                R.drawable.crossword_relaxed3};

        final String[] foundWords = {};



        final String[] moody = {"overwhelm", "emotional", "reflective", "melancholy"};
        final String[] happier = {"cheerful", "ecstatic", "overjoyed", "joyful"};
        final String[] energetic = {"active", "dynamic", "spirited", "tireless"};
        final String[] calmer = {"serene", "soothing", "tranquil", "pacific"};
        final String[] relaxed = {"casual", "laid back", "tranquil", "patient"};


        final TextView lblResult = findViewById(R.id.lblResultGame);
        final EditText editUserInput = findViewById(R.id.editInputNumberCross);
        final TextView lblFoundWords = findViewById(R.id.lblWordsFound);

        ImageView crossworPic = findViewById(R.id.crosswordPic);

        Random random = new Random();
        int imageIndex = 0;

        Intent intent = getIntent();
        final String moodTitle = intent.getStringExtra("game");

        switch (moodTitle) {
            case "Calmer":
                imageIndex = random.nextInt(calmerArray.length);
                crossworPic.setImageResource(calmerArray[imageIndex]);
                break;
            case "Happier":
                imageIndex = random.nextInt(happierArray.length);
                crossworPic.setImageResource(happierArray[imageIndex]);
                break;
            case "Moody":
                imageIndex = random.nextInt(moodyArray.length);
                crossworPic.setImageResource(moodyArray[imageIndex]);
                break;
            case "Energetic":
                imageIndex = random.nextInt(energeticArray.length);
                crossworPic.setImageResource(energeticArray[imageIndex]);
                break;
            default:
                imageIndex = random.nextInt(relaxedArray.length);
                crossworPic.setImageResource(relaxedArray[imageIndex]);
                break;

        }

        final Button btnImDone = findViewById(R.id.btnImDone);
        btnImDone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String userInput = editUserInput.getText().toString().toLowerCase();

                switch (moodTitle) {
                    case "Calmer":
                        boolean isExistsCalmer =  wordExists(userInput, calmer);
//                        boolean isFoundCalmer =  wordExists(userInput, foundWords);

                        if (!isExistsCalmer) {
                            lblResult.setText("The word " + userInput + " is wrong!");
                            lblResult.setTextColor(Color.RED);
                        }

                        if (userInput.isEmpty()){
                            lblResult.setTextColor(Color.RED);
                            lblResult.setText("Please enter a word!");
                        }

                        if (isExistsCalmer && counter > 0) {
                            editUserInput.setText("");
//                            foundWords[addToArrayIndex] = userInput;
                            addToArrayIndex++;
                            counter--;
                            lblResult.setText("You found " + userInput + "! You still have " + counter + " words left");
                            lblResult.setTextColor(Color.BLACK);

//                            lblFoundWords.setText(foundWords.toString());
                        }
//                        if (isFoundCalmer) {
//                            editUserInput.setText("");
//                            lblResult.setTextColor(Color.RED);
//                            lblResult.setText("You already found" + userInput + "! You still have " + counter + " words left");
//                        }

                        if (isExistsCalmer && counter == 0) {
//                            foundWords[addToArrayIndex] = userInput;
//                            addToArrayIndex++;
                            lblFoundWords.setText("");
                            editUserInput.setText("");
                            lblResult.setTextColor(Color.BLUE);
                            lblResult.setText("You found them all!!");
//                            lblFoundWords.setText(foundWords.toString());
                            editUserInput.setFocusable(false);
                            btnImDone.setEnabled(false);
                        }

                        break;
                    case "Happier":
                        boolean isExistsHappier =  wordExists(userInput, happier);
//                        boolean isFoundHappier =  wordExists(userInput, foundWords);

                        if (!isExistsHappier) {
                            lblResult.setText("The word " + userInput + " is wrong!");
                            lblResult.setTextColor(Color.RED);
                        }

                        if (userInput.isEmpty()){
                            lblResult.setTextColor(Color.RED);
                            lblResult.setText("Please enter a word!");
                        }

                        if (isExistsHappier && counter > 0) {
                            editUserInput.setText("");
//                            foundWords[addToArrayIndex] = userInput;
                            addToArrayIndex++;
                            counter--;
                            lblResult.setText("You found " + userInput + "! You still have " + counter + " words left");
                            lblResult.setTextColor(Color.BLACK);

//                            lblFoundWords.setText(foundWords.toString());
                        }
                        
//                        if (isFoundHappier) {
//                            editUserInput.setText("");
//                            lblResult.setTextColor(Color.RED);
//                            lblResult.setText("You already found" + userInput + "! You still have " + counter + " words left");
//                        }

                        if (isExistsHappier && counter == 0) {
//                            foundWords[addToArrayIndex] = userInput;
//                            addToArrayIndex++;
                            lblFoundWords.setText("");
                            editUserInput.setText("");
                            lblResult.setTextColor(Color.BLUE);
                            lblResult.setText("You found them all!!");
//                            lblFoundWords.setText(foundWords.toString());
                            editUserInput.setFocusable(false);
                            btnImDone.setEnabled(false);
                        }
                        break;
                        
                    case "Moody":
                        boolean isExistsMoody =  wordExists(userInput, moody);
//                        boolean isFoundMoody =  wordExists(userInput, foundWords);

                        if (!isExistsMoody) {
                            lblResult.setText("The word " + userInput + " is wrong!");
                            lblResult.setTextColor(Color.RED);
                        }

                        if (userInput.isEmpty()){
                            lblResult.setTextColor(Color.RED);
                            lblResult.setText("Please enter a word!");
                        }

                        if (isExistsMoody && counter > 0) {
                            editUserInput.setText("");
//                            foundWords[addToArrayIndex] = userInput;
                            addToArrayIndex++;
                            counter--;
                            lblResult.setText("You found " + userInput + "! You still have " + counter + " words left");
                            lblResult.setTextColor(Color.BLACK);

//                            lblFoundWords.setText(foundWords.toString());
                        }

//                        if (isFoundMoody) {
//                            editUserInput.setText("");
//                            lblResult.setTextColor(Color.RED);
//                            lblResult.setText("You already found" + userInput + "! You still have " + counter + " words left");
//                        }

                        if (isExistsMoody && counter == 0) {
//                            foundWords[addToArrayIndex] = userInput;
//                            addToArrayIndex++;
                            lblFoundWords.setText("");
                            editUserInput.setText("");
                            lblResult.setTextColor(Color.BLUE);
                            lblResult.setText("You found them all!!");
//                            lblFoundWords.setText(foundWords.toString());
                            editUserInput.setFocusable(false);
                            btnImDone.setEnabled(false);
                        }

                        break;
                    case "Energetic":
                        boolean isExistsEnergetic =  wordExists(userInput, energetic);
//                        boolean isFoundEnergetic =  wordExists(userInput, foundWords);

                        if (!isExistsEnergetic) {
                            lblResult.setText("The word " + userInput + " is wrong!");
                            lblResult.setTextColor(Color.RED);
                        }

                        if (userInput.isEmpty()){
                            lblResult.setTextColor(Color.RED);
                            lblResult.setText("Please enter a word!");
                        }

                        if (isExistsEnergetic && counter > 0) {
                            editUserInput.setText("");
//                            foundWords[addToArrayIndex] = userInput;
                            addToArrayIndex++;
                            counter--;
                            lblResult.setText("You found " + userInput + "! You still have " + counter + " words left");
                            lblResult.setTextColor(Color.BLACK);

//                            lblFoundWords.setText(foundWords.toString());
                        }

//                        if (isFoundEnergetic) {
//                            editUserInput.setText("");
//                            lblResult.setTextColor(Color.RED);
//                            lblResult.setText("You already found" + userInput + "! You still have " + counter + " words left");
//                        }

                        if (isExistsEnergetic && counter == 0) {
//                            foundWords[addToArrayIndex] = userInput;
//                            addToArrayIndex++;
                            lblFoundWords.setText("");
                            editUserInput.setText("");
                            lblResult.setTextColor(Color.BLUE);
                            lblResult.setText("You found them all!!");
//                            lblFoundWords.setText(foundWords.toString());
                            editUserInput.setFocusable(false);
                            btnImDone.setEnabled(false);
                        }
                        break;
                    default:
                        boolean isExistsRelaxed =  wordExists(userInput, relaxed);
//                        boolean isFoundRelaxed =  wordExists(userInput, foundWords);

                        if (!isExistsRelaxed) {
                            lblResult.setText("The word " + userInput + " is wrong!");
                            lblResult.setTextColor(Color.RED);
                        }

                        if (userInput.isEmpty()){
                            lblResult.setTextColor(Color.RED);
                            lblResult.setText("Please enter a word!");
                        }

                        if (isExistsRelaxed && counter > 0) {
                            editUserInput.setText("");
//                            foundWords[addToArrayIndex] = userInput;
                            addToArrayIndex++;
                            counter--;
                            lblResult.setText("You found " + userInput + "! You still have " + counter + " words left");
                            lblResult.setTextColor(Color.BLACK);

//                            lblFoundWords.setText(foundWords.toString());
                        }

//                        if (isFoundRelaxed) {
//                            editUserInput.setText("");
//                            lblResult.setTextColor(Color.RED);
//                            lblResult.setText("You already found" + userInput + "! You still have " + counter + " words left");
//                        }

                        if (isExistsRelaxed && counter == 0) {
//                            foundWords[addToArrayIndex] = userInput;
//                            addToArrayIndex++;
                            lblFoundWords.setText("");
                            editUserInput.setText("");
                            lblResult.setTextColor(Color.BLUE);
                            lblResult.setText("You found them all!!");
//                            lblFoundWords.setText(foundWords.toString());
                            editUserInput.setFocusable(false);
                            btnImDone.setEnabled(false);
                        }
                        break;

                }
            }
        });
    }

    //function to start AboutActivity
    private void openAbout(){
        Intent start = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(start);
    }

    private void openlogin(){
        Intent start = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(start);
    }

    private boolean wordExists(String userInput, String[] moodArray) {
        for (int i = 0; i < moodArray.length; i++) {
            // get the value at current array index
            String arrayValue = moodArray[i];
            // compare values
            if (userInput.equals(arrayValue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            openAbout();
            return true;
        }
        if (item.getItemId() == R.id.menu_main) {
            openlogin();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
