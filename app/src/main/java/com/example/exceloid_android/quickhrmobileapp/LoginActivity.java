package com.example.exceloid_android.quickhrmobileapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    TextView forgotPassword;
    //UserFunctions userFunctions;
    private static int SPLASH_TIME_OUT = 3000;
    private String user_name,pass_word,success;
    private Animation animShow;
    LinearLayout linearLayout,layout;
    //JSONParser jsonParser;
    InputStream is = null;
    String result = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText)findViewById(R.id.userName);
        password=(EditText)findViewById(R.id.passWord);
        login=(Button)findViewById(R.id.quickHrLogin);
        forgotPassword=(TextView)findViewById(R.id.forgotPassword);

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

