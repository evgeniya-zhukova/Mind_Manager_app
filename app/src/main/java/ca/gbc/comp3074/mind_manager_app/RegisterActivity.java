package ca.gbc.comp3074.mind_manager_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import ca.gbc.comp3074.mind_manager_app.Models.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Database instance
        final GoogleMySQLConnectionHelper db = new GoogleMySQLConnectionHelper();
        final Connection connect = db.connectionclass();

        final TextView editFirstName = findViewById(R.id.editName);
        final TextView editUserName = findViewById(R.id.editUsername);
        final TextView editPassword = findViewById(R.id.editPassword);
        final TextView editConfirmPassword = findViewById(R.id.editConfirmPass);
        final TextView lblError = findViewById(R.id.lblError);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Execute the query, find if username input from user is existing in data base
                User userExist = db.getUser(connect, editUserName.getText().toString());

                if(userExist != null){
                    lblError.setText("This user name is already exist");
                }
                else if (!editPassword.getText().toString().equals(editConfirmPassword.getText().toString())){
                    lblError.setText("Password and Confirm Password not match");
                } else{
                    //Insert new user
                    User user = new User("user", editUserName.getText().toString()+"",
                            editFirstName.getText().toString()+"", editPassword.getText().toString()+"");
                    db.addUser(connect, user);
                    backToLogin();
                }
            }
        });

        //Button Cancel goes back to the login page(MainActivity)
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });
    }

    //function to start MainActivity
    private void backToLogin(){
        Intent start = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(start);
    }
}