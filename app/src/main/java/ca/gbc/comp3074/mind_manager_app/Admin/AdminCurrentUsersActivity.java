package ca.gbc.comp3074.mind_manager_app.Admin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.sql.Connection;
import java.util.List;

import ca.gbc.comp3074.mind_manager_app.AboutActivity;
import ca.gbc.comp3074.mind_manager_app.Disclaimer;
import ca.gbc.comp3074.mind_manager_app.GoogleMySQLConnectionHelper;
import ca.gbc.comp3074.mind_manager_app.MainActivity;
import ca.gbc.comp3074.mind_manager_app.Models.User;
import ca.gbc.comp3074.mind_manager_app.Models.UserArrayAdapter;
import ca.gbc.comp3074.mind_manager_app.R;

public class AdminCurrentUsersActivity extends ListActivity {

    List<User> users;
    TextView lblError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_current_users);

        lblError = findViewById(R.id.lblError2);

        //Database instance
        final GoogleMySQLConnectionHelper db = new GoogleMySQLConnectionHelper();
        final Connection connect = db.connectionclass();

        printArray(connect, db);

        //button add User
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd(connect, db);
            }
        });

        //button Logout
        Button btnLogOut = findViewById(R.id.btnLogoutAdminCurrentUsers);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogOut();
            }
        });
    }

    //print array of all users
    private void printArray(Connection connect, GoogleMySQLConnectionHelper db){
        users = db.getAllUsers(connect);
        StringBuilder sb = new StringBuilder();
        int size = users.size();
        boolean appendSeparator = false;
        for(int y=0; y < size; y++){
            if (appendSeparator)
                sb.append(','); // a comma
            appendSeparator = true;
            sb.append(users.get(y));
        }
        ArrayAdapter<User> myAdapter = new UserArrayAdapter(this, users);
        setListAdapter(myAdapter);
    }

    //function add User
    private void openAdd(Connection connect, GoogleMySQLConnectionHelper db){
        String role = ((EditText) findViewById(R.id.edittxt_role)).getText().toString();
        String username = ((EditText) findViewById(R.id.edittxt_username)).getText().toString();
        String firstName = ((EditText) findViewById(R.id.edittxt_firstName)).getText().toString();
        //Execute the query, find if username input is existing in data base
        User userExist = db.getUser(connect, username);

        if(userExist != null){
            lblError.setText("This user name is already exist");
        }else {
            lblError.setText("");
            //Insert new user
            if (!role.equals("admin")){
                role = "user";
            }
            User user = new User(role, username, firstName, "123_Ben");
            db.addUser(connect, user);
            printArray(connect, db);
            ((EditText) findViewById(R.id.edittxt_role)).setText("");
            ((EditText) findViewById(R.id.edittxt_username)).setText("");
            ((EditText) findViewById(R.id.edittxt_firstName)).setText("");
        }
    }

    //function LogOut
    private void openLogOut(){
        Intent start = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(start);
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

    private void openDisclaimer(){
        Intent start = new Intent(getApplicationContext(), Disclaimer.class);
        startActivity(start);
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
        if (item.getItemId() == R.id.disclaimer) {
            openDisclaimer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}