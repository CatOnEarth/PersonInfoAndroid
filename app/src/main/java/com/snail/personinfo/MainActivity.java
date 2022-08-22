package com.snail.personinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.snail.personinfo.logger.Logger;

/** Activity for save fill and save person info
 *
 */
public class MainActivity extends AppCompatActivity {
    /**
     * < TAG for class name debug
     */
    private final String TAG = this.getClass().getSimpleName();

    /**
     * < Logger
     */
    private Logger logger;

    /**
     * < EditText for person name
     */
    private EditText editTextPersonName;
    /**
     * < EditText for person surname
     */
    private EditText editTextPersonSurname;
    /**
     * < EditText for person emali
     */
    private EditText editTextPersonEmail;
    /**
     * < EditText for person age
     */
    private EditText editTextPersonAge;

    /**
     * < Spinner for person gender
     */
    private Spinner spinnerGender;

    /** Method onCreate activity person register
     *
     * @param savedInstanceState saved last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logger = new Logger();
        logger.LogInfo(TAG, "call onCreate");

        editTextPersonName    = findViewById(R.id.editTextPersonName);
        editTextPersonSurname = findViewById(R.id.editTextSurname);
        editTextPersonEmail   = findViewById(R.id.editTextEmailPerson);
        editTextPersonAge     = findViewById(R.id.editTextAgePerson);

        spinnerGender         = findViewById(R.id.spinnerGenders);

        Button bReg           = findViewById(R.id.buttonRegisterPerson);

        View.OnClickListener regBtnClick = view -> {
            logger.LogInfo(TAG, "Click reg button");
            if (IsCorrectFillFields()) {
                RegisterPerson();
            }
        };

        bReg.setOnClickListener(regBtnClick);
    }

    /** Function for check input in all fields
     *
     * @return true - correct input; false - incorrect input
     */
    private boolean IsCorrectFillFields() {
        logger.LogInfo(TAG, "call IsCorrectFillFields");

        boolean IsError = false;

        if (!IsCorrectPersonName()) {
            logger.LogErr(TAG, "Incorrect name");
            editTextPersonName.setError("Неправильное имя");
            IsError = true;
        }
        if (!IsCorrectSurname()) {
            logger.LogErr(TAG, "Incorrect surname");
            editTextPersonSurname.setError("Неправильная фамилия");
            IsError = true;
        }
        if (!IsCorrectEmailPerson()) {
            logger.LogErr(TAG, "Incorrect email");
            editTextPersonEmail.setError("Неправильный email");
            IsError = true;
        }
        if (!IsCorrectAgePerson()) {
            logger.LogErr(TAG, "Incorrect age");
            editTextPersonAge.setError("Некорректный возраст");
            IsError = true;
        }

        return !IsError;
    }

    /**
     * Function for check correction person name field
     * @return true - correct input; false - incorrect input
     */
    private boolean IsCorrectPersonName() {
        logger.LogInfo(TAG, "call IsCorrectPersonName");

        String textName = editTextPersonName.getText().toString();
        return textName.length() != 0;
    }

    /**
     * Function for check correction person surname field
     * @return true - correct input; false - incorrect input
     */
    private boolean IsCorrectSurname() {
        logger.LogInfo(TAG, "call IsCorrectSurname");

        String textSurname = editTextPersonSurname.getText().toString();
        return textSurname.length() != 0;
    }

    /**
     * Function for check correction person email field
     * @return true - correct input; false - incorrect input
     */
    private boolean IsCorrectEmailPerson() {
        logger.LogInfo(TAG, "call IsCorrectEmailPerson");

        String textEmail = editTextPersonEmail.getText().toString();
        return textEmail.length() != 0;
    }

    /**
     * Function for check correction person age field
     * @return true - correct input; false - incorrect input
     */
    private boolean IsCorrectAgePerson() {
        logger.LogInfo(TAG, "call IsCorrectAgePerson");

        String textAge = editTextPersonAge.getText().toString();
        try {
            int age = Integer.parseInt(textAge);
            if (age <= 0 || age > 200) {
                return false;
            }
        } catch  (NumberFormatException ex) {
            logger.LogInfo(TAG, "catch exception age converting string to int");

            return false;
        }

        return true;
    }

    /** Method for register person in database
     *
     */
    private void RegisterPerson() {
        logger.LogInfo(TAG, "call RegisterPerson");

        String textName     = editTextPersonName.getText().toString();
        String textSurname  = editTextPersonSurname.getText().toString();
        String textEmail    = editTextPersonEmail.getText().toString();
        String textAge      = editTextPersonAge.getText().toString();
        String textGender   = spinnerGender.getSelectedItem().toString();

        logger.LogInfo(TAG, "Person Info:  Name: "    + textName    + ", " +
                                                   "Surname: " + textSurname + ", " +
                                                   "Email: "   + textEmail   + ", " +
                                                   "Age: "     + textAge     + ", " +
                                                   "Gender: "  + textGender);



    }
}