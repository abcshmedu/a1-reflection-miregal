package edu.hm.eporcio;
import java.lang.reflect.*;

/**
 * A renderer for Objects.
 */
public class Renderer {

	/** The object to render. */
	private Object obj;
	
	/** Standard constructor.
	 *  @param obj The object to be rendered.
	 */
	public Renderer(Object obj){
		this.obj = obj;
	}
	
	/**
	 * Renders the object by describing all fields that have the RenderMe annotation.
	 * @return A string describing all fields in the format: name (type) value\n
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 */
	public String render() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		
		String result = "";
		
		Class<?> toRender = obj.getClass();
		String objectClass = "Instance of " + obj.getClass().getName().toString() + ":\n";
		Field[] fields = toRender.getFields();
		for (Field field : fields) {
			if (field.getAnnotation(edu.hm.eporcio.RenderMe.class) != null) {
				String renderClassFQDN = (String) field.getAnnotation(edu.hm.eporcio.RenderMe.class).with();	//retrieving annotation argument
				String value = "";
				if(renderClassFQDN.length() != 0){	//use specified renderer to get field value, or toString if none was specified
					value = (String) Class.forName(renderClassFQDN).getMethod("render", field.getType()).invoke(field);
				}
				else{
					Object fieldContent = field.get(obj);
					value = fieldContent.toString();
				}
				
				result = result + field.getName() + " (Type " + field.getType().getName() + "): " + value + "\n";
			}
		}
		return objectClass + result;
	}
}
