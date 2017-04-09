package edu.hm.eporcio;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class RenderTest 
{

	 private SomeClass toRender;      
	 private Renderer renderer;
	 private String expectedString;
	 
	 public RenderTest(int value, String msg)
	 {
		 this.toRender = new SomeClass(value);
		 this.expectedString = msg;
		 renderer = new Renderer(toRender);
	 }
	 
	 @Parameters
	 public static Collection<Object[]> data()
	 {
		 return Arrays.asList(new Object[][]
		 {
			 {
				 5, 
				 "Instance of edu.hm.eporcio.SomeClass:\n" 
				 + "foo (Type int): 5\n"
				 + "array (Type int[]) [1, 2, 3, ]\n"
				 + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"
			 },
			 {
				 2, 
				 "Instance of edu.hm.eporcio.SomeClass:\n" 
				 + "foo (Type int): 2\n"
				 + "array (Type int[]) [1, 2, 3, ]\n"
				 + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"
			 }
		 }); 
	 }
	    
	 @Test    
	 public void testRendering() throws Exception 
	 {    
		 assertEquals(expectedString, renderer.render());      
	 }
}
