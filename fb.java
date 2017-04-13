package com.example.sagarsaxena.fb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class fb extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 0;
    private FirebaseAuth auth;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Log.d("AUTH", auth.getCurrentUser().getEmail());
            } else {
                Log.d("AUTH", "NOT AUTHENTICAETD");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            //user signed in
        } else {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setProviders(
                    AuthUI.FACEBOOK_PROVIDER,
                    AuthUI.GOOGLE_PROVIDER,
                    AuthUI.EMAIL_PROVIDER).build(), RC_SIGN_IN);
        }
        findViewById(R.id.log_out_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.log_out_button) {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("AUTH", "USER LOGGED OUT");
                    finish();
                }
            });
        }
    }
}




