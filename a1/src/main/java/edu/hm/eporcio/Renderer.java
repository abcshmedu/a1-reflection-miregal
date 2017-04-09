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
    public Renderer(Object obj) {
        this.obj = obj;
    }
    
/**
 * Renders the object by describing all fields that have the RenderMe annotation.
 * @return A string describing all fields in the format: name (type) value\n
 * @throws IllegalAccessException If the default constructor of the specified render class is inaccessible.
 * @throws IllegalArgumentException If the default constructor of the specified render class is inaccessible.
 * @throws InvocationTargetException If the default constructor of the specified render class throws an exception.
 * @throws ClassNotFoundException If the specified renderer class does not exist.
 * @throws InstantiationException If the specified renderer class is abstract.
 * @throws NoSuchMethodException If the default constructor of the specified render class fails to return an object.
 * @throws SecurityException If an annotated field is private and cannot be changed to public.
 */
    public String render() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        
        String result = "";
        
        Class< ? > toRender = obj.getClass();
        result += "Instance of " + obj.getClass().getName() + ":\n";
        Field[] fields = toRender.getDeclaredFields();
        Field.setAccessible(fields, true);
        for (Field field : fields) {
            if (field.getAnnotation(edu.hm.eporcio.RenderMe.class) != null) {
                result += field.getName();
                result += " (Type ";
                String renderClassFQDN = (String) field.getAnnotation(edu.hm.eporcio.RenderMe.class).with();    //retrieving annotation argument
                if (renderClassFQDN.length() != 0) {    //use specified renderer to get field value, or toString if none was specified
                    Class< ? > renderClass = Class.forName(renderClassFQDN);
                    Object renderObject = renderClass.getConstructor().newInstance();
                    result += (String)renderClass.getMethod("render", field.getType()).invoke(renderObject, field.get(obj));
                }
                else {
                    Object fieldContent = field.get(obj);
                    result += field.getType().getName();
                    result += "): ";
                    result += fieldContent.toString();
                }
                result += "\n";
            }
        }

        Method[] methods = toRender.getDeclaredMethods();
        Method.setAccessible(methods, true);
        for (Method method : methods) {
            if (method.getAnnotation(edu.hm.eporcio.RenderMe.class) != null) {
                result += method.getName();
                result += " (Type ";
                result += method.getReturnType().getName();
                result += "): ";
                result += method.invoke(obj).toString();
                result += "\n";
            }
        }
        
        return result;
    }
}
