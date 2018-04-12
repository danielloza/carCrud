package mx.com.dloza.crudcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mx.com.dloza.crudcar.model.Car;
import mx.com.dloza.crudcar.utils.CarDBHelper;

public class AddRecordActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mMarkEditText;
    private EditText mModelEditText;
    private EditText mTransmissionEditText;
    private EditText mCombustibleEditText;
    private EditText mYearEditText;
    private EditText mImageEditText;
    private Button mAddBtn;

    private CarDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        //init
        mNameEditText = (EditText)findViewById(R.id.car_name);
        mMarkEditText = (EditText)findViewById(R.id.car_mark);
        mModelEditText = (EditText)findViewById(R.id.car_model);
        mTransmissionEditText = (EditText)findViewById(R.id.car_transmission);
        mCombustibleEditText = (EditText)findViewById(R.id.car_combustible);
        mYearEditText = (EditText)findViewById(R.id.car_year);
        mImageEditText = (EditText)findViewById(R.id.car_image_link);
        mAddBtn = (Button)findViewById(R.id.add_new_car_button);

        //listen to add button click
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save car method
                saveCar();
            }
        });

    }

    private void saveCar(){
        String name = mNameEditText.getText().toString().trim();
        String mark = mMarkEditText.getText().toString().trim();
        String model = mModelEditText.getText().toString().trim();
        String transmission = mTransmissionEditText.getText().toString().trim();
        String combustible = mCombustibleEditText.getText().toString().trim();
        String year = mYearEditText.getText().toString().trim();
        String image = mImageEditText.getText().toString().trim();
        dbHelper = new CarDBHelper(this);

        if(name.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }

        if(mark.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an mark", Toast.LENGTH_SHORT).show();
        }

        if(model.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an model", Toast.LENGTH_SHORT).show();
        }

        if(transmission.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an transmission", Toast.LENGTH_SHORT).show();
        }

        if(combustible.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an combustible", Toast.LENGTH_SHORT).show();
        }

        if(year.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an year", Toast.LENGTH_SHORT).show();
        }

        if(image.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an image link", Toast.LENGTH_SHORT).show();
        }

        //create new car
        Car car = new Car(name, mark, model, transmission, combustible,year, image);
        dbHelper.saveNewCar(car);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(AddRecordActivity.this, MainActivity.class));
    }
}
