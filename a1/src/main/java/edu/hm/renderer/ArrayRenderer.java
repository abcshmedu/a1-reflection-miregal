package edu.hm.renderer;

/**
 * Renderer for int arrays.
 */
public class ArrayRenderer {
	/**
	 * Renders the specified int Array by listing its elements.
	 * @param toRender The array to be rendered
	 */
	public String render(int[] toRender) {
		String result = "";
		result += toRender.getClass().getSimpleName();
		result += ") [";
		for(int currentIndex = 0; currentIndex < toRender.length; currentIndex++){
			result += toRender[currentIndex] + ", ";
		}
		result += "]";
		return result;
	}
}
