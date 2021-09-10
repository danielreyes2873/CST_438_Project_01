package com.example.cst_438_project_01.database;

import static org.junit.Assert.*;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import com.example.cst_438_project_01.data_model.Concept;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConceptDbTest {

        @Before
        public void clearDB() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ConceptDb conceptDb = ConceptDb.getInstance(appContext);
            conceptDb.concept().deleteAll();
        }
        @Test
        public void testInsertConcept() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ConceptDb conceptDb = ConceptDb.getInstance(appContext);
            conceptDb.concept().addConcept(new Concept(1,"thing","api.reference.com"));
            assertEquals(conceptDb.concept().getAllConcepts().size(), 1);
            conceptDb.concept().addConcept(new Concept(2,"otherThing","api.reference.com"));
            assertEquals(conceptDb.concept().getAllConcepts().size(), 2);
        }

        @Test
        public void testCount() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ConceptDb conceptDb = ConceptDb.getInstance(appContext);
            assertEquals(conceptDb.concept().count(), 0);
            conceptDb.concept().addConcept(new Concept(3,"anotherThing","api.reference.com"));
            assertEquals(conceptDb.concept().count(), 1);
            conceptDb.concept().addConcept(new Concept(4,"andAnotherThing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(5,"oneMoreThing","api.reference.com"));
            assertEquals(conceptDb.concept().count(), 3);
        }

        @Test
        public void testDeleteByName() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ConceptDb conceptDb = ConceptDb.getInstance(appContext);
            conceptDb.concept().addConcept(new Concept(1,"thing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(2,"otherThing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(3,"anotherThing","api.reference.com"));
            conceptDb.concept().deleteByName("thing");
            assertEquals(conceptDb.concept().count(), 2);
            conceptDb.concept().deleteByName("anotherThing");
            assertEquals(conceptDb.concept().count(), 1);
        }

        @Test
        public void testDeleteByUserID() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ConceptDb conceptDb = ConceptDb.getInstance(appContext);
            conceptDb.concept().addConcept(new Concept(1,"thing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(2,"otherThing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(3,"anotherThing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(4,"andAnotherThing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(5,"oneMoreThing","api.reference.com"));
            conceptDb.concept().deleteByUserID(2);
            conceptDb.concept().deleteByUserID(4);
            conceptDb.concept().deleteByUserID(3);
            assertEquals(conceptDb.concept().count(), 2);
        }

        @Test
        public void testDeleteByApiReference() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ConceptDb conceptDb = ConceptDb.getInstance(appContext);
            conceptDb.concept().addConcept(new Concept(2,"otherThing","api.reference.thisone.com"));
            conceptDb.concept().addConcept(new Concept(4,"andAnotherThing","api.reference.thatone.com"));
            conceptDb.concept().addConcept(new Concept(5,"oneMoreThing","api.reference.theotherone.com"));
            conceptDb.concept().deleteByApiReference("api.reference.thatone.com");
            conceptDb.concept().deleteByApiReference("api.reference.thisone.com");
            assertEquals(conceptDb.concept().count(), 1);
        }

        @Test
        public void testFindByName() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ConceptDb conceptDb = ConceptDb.getInstance(appContext);
            conceptDb.concept().addConcept(new Concept(1,"thing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(2,"otherThing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(3,"anotherThing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(4,"andAnotherThing","api.reference.com"));
            conceptDb.concept().addConcept(new Concept(5,"oneMoreThing","api.reference.com"));
            assertEquals(conceptDb.concept().findByName("otherThing").getName(),"otherThing");
            assertEquals(conceptDb.concept().findByName("anotherThing").getName(),"anotherThing");
            assertEquals(conceptDb.concept().findByName("oneMoreThing").getName(),"oneMoreThing");
        }

    @Test
    public void testFindByUserID() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ConceptDb conceptDb = ConceptDb.getInstance(appContext);
        conceptDb.concept().addConcept(new Concept(1,"thing","api.reference.com"));
        conceptDb.concept().addConcept(new Concept(2,"otherThing","api.reference.com"));
        conceptDb.concept().addConcept(new Concept(3,"anotherThing","api.reference.com"));
        conceptDb.concept().addConcept(new Concept(4,"andAnotherThing","api.reference.com"));
        conceptDb.concept().addConcept(new Concept(5,"oneMoreThing","api.reference.com"));
        assertEquals(conceptDb.concept().findByUserID(2).getUserID(),2);
        assertEquals(conceptDb.concept().findByUserID(5).getUserID(),5);
        assertEquals(conceptDb.concept().findByUserID(4).getUserID(),4);
    }

    @Test
    public void testFindByApiReference() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ConceptDb conceptDb = ConceptDb.getInstance(appContext);
        conceptDb.concept().addConcept(new Concept(1,"thing","api.reference.thisone.com"));
        conceptDb.concept().addConcept(new Concept(2,"otherThing","api.reference.thatone.com"));
        conceptDb.concept().addConcept(new Concept(3,"anotherThing","api.reference.theotherone.com"));
        conceptDb.concept().addConcept(new Concept(4,"andAnotherThing","api.reference.orthisone.com"));
        conceptDb.concept().addConcept(new Concept(5,"oneMoreThing","api.reference.nothisone.com"));
        assertEquals(conceptDb.concept().findByApiReference("api.reference.nothisone.com").getApi_reference(),"api.reference.nothisone.com");
        assertEquals(conceptDb.concept().findByApiReference("api.reference.theotherone.com").getApi_reference(),"api.reference.theotherone.com");
        assertEquals(conceptDb.concept().findByApiReference("api.reference.thatone.com").getApi_reference(),"api.reference.thatone.com");
    }

}