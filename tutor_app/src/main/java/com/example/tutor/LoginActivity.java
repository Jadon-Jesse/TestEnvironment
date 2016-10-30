package com.example.tutor;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity implements tutor_AsyncResponse {

    //Global Variables to be used
    private Toolbar toolbar;
    private String name, email, password, login_email, login_password;
    private EditText inputName, inputEmail, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get and assign toolbar entered information
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get inputLayouts
      //  inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        //get EditText objects
       // inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);


        btnLogin = (Button) findViewById(R.id.btn_signup);
        TextView btnRegister = (TextView) findViewById(R.id.tvRegister);

        //use EditText objects to moniter the  Text fields for valid input
      //  inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        //get Strings from EditText objects
      //  name = inputName.getText().toString();
        email= inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        //Submit form when login is clicked
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();



            }
        });

        //go to Register page if Register TextView Object is clicked
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent regAct = new Intent(LoginActivity.this, RegisterActivity.class);
                //regAct.putExtra() send stuff to regActivity
                LoginActivity.this.startActivity(regAct);




            }
        });

    }// end on create

    /**
     * Validating form
     */
    //Validate entered information
    private void submitForm() {

       // if (!validateName()) {
         //   return;
        //}

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        if( validateEmail() && validatePassword())
        {
            int test ;
            //Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
            login connect2server = new login(this, login_email, login_password,0);
            connect2server.delegate = this;
            connect2server.execute();


        }


    }
   // @TargetApi(9)
    //Checks if name is a valid name if not return false and keep focus otherwise return true
   /* private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }*/
    @TargetApi(9)
    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();
        login_email = email;
        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    @TargetApi(9)
    private boolean validatePassword() {
        String password2 = inputPassword.getText().toString().trim();
        login_password = password2;
        if (password2.isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }
    @TargetApi(8)
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void processFinish(String output) {

    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                //case R.id.input_name:
                  //  validateName();
                   // break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }
 }
