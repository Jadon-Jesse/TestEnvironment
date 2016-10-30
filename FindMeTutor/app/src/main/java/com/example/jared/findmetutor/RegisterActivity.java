package com.example.jared.findmetutor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class  RegisterActivity extends AppCompatActivity {

    private String firstName,lastName, email, number, password,ConfirmPassword;
    private String firstName2,lastName2, email2, number2, password2,ConfirmPassword2, stdNum;
    private EditText inputFirstName,inputLastName, inputEmail, inputNumber, inputPassword,inputConfirmPassword, studentNumber;
    private TextInputLayout inputLayoutFName, inputLayoutLName, inputLayoutEmail, inputLayoutNumber, inputLayoutPass,inputLayoutCPass, inputStudentNumber ;
    private Button btnRegister;
    private String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get and assign toolbar entered information
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get layouts
         inputLayoutFName = (TextInputLayout) findViewById(R.id.input_layout_Firstname);
         inputLayoutLName = (TextInputLayout) findViewById(R.id.input_layout_Lastname);
         inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
         inputLayoutNumber = (TextInputLayout) findViewById(R.id.input_layout_number) ;
         inputLayoutPass = (TextInputLayout) findViewById(R.id.input_layout_password);
         inputLayoutCPass = (TextInputLayout) findViewById(R.id.input_layout_passwordConfirm);
         inputStudentNumber =(TextInputLayout) findViewById(R.id.input_layout_stdNum);

        //Get EditText objects
         inputFirstName = (EditText) findViewById(R.id.input_Firstname);
         inputLastName = (EditText) findViewById(R.id.input_Lastname);
         inputEmail = (EditText) findViewById(R.id.input_email);
         inputNumber = (EditText) findViewById(R.id.input_Number);
         inputPassword = (EditText) findViewById(R.id.input_password);
         inputConfirmPassword =(EditText) findViewById(R.id.input_passwordConfirm);
         studentNumber = (EditText) findViewById(R.id.stdNum);


        //Get Strings from those objects
         firstName = inputFirstName.getText().toString();
         lastName = inputLastName.getText().toString();
         email = inputEmail.getText().toString();
         number = inputNumber.getText().toString();
         password = inputPassword.getText().toString();
         ConfirmPassword = inputConfirmPassword.getText().toString();
         stdNum = studentNumber.getText().toString();


        inputFirstName.addTextChangedListener(new RegisterActivity.MyTextWatcher(inputFirstName));
        inputLastName.addTextChangedListener(new RegisterActivity.MyTextWatcher(inputLastName));
        inputEmail.addTextChangedListener(new RegisterActivity.MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new RegisterActivity.MyTextWatcher(inputPassword));
        inputConfirmPassword.addTextChangedListener(new RegisterActivity.MyTextWatcher(inputConfirmPassword));

        //get button object
        btnRegister = (Button) findViewById(R.id.btn_register);


        //Submit form when login is clicked
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        //if Login is clicked take you back to login screen
        TextView log = (TextView) findViewById(R.id.tvLogin);

        log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginAct = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginAct);
            }
        });




    }



   private void submitForm() {

        if (!validateFName()) {
            return;
        }
        if(!validateLName()){
            return;
        }

        if (!validateEmail()) {
            return;
        }
        if(!validateNumber()){
            return;
        }

        if (!validatePassword()) {
            return;
        }

       if(!validateConfirmPassword())
       {
           return ;
       }

       //if everyhing checks out then go back to login page
        if(validateFName() && validateLName() && validateEmail() && validatePassword() && validateConfirmPassword())
        {


           // String sNum = getStdNum(inputEmail.getText().toString());
            studentCheckReg connect2server = new studentCheckReg(this, firstName2,lastName2, number2,studentNumber.getText().toString(), email2,password2 );
            connect2server.execute();

            Toast.makeText(getApplicationContext(), "Thank You for registering!", Toast.LENGTH_SHORT).show();
           // Toast.makeText(getApplicationContext(), "StudentNum : " +studentNumber., Toast.LENGTH_SHORT).show();
            Intent logInAct = new Intent(RegisterActivity.this, LoginActivity.class);
            RegisterActivity.this.startActivity(logInAct);
        }


    }

    public  String getStdNum(String emai)
    {
        int index = emai.indexOf("@");
        String sub = emai.substring(0, index);
        return sub;
    }




    private boolean validateFName() {
        firstName2 = inputFirstName.getText().toString().trim();
        if (firstName2.isEmpty()) {
            inputLayoutFName.setError(getString(R.string.err_msg_Fname));
            requestFocus(inputFirstName);
            return false;
        } else {
            inputLayoutFName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLName() {
        lastName2 = inputLastName.getText().toString().trim();
        if (lastName2.isEmpty()) {
            inputLayoutLName.setError(getString(R.string.err_msg_Lname));
            requestFocus(inputLastName);
            return false;
        } else {
            inputLayoutLName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        email2 = inputEmail.getText().toString().trim();
        if (email2.isEmpty() || !isValidEmail(email2) || !isWitsEmail(email2))
        {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNumber() {
        number2 = inputNumber.getText().toString().trim();
        if (number2.length()<10) {
            inputLayoutNumber.setError(getString(R.string.err_msg_number));
            requestFocus(inputNumber);
            return false;
        } else {
            inputLayoutNumber.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validatePassword() {
        password2 = inputPassword.getText().toString().trim();
        if (password2.isEmpty() || password2.length() <7 ) {
            inputLayoutPass.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPass.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateConfirmPassword() {
        ConfirmPassword2 = inputConfirmPassword.getText().toString().trim();
        if (!(ConfirmPassword2.equals(password2))) {
            inputLayoutCPass.setError("Confirm password mismatch");
            requestFocus(inputConfirmPassword);
            return false;
        } else {
            inputLayoutCPass.setErrorEnabled(false);
        }

        return true;
    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isWitsEmail(String email){
        boolean ret = false;

        int iAt = email.indexOf('@');

        String students = email.substring(iAt+1);

        if(students.equalsIgnoreCase("students.wits.ac.za")){
            ret = true;
        }


        return ret;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
                case R.id.input_Firstname:
                    validateFName();
                    break;
                case R.id.input_Lastname:
                    validateLName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_Number:
                    validateNumber();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
                case R.id.input_layout_passwordConfirm:
                    validateConfirmPassword();
                    break;
            }
        }
    }





}



