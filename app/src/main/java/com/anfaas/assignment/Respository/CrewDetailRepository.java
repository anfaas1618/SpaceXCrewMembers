package com.anfaas.assignment.Respository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.anfaas.assignment.Database.CrewDatabase;
import com.anfaas.assignment.Modal.CrewDetail;

import java.util.List;

public class CrewDetailRepository {

    private CrewDatabase crewDatabase;
    private LiveData<List<CrewDetail>> getAllCrewDetail;

    public CrewDetailRepository(Application application)
    {
      crewDatabase=CrewDatabase.getInstance(application);
      getAllCrewDetail=crewDatabase.crewDao().getAllCrewDetails();
    }

    public LiveData<List<CrewDetail>> getAllcrewDetails()
    {
        return getAllCrewDetail;
    }
}
