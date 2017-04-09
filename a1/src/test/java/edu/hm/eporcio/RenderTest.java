package edu.hm.eporcio;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit test class for RenderMe annotation.
 */
@RunWith(Parameterized.class)
public class RenderTest {

     private SomeClass toRender;      
     private Renderer renderer;
     private String expectedString;
     
     /**
      * Standard constructor.
      * @param value The value with which the field "foo" in "SomeClass" will be initialized.
      * @param expectedString The expected return value.
      */
     public RenderTest(int value, String expectedString) {
         this.toRender = new SomeClass(value);
         this.expectedString = expectedString;
         renderer = new Renderer(toRender);
     }
     
     /**
      * Parameters with which the parameterized unit tests will be executed.
      * @return A Collection of parameters.
      */
     @Parameters
     public static Collection<Object[]> data() {
         return Arrays.asList(new Object[][]
         {
             {
                 //CHECKSTYLE:OFF
                 5, 
                 //CHECKSTYLE:ON
                 "Instance of edu.hm.eporcio.SomeClass:\n" 
                 + "foo (Type int): 5\n"
                 + "array (Type int[]) [1, 2, 3, ]\n"
                 + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"
                 + "someMethod (Type java.lang.String): Some return value\n"
             },
             {
                 2, 
                 "Instance of edu.hm.eporcio.SomeClass:\n" 
                 + "foo (Type int): 2\n"
                 + "array (Type int[]) [1, 2, 3, ]\n"
                 + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"
                 + "someMethod (Type java.lang.String): Some return value\n"
             }
         }); 
     }
        
     /**
      * Standard test case.
      */
     @Test    
     public void testRendering() {
         try {
            assertEquals(expectedString, renderer.render());
        } catch (Exception e) {
            assertTrue("Exeption was thrown", false);
        }
     }
}
