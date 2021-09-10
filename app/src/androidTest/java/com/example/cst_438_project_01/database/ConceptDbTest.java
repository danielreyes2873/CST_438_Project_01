package com.example.cst_438_project_01.database;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import com.example.cst_438_project_01.data_model.Concept;
import junit.framework.TestCase;
import org.junit.Test;

public class ConceptDbTest extends TestCase {
        @Test
        public void testInsertConcept() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ConceptDb conceptDb = ConceptDb.getInstance(appContext);

            conceptDb.concept().addConcept(new Concept(1,"thing","api.reference.com"));
            assertEquals(conceptDb.concept().findByName("thing").getName(),"thing");
        }

        @Test
        public void testDeleteUser() {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ConceptDb conceptDb = ConceptDb.getInstance(appContext);

            conceptDb.concept().addConcept(new Concept(1,"thing","api.reference.com"));
            assertEquals(conceptDb.concept().findByName("thing").getName(),"thing");
            conceptDb.concept().deleteByName("thing");
            assertEquals(conceptDb.concept().getAllConcepts().size(), 0);
        }


}