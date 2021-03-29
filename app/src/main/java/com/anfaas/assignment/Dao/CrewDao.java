package com.anfaas.assignment.Dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.anfaas.assignment.Modal.Crew;
import com.anfaas.assignment.Modal.CrewDetail;

import java.util.List;

@Dao
public interface CrewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Crew> crewList);

    @Query("DELETE FROM crew")
    void deleteAll();

    @Query("SELECT * FROM crew ORDER BY database_id ASC")
    DataSource.Factory<Integer,Crew> getAllCrew();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_crew_details(List<CrewDetail> crewDetailList);

    @Query("SELECT * FROM crew_detail")
    LiveData<List<CrewDetail>> getAllCrewDetails();
}
