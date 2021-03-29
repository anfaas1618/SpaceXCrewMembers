package com.anfaas.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.anfaas.assignment.Modal.CrewDetail;
import com.anfaas.assignment.Network.DetailsApi;
import com.anfaas.assignment.Respository.CrewRepository;
import com.anfaas.assignment.ViewModel.CrewDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {
 public static final String DATA_URL="http://api.spacexdata.com/";
    private static final String TAG ="main" ;
    private CrewRepository repository;
 private int position;
    String image,name,status,wiki;
    private String get_id;
 private List<CrewDetail> crewDetails;
 private TextView name_details,description_details,status_details;
 private ImageView poster_details;
 private CrewDetailViewModel crewDetailViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name_details=findViewById(R.id.name_detail);
        status_details=findViewById(R.id.status_detail);
        description_details=findViewById(R.id.description_detail);
        poster_details=findViewById(R.id.image_detail);
        repository=new CrewRepository(getApplication());
        crewDetails=new ArrayList<>();
        crewDetailViewModel=new ViewModelProvider(this).get(CrewDetailViewModel.class);
        getCrewDetails();
        setData();
    }

    private void setData() {
        Bundle bundle=getIntent().getExtras();

        if(bundle !=null)
        {
            position=bundle.getInt("position",0);
            get_id=bundle.getString("id","here");
             image=bundle.getString("picture","null");
            name=bundle.getString("name","null");
            wiki=bundle.getString("wiki","null");
            status=bundle.getString("status","null");
            crewDetailViewModel.getAllCrewDetail().observe(this, new Observer<List<CrewDetail>>() {
                @Override
                public void onChanged(List<CrewDetail> crewDetailList) {
                 Glide.with(getApplicationContext())
                           .load(image)
                           .into(poster_details);
                 name_details.setText(name);
                 status_details.setText(""+status);
                   description_details.setText(wiki);
//
//                    Log.d(TAG, "onChanged: "+crewDetailList.get(position).getCrew_name());
                }
            });

        }
    }

    private void getCrewDetails() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(DATA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DetailsApi api=retrofit.create(DetailsApi.class);
        Call<List<CrewDetail>> call=api.getAllCrewDetails();
        call.enqueue(new Callback<List<CrewDetail>>() {
            @Override
            public void onResponse(Call<List<CrewDetail>> call, Response<List<CrewDetail>> response) {
                Log.d("maina", "onFailure: "+response);
                if(response.isSuccessful())

                {
                   repository.insertCrewDetail(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CrewDetail>> call, Throwable t) {
                Log.d("maina", "onFailure: "+t.getMessage());
            }
        });
    }
}