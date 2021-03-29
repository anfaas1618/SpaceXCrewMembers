package com.anfaas.assignment.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anfaas.assignment.Modal.CrewDetail;
import com.anfaas.assignment.Respository.CrewDetailRepository;

import java.util.List;

public class CrewDetailViewModel extends AndroidViewModel {

    private LiveData<List<CrewDetail>> crewDetailLiveData;
    private CrewDetailRepository crewRepository;

    public CrewDetailViewModel(@NonNull Application application) {
        super(application);
        crewRepository=new CrewDetailRepository(application);
        crewDetailLiveData=crewRepository.getAllcrewDetails();
    }

    public LiveData<List<CrewDetail>> getAllCrewDetail()
    {
        return crewDetailLiveData;
    }
}
