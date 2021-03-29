package com.anfaas.assignment.Respository;

import android.app.Application;
import android.os.AsyncTask;

import com.anfaas.assignment.Dao.CrewDao;
import com.anfaas.assignment.Database.CrewDatabase;
import com.anfaas.assignment.Modal.Crew;
import com.anfaas.assignment.Modal.CrewDetail;

import java.util.List;

public class CrewRepository {

    private CrewDatabase crewDatabase;
    private int id;

    public CrewRepository(Application application)
    {
        crewDatabase= CrewDatabase.getInstance(application);
    }

   public void Insert(List<Crew> crewList)
    {
        new InsertAsyncTask(crewDatabase).execute(crewList);
    }

    public void insertCrewDetail(List<CrewDetail> crewDetailList){
        new InsertCrewDetailAsyncTask(crewDatabase).execute(crewDetailList);
    }



    static class InsertAsyncTask extends AsyncTask<List<Crew>,Void,Void>
    {
        private CrewDao crewDao;

        private InsertAsyncTask(CrewDatabase crewDatabase)
        {
            crewDao=crewDatabase.crewDao();
        }

        @Override
        protected Void doInBackground(List<Crew>... pagedLists) {
            crewDao.insert(pagedLists[0]);
            return null;
        }
    }

    static class InsertCrewDetailAsyncTask extends AsyncTask<List<CrewDetail>,Void,Void>
    {
        private CrewDao crewDao;

        private InsertCrewDetailAsyncTask(CrewDatabase crewDatabase)
        {
            crewDao=crewDatabase.crewDao();
        }

        @Override
        protected Void doInBackground(List<CrewDetail>... lists) {
            crewDao.insert_crew_details(lists[0]);
            return null;
        }
    }
}
