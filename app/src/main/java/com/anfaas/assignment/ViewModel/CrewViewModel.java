package com.anfaas.assignment.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.anfaas.assignment.Dao.CrewDao;
import com.anfaas.assignment.Database.CrewDatabase;
import com.anfaas.assignment.Modal.Crew;

public class CrewViewModel extends AndroidViewModel {

    private CrewDao crewDao;

    public final LiveData<PagedList<Crew>> pagedListLiveData;

    public CrewViewModel(@NonNull Application application) {
        super(application);

        crewDao= CrewDatabase.getInstance(application).crewDao();
        pagedListLiveData=new LivePagedListBuilder<>(
                crewDao.getAllCrew(),10
        ).build();


    }


}
