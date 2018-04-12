package mx.com.dloza.crudcar.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import mx.com.dloza.crudcar.R;
import mx.com.dloza.crudcar.UpdateRecordActivity;
import mx.com.dloza.crudcar.model.Car;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private List<Car> mCarList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView carNameTxtV;
        public TextView carMarkTxtV;
        public TextView carModelTxtV;
        public TextView carTransmissionTxtV;
        public TextView carCombustibleTxtV;
        public TextView carYearTxtV;
        public ImageView carImageImgV;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            carNameTxtV = (TextView) v.findViewById(R.id.name);
            carModelTxtV = (TextView) v.findViewById(R.id.model);
            carMarkTxtV = (TextView) v.findViewById(R.id.mark);
            carTransmissionTxtV = (TextView) v.findViewById(R.id.transmission);
            carCombustibleTxtV = (TextView) v.findViewById(R.id.combustible);
            carYearTxtV = (TextView) v.findViewById(R.id.year);
            carImageImgV = (ImageView) v.findViewById(R.id.image);

        }
    }

    public void add(int position, Car car) {
        mCarList.add(position, car);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mCarList.remove(position);
        notifyItemRemoved(position);
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public CarAdapter(List<Car> myDataset, Context context, RecyclerView recyclerView) {
        mCarList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.single_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Car car = mCarList.get(position);
        holder.carNameTxtV.setText("Name: " + car.getName());
        holder.carMarkTxtV.setText("Mark: " + car.getMark());
        holder.carModelTxtV.setText("Model: " + car.getModel());
        holder.carTransmissionTxtV.setText("Transmission: " + car.getTransmission());
        holder.carCombustibleTxtV.setText("Combustible: " + car.getCombustible());
        holder.carYearTxtV.setText("Year: " + car.getYear());
        Picasso.with(mContext).load(car.getImage()).placeholder(R.mipmap.ic_launcher).into(holder.carImageImgV);

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete car?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    //go to update activity
                        goToUpdateActivity(car.getId());

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CarDBHelper dbHelper = new CarDBHelper(mContext);
                        dbHelper.deleteCarRecord(car.getId(), mContext);

                        mCarList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mCarList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });


    }

    private void goToUpdateActivity(long carId){
        Intent goToUpdate = new Intent(mContext, UpdateRecordActivity.class);
        goToUpdate.putExtra("CAR_ID", carId);
        mContext.startActivity(goToUpdate);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCarList.size();
    }



}