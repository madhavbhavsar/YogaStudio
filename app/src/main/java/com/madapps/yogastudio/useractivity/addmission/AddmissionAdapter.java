package com.madapps.yogastudio.useractivity.addmission;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.madapps.yogastudio.R;

public class AddmissionAdapter extends FirestoreRecyclerAdapter<AddmissionModel,AddmissionAdapter.AddVH> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
        Context context;
    public AddmissionAdapter(@NonNull FirestoreRecyclerOptions<AddmissionModel> options, Context context) {
        super(options);
        this.context =context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AddVH holder, int position, @NonNull AddmissionModel model) {

        holder.fullname.setText(model.getFullname());
        holder.age.setText(model.getAge());
        holder.batchno.setText(model.getBatchno());
        holder.month.setText(model.getFormonth());
        holder.username.setText(model.getUsername());


    }

    @NonNull
    @Override
    public AddVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addmission_master_layout, parent, false);
        return new AddmissionAdapter.AddVH(view);
    }

    public class AddVH extends RecyclerView.ViewHolder{

        TextView fullname,age,batchno,month,username;
        public AddVH(@NonNull View itemView) {
            super(itemView);
            fullname = (TextView) itemView.findViewById(R.id.fullnamehere);
            age = (TextView) itemView.findViewById(R.id.agehere);
            batchno = (TextView) itemView.findViewById(R.id.batchhere);
            username = (TextView) itemView.findViewById(R.id.usernamehere);
            month = (TextView) itemView.findViewById(R.id.monthhere);
        }
    }

}
