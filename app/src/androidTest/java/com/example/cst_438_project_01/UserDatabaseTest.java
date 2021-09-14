package com.example.cst_438_project_01;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.cst_438_project_01.data_model.User;
import com.example.cst_438_project_01.database.UserDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
public class UserDatabaseTest {

    @Test
    public void testInsertUser() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDatabase userDB = UserDatabase.getInstance(appContext);
        
        userDB.userDao().addUser(new User("Billy", "password1"));
        assertEquals(userDB.userDao().findByUsername("Billy").getUsername(),"Billy");
    }


    //probably we want delete by ID
    @Test
    public void testDeleteUser() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDatabase userDB = UserDatabase.getInstance(appContext);

        userDB.userDao().addUser(new User("Billy", "password1"));
        System.out.println("hi");
        assertEquals(userDB.userDao().findByUsername("Billy").getUsername(),"Billy");
        userDB.userDao().deleteByUsername("Billy");
        assertEquals(userDB.userDao().getAllUsers().size(), 0);
    }
}
