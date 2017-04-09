package edu.hm.eporcio;
import java.util.*;
/**
 * Test class for RenderMe annotation.
 */
public class SomeClass {
    //CHECKSTYLE:OFF
    @RenderMe private int foo;
    @RenderMe(with = "edu.hm.renderer.ArrayRenderer") private int[] array = {1, 2, 3};
    @RenderMe private Date date = new Date(123456789);
    //CHECKSTYLE:ON
    /**
     * Default constructor.
     * @param foo Value of field foo.
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }
    
    /**
     * Test method for RenderMe annotation.
     * @return "Some return value"
     */
    @RenderMe
    private String someMethod() {
        return "Some return value";
    }
}