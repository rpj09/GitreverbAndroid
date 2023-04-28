package com.example.experimentalreverb;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String CLIENT_ID = "4d07a219647cab01ce5c";
    private static final String APP_ID_SECRET = "370949a4ee53b9408e73670ce3c3f44e68fe531e";
    private static final String REDIRECT_URI = "com.example.app.my-app-scheme://auth";
    private EditText gitEmail;
    private Button gitLogin;
    private FirebaseAuth fAuth;

    private FirebaseAuth mAuth;
    private TextView mUserNameTextView;
    private TextView mUserBioTextView;
    private TextView mUserRepoCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gitEmail = findViewById(R.id.email);
        gitLogin = findViewById(R.id.login_button);
        fAuth = FirebaseAuth.getInstance();

        gitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(gitEmail.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                } else {
                    SignInWithGithubProvider(
                            OAuthProvider.newBuilder("github.com")
                                    .addCustomParameter("login", gitEmail.getText().toString())
                                    .setScopes(
                                            new ArrayList<String>(){
                                                {
                                                    add("user:email");
                                                }
                                            })
                                    .build()

                    );
                }
            }
        });
    }

    private void SignInWithGithubProvider(OAuthProvider login) {
        Task<AuthResult> pendingAuthTask = fAuth.getPendingAuthResult();
        if (pendingAuthTask != null){
            pendingAuthTask.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(MainActivity.this, "User Exists", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else {
            fAuth.startActivityForSignInWithProvider(this,login).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user = fAuth.getCurrentUser();
                    Intent intent = new Intent(MainActivity.this, Profile.class);
                    intent.putExtra("email", user.getEmail());
                    intent.putExtra("name", user.getDisplayName());
                    intent.putExtra("photo", String.valueOf(user.getPhotoUrl()));
                    intent.putExtra("acesstoken", String.valueOf(user.getIdToken(true)));
                    intent.putExtra("bio", user.getProviderId());
                    intent.putExtra("repoCount", user.getProviderId());
                    intent.putExtra("username", user.getProviderId());
                    startActivity(intent);
                    finish();

                }

            });
        }
    }
}
