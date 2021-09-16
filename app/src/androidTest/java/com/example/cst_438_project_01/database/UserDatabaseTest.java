package com.example.cst_438_project_01.database;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.cst_438_project_01.dao.ConceptDAO;
import com.example.cst_438_project_01.dao.UserDAO;
import com.example.cst_438_project_01.data_model.Concept;
import com.example.cst_438_project_01.data_model.User;
import com.example.cst_438_project_01.database.UserDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class UserDatabaseTest {
    private UserDatabase db;
    private UserDAO dao;
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, UserDatabase.class).build();
        dao = db.userDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsertFindUser() {
        dao.addUser(new User("u1", "p1", 1));
        dao.addUser(new User("u2", "p2", 2));
        dao.addUser(new User("u3", "p3", 3));
        assertEquals(dao.findByUsername("u1").getUserID(),1);
        assertEquals(dao.findByUserID("2").getUsername(),"u2");
        assertEquals(dao.findByPassword("p3").getPassword(),"p3");
        assertEquals(dao.findByPassword("p3").getUserID(),3);
        dao.deleteAll();
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteByUsername() {
        dao.addUser(new User("deleteMe", "p1", 1));
        dao.deleteByUsername("deleteMe");
        dao.findByUsername("deleteMe").getUserID();
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteByUserID() {
        dao.addUser(new User("deleteMe", "p1", 1));
        dao.deleteByUserID("1");
        dao.findByUsername("deleteMe").getUserID();
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteByPassword() {
        dao.addUser(new User("deleteMe", "deleteMePw", 1));
        dao.deleteByPassword("deleteMePw");
        dao.findByUsername("deleteMe").getUsername();
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteAll() {
        dao.addUser(new User("deleteMe", "p1", 1));
        dao.deleteAll();
        dao.findByUsername("deleteMe").getUsername();
    }
}
