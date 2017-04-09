package edu.hm.eporcio;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

/**
 * Annotation for fields that should be rendered by a renderer.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RenderMe {
    /**
     * A renderer for fields that are not primitive types.
     * @return FQDN of the renderer to be used.
     */
    String with() default "";
}