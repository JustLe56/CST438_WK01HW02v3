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
                User possibleUser = validateUser(userList,mUsername);
                if (possibleUser != null && validatePassword(possibleUser,mPassword)){
                    Toast.makeText(LoginActivity2.this, "Welcome, " +possibleUser.getUsername() , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),LandingActivity.class); //user has to log in
                    intent.putExtra("userID",possibleUser.getUserID());
                    startActivity(intent);
                }
                else if (possibleUser == null){
                    mUsernameField.requestFocus();
                    mUsernameField.setError("Invalid user");
                    //toastMaker("Invalid username or password.");
                }
                else{
                    mPasswordField.requestFocus();
                    mPasswordField.setError("Invalid password");
                }
            }
        });
    }

    private void getValuesFromDisplay(){
        mUsername = mUsernameField.getText().toString();
        mPassword = mPasswordField.getText().toString();
    }

    public static User validateUser(ArrayList<User> users,String username){
        for(User each: users){
            if (each.getUsername().equals(username)){ //found matching username
                return each;
            }
        }
        return null;
    }

    public static boolean validatePassword(User user, String pass){
        if (user == null){
            return false;
        }
        if(user.getPassword().equals(pass)){
            return true;
        }
        return false;
    }

    public void toastMaker(String message){
        Toast.makeText(LoginActivity2.this, message , Toast.LENGTH_SHORT).show();
    }
    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginActivity2.class);

        return intent;
    }
}
