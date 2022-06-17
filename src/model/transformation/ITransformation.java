package model.transformation;

import model.picture.IPicture;

/**
 * Interface that represents a transformation for a picture. It functions by multiplying a matrix to
 * a 1x3 matrix of rgb values for a pixel, achieving an ultimate value for the pixel, then assigns
 * it its final RGB values.
 */
public interface ITransformation {

  /**
   * Applies the given filter onto a given picture.
   * @param picture   Picture to be passed through the matrix
   * @param matrix    Matrix to be applied onto the picture
   * @return the transformed picture
   * @throws IllegalArgumentException if given a non 3x3 matrix to apply to a 3x1 rgb matrix
   */
  IPicture apply(IPicture picture, double[][] matrix) throws IllegalArgumentException;
}
