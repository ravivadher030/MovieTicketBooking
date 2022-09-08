package com.example.myticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Login extends AppCompatActivity {
    private static final int REQ_ONE_TAP = 123;
    MaterialButton gooleLogin;
    ImageView backe_btn;
    FirebaseDatabase root;
    DatabaseReference reference;
    private GoogleSignInClient msclient;
    private FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            SharedPreferences sharedPreferences=getSharedPreferences("MyTicket",MODE_PRIVATE);;
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("username",user.getEmail());
            editor.putBoolean("LoggedIn",true);
            editor.commit();
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Google Authentication
        googlerequest();
        gooleLogin=findViewById(R.id.googleLogin);
        backe_btn=findViewById(R.id.backed_btn);

        //Backed Press
        backe_btn.setOnClickListener(v->{
            super.onBackPressed();
        });

        //Google Login Button
        mAuth=FirebaseAuth.getInstance();
        gooleLogin.setOnClickListener(v->{
            googleLogin();
        });

    }
    private void googlerequest() {
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client))
                .requestEmail()
                .build();

        msclient= GoogleSignIn.getClient(this,gso);
    }
    private void googleLogin() {
        Intent signedIn= msclient.getSignInIntent();
        startActivityForResult(signedIn,REQ_ONE_TAP);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_ONE_TAP){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account=task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            }catch (ApiException apiException){
                Toast.makeText(this,apiException+"", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void firebaseAuthWithGoogle(String account) {
        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(account, null);
        mAuth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener(this, task -> {
                        SharedPreferences sharedPreferences=getSharedPreferences("MyTicket",MODE_PRIVATE);;
                        SharedPreferences.Editor editor=sharedPreferences.edit();                 if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();
                        editor.putString("username",user.getEmail());
                        editor.putBoolean("LoggedIn",true);
                        editor.commit();
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);

                    } else {
                        Toast.makeText(getApplicationContext(),"Something went Wrong !",Toast.LENGTH_LONG).show();
                    }
                });

    }
}