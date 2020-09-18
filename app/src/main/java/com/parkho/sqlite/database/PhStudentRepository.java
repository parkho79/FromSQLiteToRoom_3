package com.parkho.sqlite.database;

import android.app.Application;
import android.os.AsyncTask;

import com.parkho.sqlite.PhUpdateInterface;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PhStudentRepository {

    private PhStudentDao mStudentDao;

    public PhStudentRepository(Application a_application) {
        mStudentDao = PhDatabase.getInstance(a_application).studentDao();
    }

    public List<PhStudentEntity> getAllStudents(PhUpdateInterface a_updateInterface) {
        List<PhStudentEntity> students = null;
        try {
            students = new getAllUsersAsyncTask(mStudentDao, a_updateInterface).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void insert(PhStudentEntity a_studentEntity) {
        new insertAsyncTask(mStudentDao).execute(a_studentEntity);
    }

    public void deleteById(int a_id) {
        new deleteAsyncTask(mStudentDao).execute(a_id);
    }

    private static class getAllUsersAsyncTask extends AsyncTask<Void, Void, List<PhStudentEntity>> {
        private PhStudentDao mAsyncTaskDao;
        private PhUpdateInterface mUpdateInterface;

        getAllUsersAsyncTask(PhStudentDao a_dao, PhUpdateInterface a_updateInterface) {
            mAsyncTaskDao = a_dao;
            mUpdateInterface = a_updateInterface;
        }

        @Override
        protected List<PhStudentEntity> doInBackground(Void... url) {
            return mAsyncTaskDao.getAllStudents();
        }

        @Override
        protected void onPostExecute(List<PhStudentEntity> a_studentEntityList) {
            mUpdateInterface.onUpdate(a_studentEntityList);
        }
    }

    private static class insertAsyncTask extends AsyncTask<PhStudentEntity, Void, Void> {
        private PhStudentDao asyncTaskDao;

        insertAsyncTask(PhStudentDao a_dao) {
            asyncTaskDao = a_dao;
        }

        @Override
        protected Void doInBackground(final PhStudentEntity... studentEntities) {
            asyncTaskDao.insert(studentEntities[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private PhStudentDao asyncTaskDao;

        deleteAsyncTask(PhStudentDao a_dao) {
            asyncTaskDao = a_dao;
        }

        @Override
        protected Void doInBackground(final Integer... a_id) {
            asyncTaskDao.deleteById(a_id[0]);
            return null;
        }
    }
}