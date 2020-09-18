package com.parkho.sqlite.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.parkho.sqlite.PhUpdateInterface;

import java.util.List;

public class PhStudentViewModel extends AndroidViewModel {

    private PhStudentRepository mRepository;

    public PhStudentViewModel(@NonNull Application a_application) {
        super(a_application);

        mRepository = new PhStudentRepository(a_application);
    }

    public List<PhStudentEntity> getAllStudents(PhUpdateInterface a_updateInterface) {
        return mRepository.getAllStudents(a_updateInterface);
    }

    public void insert(PhStudentEntity a_studentEntity) {
        mRepository.insert(a_studentEntity);
    }

    public void deleteById(int a_id) {
        mRepository.deleteById(a_id);
    }
}
