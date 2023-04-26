package com.example.experimentalreverb;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.auth.GithubAuthProvider;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String CLIENT_ID = "4d07a219647cab01ce5c";
    private static final String APP_ID_SECRET = "370949a4ee53b9408e73670ce3c3f44e68fe531e";
    private static final String REDIRECT_URI = "com.example.app.my-app-scheme://auth";

    private FirebaseAuth mAuth;
    private TextView mUserNameTextView;
    private TextView mUserBioTextView;
    private TextView mUserRepoCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithGitHub();
            }
        });

        // Initialize the Firebase Auth instance.
        mAuth = FirebaseAuth.getInstance();
    }

    private void loginWithGitHub() {
        // Create an OAuth provider.
        OAuthProvider provider = OAuthProvider.newBuilder(GithubAuthProvider.PROVIDER_ID).build();

        // Set the client ID and client secret.
        provider.setClientId(CLIENT_ID);
        provider.setClientSecret(APP_ID_SECRET);

        // Set the redirect URI.
        provider.setRedirectUri(REDIRECT_URI);

        // Start the OAuth flow.
        Intent authorizationIntent = provider.getAuthorizationRequestIntent();
        startActivityForResult(authorizationIntent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            // Check the result code.
            if (resultCode == RESULT_OK) {
                // Get the authorization response.
                OAuthCredential credential = OAuthProvider.getCredential(data);

                // Sign in with the credential.
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                mUserNameTextView.setText(user.getDisplayName());
                                mUserBioTextView.setText(user.getBio());
                                mUserRepoCountTextView.setText(String.valueOf(user.getGithubRepoCount()));
                            } else {
                                // Sign in failed, display a message to the user.
                                Log.w(TAG, "Sign in failed: ", task.getException());
                            }
                        });
            } else {
                // Authorization failed, display a message to the user.
                Log.w(TAG, "Authorization failed.");
            }
        }
    }
}