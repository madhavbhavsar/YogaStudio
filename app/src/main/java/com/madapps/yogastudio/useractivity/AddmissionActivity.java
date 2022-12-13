package com.madapps.yogastudio.useractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.madapps.yogastudio.R;
import com.madapps.yogastudio.useractivity.addmission.AddmissionAdapter;
import com.madapps.yogastudio.useractivity.addmission.AddmissionModel;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;

public class AddmissionActivity extends AppCompatActivity {

    Button userhome;
    Button addmissionpage;
    Button addmembers;
    RecyclerView rview;
    RecyclerView.LayoutManager manager;
    AddmissionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmission);


        userhome = (Button) findViewById(R.id.userhome);
        addmissionpage = (Button) findViewById(R.id.addmissionpage);
        addmembers = (Button) findViewById(R.id.addmembers);
        rview = (RecyclerView) findViewById(R.id.rview);

        userhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddmissionActivity.this,UserDashboard.class);
                startActivity(i);
                finish();
            }
        });

        setUpRecyclerviewhere();

        addmembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(AddmissionActivity.this)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0, 50, 0)
                        .setContentHolder(new ViewHolder(R.layout.addmem_box))
                        .setExpanded(false)
                        .create();
                dialogPlus.show();

                View hView = (LinearLayout) dialogPlus.getHolderView();

                EditText fullname = (EditText) hView.findViewById(R.id.fullname);
                EditText formonth = (EditText) hView.findViewById(R.id.formonth);
                EditText age = (EditText) hView.findViewById(R.id.age);
                ImageButton month= (ImageButton) hView.findViewById(R.id.monthdatepicker);
                Button addmem = (Button) hView.findViewById(R.id.addmem);
                Button cancel = (Button) hView.findViewById(R.id.cancelbtn);
                AutoCompleteTextView batches = (AutoCompleteTextView) hView.findViewById(R.id.batchno);
                ArrayAdapter arrayAdapter;
                String[] arr = getResources().getStringArray(R.array.batches);
                arrayAdapter = new ArrayAdapter(AddmissionActivity.this,R.layout.dropdown_batches,arr);
                batches.setAdapter(arrayAdapter);



                addmem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(fullname.getText().toString().isEmpty()){
                            Toast.makeText(AddmissionActivity.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                        } else if (age.getText().toString().isEmpty()){
                            Toast.makeText(AddmissionActivity.this, "Enter Age", Toast.LENGTH_SHORT).show();
                        } else if ( Integer.parseInt( age.getText().toString().trim())<18 || Integer.parseInt( age.getText().toString().trim())>65 ){
                            Toast.makeText(AddmissionActivity.this, "Age should be between 18 to 65", Toast.LENGTH_SHORT).show();
                        } else if(batches.getText().toString().trim().isEmpty()){
                            Toast.makeText(AddmissionActivity.this, "Select Batch", Toast.LENGTH_SHORT).show();
                        } else {

                            HashMap<String,String> hp = new HashMap<>();
                            hp.put("fullname", fullname.getText().toString().trim() );
                            hp.put("age", age.getText().toString().trim() );
                            hp.put("formonth", formonth.getText().toString().trim() );
                            hp.put("batchno", batches.getText().toString().trim() );
                            hp.put("username","username in app");

                            FirebaseFirestore.getInstance().collection("Members").add(hp).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                    Toast.makeText(AddmissionActivity.this, "Member Added", Toast.LENGTH_SHORT).show();
                                    dialogPlus.dismiss();

                                }
                            });






                        }



                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPlus.dismiss();
                        //adapter.stopListening();
                    }
                });


            }
        });







    }

    private void setUpRecyclerviewhere() {


        rview = findViewById(R.id.rview);
        manager = new LinearLayoutManager(this);
        rview.setLayoutManager(manager);

        Query query = FirebaseFirestore.getInstance().collection("Members").whereEqualTo("username","username in app");
        FirestoreRecyclerOptions<AddmissionModel> options = new FirestoreRecyclerOptions.Builder<AddmissionModel>()
                .setQuery(query, AddmissionModel.class).build();
        adapter = new AddmissionAdapter(options, AddmissionActivity.this);
        adapter.notifyDataSetChanged();
        //rv.setHasFixedSize(true);
        rview.setAdapter(adapter);
        adapter.startListening();


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(AddmissionActivity.this,UserDashboard.class);
        startActivity(i);
        finish();

    }
}