package com.snail.personinfo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.snail.personinfo.db.DBHelper;
import com.snail.personinfo.logger.Logger;

import java.util.ArrayList;

/** Activity for fill and save person info
 *
 */
public class MainActivity extends AppCompatActivity {
    /** TAG for class name debug */
    private final String TAG = this.getClass().getSimpleName();
    /** Logger */
    private Logger logger;
    /** EditText for person name */
    private EditText editTextPersonName;
    /** EditText for person surname */
    private EditText editTextPersonSurname;
    /** EditText for person email */
    private EditText editTextPersonEmail;
    /** EditText for person age */
    private EditText editTextPersonAge;
    /** Spinner for person gender */
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

        Button bReg            = findViewById(R.id.buttonRegisterPerson);
        Button bSelectAvatar   = findViewById(R.id.buttonSelectAvatar);
        Button bClearDB        = findViewById(R.id.buttonClearDB);
        Button bShowAllPersons = findViewById(R.id.buttonShowAllPersons);

        View.OnClickListener regBtnClick = view -> {
            logger.LogInfo(TAG, "Click reg button");
            if (IsCorrectFillFields()) {
                RegisterPerson();
            }
        };

        View.OnClickListener selectAvatarBtnClick = view -> {
            logger.LogInfo(TAG, "Click select avatar button");
            openFileDialog(view);
        };

        View.OnClickListener clearDbBtnClick = view -> {
            logger.LogInfo(TAG, "Click clear db button");
            clearDB();
        };

        View.OnClickListener showAllPersonsBtnClick = view -> {
            logger.LogInfo(TAG, "Click show all persons button");
            showAllPersons();
        };

        bReg.setOnClickListener(regBtnClick);
        bSelectAvatar.setOnClickListener(selectAvatarBtnClick);
        bClearDB.setOnClickListener(clearDbBtnClick);
        bShowAllPersons.setOnClickListener(showAllPersonsBtnClick);
    }

    /** Method to show all persons info
     *
     */
    private void showAllPersons() {
        logger.LogInfo(TAG, "call showAllPersons");
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<Person> persons = dbHelper.selectAllPersons();

        for (int ii = 0; ii < persons.size(); ++ii) {
            Toast.makeText(this, "Person №" + String.valueOf(ii) + ". Name: " + persons.get(ii).getName() +
                    "; Surname: " + persons.get(ii).getSurname() + "; Email: " + persons.get(ii).getEmail() + "; Age: " + String.valueOf(persons.get(ii).getAge()) +
                    "; Gender: " + String.valueOf(persons.get(ii).getGender()), Toast.LENGTH_LONG).show();
        }
    }

    /** Method to clear db person info table
     *
     */
    private void clearDB() {
        logger.LogInfo(TAG, "call clearDB");
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.deleteDB();

        Toast.makeText(this, "База данных очищена", Toast.LENGTH_LONG).show();
    }

    /** Browser for select avatar */
    ActivityResultLauncher<Intent> selectActivityRes = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    logger.LogInfo(TAG, "call onActivityResult");

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri uri = data.getData();
                        }
                    }
                }
            }
    );

    /**Method to open explorer for select avatar image
     *
     * @param view Button select avatar
     */
    public void openFileDialog(View view) {
        logger.LogInfo(TAG, "call openFileDialog");

        Intent data = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        data.setType("image/*");
        data = Intent.createChooser(data, "Choose an avatar");
        selectActivityRes.launch(data);
    }

    /** Function for check input in all fields
     *
     * @return true: correct input
     * @return false: incorrect input
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
     * @return true: correct input
     * @return false: incorrect input
     */
    private boolean IsCorrectPersonName() {
        logger.LogInfo(TAG, "call IsCorrectPersonName");

        String textName = editTextPersonName.getText().toString();
        return textName.length() != 0;
    }

    /**
     * Function for check correction person surname field
     * @return true: correct input
     * @return false: incorrect input
     */
    private boolean IsCorrectSurname() {
        logger.LogInfo(TAG, "call IsCorrectSurname");

        String textSurname = editTextPersonSurname.getText().toString();
        return textSurname.length() != 0;
    }

    /**
     * Function for check correction person email field
     * @return true: correct input
     * @return false: incorrect input
     */
    private boolean IsCorrectEmailPerson() {
        logger.LogInfo(TAG, "call IsCorrectEmailPerson");

        String textEmail = editTextPersonEmail.getText().toString();
        return textEmail.length() != 0;
    }

    /**
     * Function for check correction person age field
     * @return true: correct input
     * @return false: incorrect input
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
        int    intAge       = Integer.parseInt(editTextPersonAge.getText().toString());
        String textGender   = spinnerGender.getSelectedItem().toString();
        byte   byteGender   = ConvertGenderStrToByte(textGender);

        logger.LogInfo(TAG, "Person Info:  Name: "    + textName    + ", " +
                                                   "Surname: " + textSurname + ", " +
                                                   "Email: "   + textEmail   + ", " +
                                                   "Age: "     + intAge      + ", " +
                                                   "Gender: "  + textGender  + " (" + byteGender + ")");

        Person   person   = new Person(textName, textSurname, textEmail, intAge, byteGender);
        DBHelper dbHelper = new DBHelper(this);

        long res_insert = dbHelper.insertPerson(person);
        if (res_insert == -1) {
            logger.LogErr(TAG, "Error while insert person in table");
            Toast.makeText(this, "Error while insert person in table", Toast.LENGTH_LONG).show();
        } else if (res_insert == -2) {
            logger.LogInfo(TAG, "Person exist in table");
            Toast.makeText(this, "Пользователь уже существует", Toast.LENGTH_LONG).show();
        } else {
            logger.LogInfo(TAG, "Insert successful");
            Toast.makeText(this, "Insert successful", Toast.LENGTH_LONG).show();
        }
    }

    /** Convert string gender to byte to save in database
     *
     * @param textGender text of gender
     * @return byte value of gender
     */
    private byte ConvertGenderStrToByte(String textGender) {
        logger.LogInfo(TAG, "call ConvertGenderStrToByte");
        logger.LogInfo(TAG, "Person select " + textGender);

        switch (textGender) {
            case "Мужской":
                return 1;
            case "Женский":
                return 2;
            case "Неопределённый":
                return 3;
            default:
                return 0;
        }
    }
}