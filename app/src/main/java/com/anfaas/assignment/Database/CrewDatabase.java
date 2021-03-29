package com.anfaas.assignment.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.anfaas.assignment.Dao.CrewDao;
import com.anfaas.assignment.Modal.Crew;
import com.anfaas.assignment.Modal.CrewDetail;

@Database(entities = {Crew.class, CrewDetail.class},version = 10)

public abstract class CrewDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="Crew_list";
    public abstract CrewDao crewDao();


    private static volatile CrewDatabase INSTANCE=null;

    public static CrewDatabase getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (CrewDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE= Room.databaseBuilder(context,CrewDatabase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };

    static class PopulateAsynTask extends AsyncTask<Void,Void,Void>
    {
        private CrewDao crewDao;

        private PopulateAsynTask(CrewDatabase crewDatabase){
            crewDao=crewDatabase.crewDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            crewDao.deleteAll();
            return null;
        }
    }
}
