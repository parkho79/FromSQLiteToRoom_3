package com.parkho.sqlite;

import com.parkho.sqlite.database.PhStudentEntity;

import java.util.List;

public interface PhUpdateInterface {
    void onUpdate(List<PhStudentEntity> a_studentEntityList);
}
