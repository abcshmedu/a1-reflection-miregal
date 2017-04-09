package edu.hm.renderer;

/**
 * Renderer for int arrays.
 */
public class ArrayRenderer {
    /**
     * Renders the specified int array by naming its type and listing its elements.
     * @param toRender The array to be rendered
     * @return Type and elements of the array to be rendered
     */
    public String render(int[] toRender) {
        String result = "";
        result += toRender.getClass().getSimpleName();
        result += ") [";
        for (int currentIndex = 0; currentIndex < toRender.length; currentIndex++) {
            result += toRender[currentIndex] + ", ";
        }
        result += "]";
        return result;
    }
}
