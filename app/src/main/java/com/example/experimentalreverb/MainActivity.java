package com.example.experimentalreverb;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.auth.OAuthProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText gitEmail;
    private Button gitLogin;
    private FirebaseAuth fAuth;
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
                                                {
                                                    add("repo");
                                                }
                                                {
                                                    add("read:org");
                                                }
                                                {
                                                    add("read:public_key");
                                                }
                                                {
                                                    add("read:gpg_key");
                                                }
                                                {
                                                    add("admin:org_hook");
                                                }
                                                {
                                                    add("admin:org");
                                                }
                                                {
                                                    add("admin:public_key");
                                                }
                                                {
                                                    add("admin:gpg_key");
                                                }
                                                {
                                                    add("admin:enterprise");
                                                }
                                                {
                                                    add("admin:org_hook");
                                                }
                                                {
                                                    add("admin:org");
                                                }
                                                {
                                                    add("admin:public_key");
                                                }
                                                {
                                                    add("admin:gpg_key");
                                                }
                                                {
                                                    add("admin:enterprise");
                                                }
                                                {
                                                    add("admin:enterprise_hook");
                                                }
                                                {
                                                    add("gist");
                                                }
                                                {
                                                    add("notifications");
                                                }
                                                {
                                                    add("user");
                                                }
                                                {
                                                    add("delete_repo");
                                                }
                                                {
                                                    add("write:discussion");
                                                }
                                                {
                                                    add("read:packages");
                                                }
                                                {
                                                    add("write:packages");
                                                }
                                                {
                                                    add("delete:packages");
                                                }
                                                {
                                                    add("admin:packages");
                                                }
                                                {
                                                    add("read:org");
                                                }
                                                {
                                                    add("write:org");
                                                }
                                                {
                                                    add("admin:org");
                                                }
                                                {
                                                    add("read:enterprise");
                                                }
                                                {
                                                    add("write:enterprise");
                                                }
                                                {
                                                    add("admin:enterprise");
                                                }
                                                {
                                                    add("read:user");
                                                }
                                                {
                                                    add("write:user");
                                                }
                                                {
                                                    add("admin:gpg_key");
                                                }
                                                {
                                                    add("read:gpg_key");
                                                }
                                                {
                                                    add("write:gpg_key");
                                                }
                                                {
                                                    add("admin:public_key");
                                                }
                                                {
                                                    add("read:public_key");
                                                }
                                                {
                                                    add("write:public_key");
                                                }
                                                {
                                                    add("admin:repo_hook");
                                                }
                                                {
                                                    add("read:repo_hook");
                                                }
                                                {
                                                    add("write:repo_hook");
                                                }
                                                {
                                                    add("admin:org_hook");
                                                }
                                                {
                                                    add("read:org_hook");
                                                }
                                                {
                                                    add("write:org_hook");
                                                }
                                                {
                                                    add("admin:enterprise_hook");
                                                }
                                                {
                                                    add("read:enterprise_hook");
                                                }
                                                {
                                                    add("write:enterprise_hook");
                                                }
                                                {
                                                    add("admin:discussion");
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
                    FirebaseUser UserDetails = authResult.getUser();
                    Intent intent = new Intent(MainActivity.this, RepositoriesActivity.class);
                    Log.d("User --", "onSuccess: " + user.getEmail() + " " + user.getDisplayName() + " " + user.getPhotoUrl() + " profile " + authResult.getAdditionalUserInfo().getProfile() + "  access token  ->> " +((OAuthCredential)authResult.getCredential()).getAccessToken() + " user " + authResult.getUser() + " awe "+((OAuthCredential)authResult.getCredential()).getAccessToken());
                    Accesstoken.getInstance().setToken(((OAuthCredential)authResult.getCredential()).getAccessToken());
                    intent.putExtra("email", user.getEmail());
                    intent.putExtra("name", user.getDisplayName());
                    intent.putExtra("photo", String.valueOf(user.getPhotoUrl()));
                    intent.putExtra("AccessToken",((OAuthCredential)authResult.getCredential()).getAccessToken());
                    startActivity(intent);
                    //finish();

                }

            });
        }
    }
}
