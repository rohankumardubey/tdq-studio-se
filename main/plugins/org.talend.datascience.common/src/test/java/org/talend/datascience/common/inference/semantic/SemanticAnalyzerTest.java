package org.talend.datascience.common.inference.semantic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.talend.dataquality.semantic.recognizer.CategoryRecognizerBuilder;
import org.talend.dataquality.semantic.recognizer.CategoryRecognizerBuilder.Mode;
import org.talend.datascience.common.inference.AnalyzerTest;

/**
 * This test is ignored for the time being because the dictionary path and key word path is hard coded, they should be
 * replaced later by elastic search server.
 * 
 * @author zhao
 *
 */
public class SemanticAnalyzerTest extends AnalyzerTest {

    private SemanticAnalyzer semanticAnalyzer;

    @Before
    public void setUp() throws Exception {
        final URI ddPath = this.getClass().getResource("/luceneIdx/dictionary").toURI();
        final URI kwPath = this.getClass().getResource("/luceneIdx/keyword").toURI();
        final CategoryRecognizerBuilder builder = CategoryRecognizerBuilder.newBuilder() //
                .ddPath(ddPath) //
                .kwPath(kwPath) //
                .setMode(Mode.LUCENE);
        semanticAnalyzer = new SemanticAnalyzer(builder);
        semanticAnalyzer.init();
    }

    @After
    public void tearDown() throws Exception {
        assertNotNull(semanticAnalyzer);
        semanticAnalyzer.end();
    }

    @Test
    public void testHandle() {
        final List<String[]> records = getRecords(AnalyzerTest.class.getResourceAsStream("employee_1000.csv"));
        for (String[] record : records) {
            semanticAnalyzer.analyze(record);
        }
        List<SemanticType> result = semanticAnalyzer.getResult();
        int columnIndex = 0;
        String[] expectedCategories = new String[] { "", //
                "FULL_NAME", //
                "", //
                "", //
                "", //
                "", //
                "FULL_NAME", //
                "", //
                "", //
                "DATE",  //
                "DATE",  //
                "", //
                "", //
                "", //
                "FULL_NAME", //
                "", //
                "GENDER",  //
                "FULL_NAME" //
        };
        for (SemanticType columnSemanticType : result) {
            assertEquals(expectedCategories[columnIndex++], columnSemanticType.getSuggestedCategory());
        }
    }

    @Ignore
    @Test
    public void testValidMailHandle() {
        final List<String[]> records = getRecords(AnalyzerTest.class.getResourceAsStream("employee_valid_email.csv"));
        for (String[] record : records) {
            semanticAnalyzer.analyze(record);
        }
        List<SemanticType> result = semanticAnalyzer.getResult();
        int columnIndex = 0;
        String[] expectedCategories = new String[] { "", //
                "FULL_NAME", //
                "", //
                "", //
                "EMAIL", //
                "", //
                "", //
                "", //
                "", //
                "DATE",  //
                "DATE",  //
                "", //
                "", //
                "", //
                "FULL_NAME", //
                "GENDER", //
                "GENDER",  //
                "FULL_NAME" //
        };
        for (SemanticType columnSemanticType : result) {
            assertEquals(expectedCategories[columnIndex++], columnSemanticType.getSuggestedCategory());
        }
    }

}
