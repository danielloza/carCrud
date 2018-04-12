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

public class UpdateRecordActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mMarkEditText;
    private EditText mModelEditText;
    private EditText mTransmissionEditText;
    private EditText mCombustibleEditText;
    private EditText mYearEditText;
    private EditText mImageEditText;
    private Button mUpdateBtn;

    private CarDBHelper dbHelper;
    private long receivedCarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);

        //init
        mNameEditText = (EditText)findViewById(R.id.car_name_update);
        mMarkEditText = (EditText)findViewById(R.id.car_mark_update);
        mModelEditText = (EditText)findViewById(R.id.car_model_update);
        mTransmissionEditText = (EditText)findViewById(R.id.car_transmission_update);
        mCombustibleEditText = (EditText)findViewById(R.id.car_combustible_update);
        mYearEditText = (EditText)findViewById(R.id.car_year_update);
        mImageEditText = (EditText)findViewById(R.id.car_image_link_update);
        mUpdateBtn = (Button)findViewById(R.id.update_car_button);

        dbHelper = new CarDBHelper(this);

        try {
            //get intent to get car id
            receivedCarId = getIntent().getLongExtra("CAR_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***populate car data before update***/
        Car queriedCar = dbHelper.getCar(receivedCarId);
        //set field to this user data
        mNameEditText.setText(queriedCar.getName());
        mMarkEditText.setText(queriedCar.getMark());
        mModelEditText.setText(queriedCar.getModel());
        mTransmissionEditText.setText(queriedCar.getTransmission());
        mCombustibleEditText.setText(queriedCar.getCombustible());
        mYearEditText.setText(queriedCar.getYear());
        mImageEditText.setText(queriedCar.getImage());

        //listen to add button click to update
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                updateCar();
            }
        });
    }

    private void updateCar(){
        String name = mNameEditText.getText().toString().trim();
        String mark = mMarkEditText.getText().toString().trim();
        String model = mModelEditText.getText().toString().trim();
        String transmission = mTransmissionEditText.getText().toString().trim();
        String combustible = mCombustibleEditText.getText().toString().trim();
        String year = mYearEditText.getText().toString().trim();
        String image = mImageEditText.getText().toString().trim();

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

        //create updated car
        Car updatedCar = new Car(name, mark, model, transmission, combustible,year, image);

        //call dbhelper update
        dbHelper.updateCarRecord(receivedCarId, this, updatedCar);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
