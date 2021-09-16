package com.example.cst_438_project_01.database;

import static org.junit.Assert.*;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.cst_438_project_01.dao.ConceptDAO;
import com.example.cst_438_project_01.data_model.Concept;
import com.example.cst_438_project_01.data_model.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ConceptDbTest {
        private ConceptDb db;
        private ConceptDAO dao;
        @Before
        public void createDb() {
            Context context = ApplicationProvider.getApplicationContext();
            db = Room.inMemoryDatabaseBuilder(context, ConceptDb.class).build();
            dao = db.concept();
        }

        @After
        public void closeDb() throws IOException {
            db.close();
        }

        @Test
        public void testInsertFindConcept() {
            dao.addConcept(new Concept(1,"thing","api.reference.com/1"));
            dao.addConcept(new Concept(2,"thing2","api.reference.com/2"));
            dao.addConcept(new Concept(3, "thing3","api.reference.com/3"));
            assertEquals(dao.findByApiReference("api.reference.com/1").getName(), "thing");
            assertEquals(dao.findByUserID(2).getName(), "thing2");
            assertEquals(dao.findByName("thing3").getName(), "thing3");
            dao.deleteAll();

        }

        @Test(expected = NullPointerException.class)
        public void testDeleteByName() {
            dao.addConcept(new Concept(1,"deleteMe","deleteme.com"));
            dao.deleteByName("deleteMe");
            dao.findByName("deleteMe").getName();
        }

        @Test(expected = NullPointerException.class)
        public void testDeleteByUserID() {
            dao.addConcept(new Concept(1,"deleteMe","deleteme.com"));
            dao.deleteByUserID(1);
            dao.findByName("deleteMe").getName();
        }

        @Test(expected = NullPointerException.class)
        public void testDeleteByApiReference() {
            dao.addConcept(new Concept(1,"deleteMe","deleteme.com"));
            dao.deleteByApiReference("deleteme.com");
            dao.findByName("deleteMe").getName();
        }

        @Test(expected = NullPointerException.class)
        public void testDeleteAll() {
            dao.addConcept(new Concept(1,"deleteMe","deleteme.com"));
            dao.deleteAll();
            dao.findByName("deleteMe").getName();
        }

}