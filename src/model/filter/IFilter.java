package model.filter;

import model.picture.IPicture;

/**
 * Interface that represents a filter for a picture. It functions by applying a filter onto a kernel
 * of the same size, achieving an ultimate value for the pixel at the center of the kernel, then
 * assigns it its final RGB values.
 */
public interface IFilter {

  /**
   * Applies the given filter onto a given picture.
   * @param picture   Picture to be passed through the filter
   * @param filter    Filter to be applied onto the picture
   * @return the filtered picture
   * @throws IllegalArgumentException if the filter isn't odd in dimensions or a square (size wise)
   */
  IPicture apply(IPicture picture, double[][] filter) throws IllegalArgumentException;
}
