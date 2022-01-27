package ca.gbc.comp3074.mind_manager_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import ca.gbc.comp3074.mind_manager_app.Admin.AdminHomeActivity;
import ca.gbc.comp3074.mind_manager_app.Models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database instance
        final GoogleMySQLConnectionHelper db = new GoogleMySQLConnectionHelper();
        final Connection connect = db.connectionclass();

        final EditText username = findViewById(R.id.editUsername);
        final EditText password = findViewById(R.id.editPassword);
        final TextView lblError = findViewById(R.id.lblerrorlogin);
        //Button Login goes to welcome page to choose a mood(WelcomeActivity)
        Button btnLogin = findViewById(R.id.btnSubmit);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Execute the query, find if username input from user is existing in data base
                final User userExist = db.getUser(connect, username.getText().toString());

                if(userExist != null && username.getText().toString().equals(userExist.getUserName()) &&
                        password.getText().toString().equals(userExist.getPassword())){
                    //admin login: 'benjeff', password: '123_Ben'
                    if((userExist.getRole()).equals("admin")){
                        openAdminHome();
                    } else {
                        openWelcome(userExist);
                    }
                }
                else{
                  lblError.setText("User name or password invalid");
                }
            }
        });

        //Button Register goes to Registration page(RegisterActivity)
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    //function to start WelcomeActivity
    private void openAdminHome(){
        Intent start = new Intent(getApplicationContext(), AdminHomeActivity.class);
        startActivity(start);
    }

    //function to start WelcomeActivity
    private void openWelcome(User userExist){
        Intent start = new Intent(getApplicationContext(), WelcomeActivity.class);
        String username = userExist.getUserName();
        String firstName = userExist.getFirstName();
        start.putExtra("username", username);
        start.putExtra("firstName", firstName);
        startActivity(start);
    }

    //function to start RegisterActivity
    private void openRegister(){
        Intent start = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(start);
    }

    //function to start AboutActivity
    private void openAbout(){
        Intent start = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            openAbout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}