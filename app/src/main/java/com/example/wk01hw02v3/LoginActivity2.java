package com.example.wk01hw02v3;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity2 extends AppCompatActivity{
    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mButton;
    ArrayList<User> userList = new ArrayList<User>();
    private String mUsername;
    private String mPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        wireupDisplay();

        userList.add(new User(1,"user1","password"));
        userList.add(new User(2,"user2","password"));
        userList.add(new User(3,"user3","password"));


    }

    private void wireupDisplay(){
        mUsernameField = findViewById(R.id.usernameInput);
        mPasswordField = findViewById(R.id.passwordInput);

        mButton = findViewById(R.id.signButton);
        Intent intent = getIntent();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity2.this, "Click"  , Toast.LENGTH_SHORT).show();
                getValuesFromDisplay();
                User validUser = validatePassword(userList,mUsername,mPassword);
                if (validUser != null){
                    Toast.makeText(LoginActivity2.this, "Welcome, " +validUser.getUsername() , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),LandingActivity.class); //user has to log in
                    intent.putExtra("userID",validUser.getUserID());
                    startActivity(intent);
                }
                else{
                    toastMaker("Invalid username or password.");
                }
            }
        });
    }

    private void getValuesFromDisplay(){
        mUsername = mUsernameField.getText().toString();
        mPassword = mPasswordField.getText().toString();
    }

    public static User validatePassword(ArrayList<User> users,String user, String pass){
        for(User each: users){
            if (each.getUsername().equals(user)){ //found matching username
                if(each.getPassword().equals(pass)){
                    return each;
                }
                else{
                    return null;
                }
            }
        }
        return null;
    }

    public void toastMaker(String message){
        Toast.makeText(LoginActivity2.this, message , Toast.LENGTH_SHORT).show();
    }
    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginActivity2.class);

        return intent;
    }
}
