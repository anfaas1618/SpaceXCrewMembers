package com.anfaas.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.anfaas.assignment.Adapter.CrewAdapter;
import com.anfaas.assignment.Listener.Listener;
import com.anfaas.assignment.Modal.Crew;
import com.anfaas.assignment.Network.NetworkApi;
import com.anfaas.assignment.Respository.CrewRepository;
import com.anfaas.assignment.ViewModel.CrewViewModel;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Listener {

    private static final String URL_DATA="http://api.spacexdata.com/";
    private CrewViewModel crewViewModel;
    private CrewAdapter crewAdapter;
    private RecyclerView recyclerView;
    private CrewRepository crewRepository;
    private PagedList<Crew> crew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        crewRepository=new CrewRepository(getApplication());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        crewAdapter=new CrewAdapter(this,this::onClickItemListener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        crewViewModel=new ViewModelProvider(this).get(CrewViewModel.class);
        crewViewModel.pagedListLiveData.observe(this, new Observer<PagedList<Crew>>() {
            @Override
            public void onChanged(PagedList<Crew> crewPagedList) {
                crewAdapter.submitList(crewPagedList);
                recyclerView.setAdapter(crewAdapter);
               crew=crewPagedList;
            }
        });
        saveDataIntoDatabase();
    }

    private void saveDataIntoDatabase() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL_DATA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkApi api=retrofit.create(NetworkApi.class);
       Call<List<Crew>> call=api.getAllCrew();
       call.enqueue(new Callback<List<Crew>>() {
           @Override
           public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {
               System.out.println("hoo"+response);

              if(response.isSuccessful())
              {
                  Log.d("main", "onResponse: "+response.body());
                  crewRepository.Insert(response.body());
              }
           }

           @Override
           public void onFailure(Call<List<Crew>> call, Throwable t) {
               Log.d("main", "onFailurec: "+t);
           }
       });

    }

    @Override
    public void onClickItemListener(int position) {
        Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("picture",crew.get(position).getPoster());
        intent.putExtra("wiki",crew.get(position).getCrew_description());
        intent.putExtra("status",crew.get(position).getStatus());
        intent.putExtra("name",crew.get(position).getCrew_name());
        if (crew.get(position) != null) {
            intent.putExtra("id", Objects.requireNonNull(crew.get(position)).getCrew_id());
        }
        startActivity(intent);
    }
}
