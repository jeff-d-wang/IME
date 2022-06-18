package model.transformation;

import model.picture.IPicture;
import model.picture.PictureImpl;
import model.pixel.PixelImpl;

/**
 * Implementation of a ITransformation interface. Represents a collection of transformation
 * commands applied to Pictures.
 */
public class TransformationImpl implements ITransformation {

  @Override
  public IPicture apply(IPicture picture, double[][] matrix) throws IllegalArgumentException {
    if (matrix.length != 3
            || matrix[0].length != 3
            || matrix[1].length != 3
            || matrix[2].length != 3) {
      throw new IllegalArgumentException("Given a non 3x3 matrix to apply to a 3x1 rgb matrix.");
    }

    IPicture alteration = new PictureImpl(picture.getWidth(), picture.getHeight());

    for (int r = 0; r < picture.getHeight(); r++) {
      for (int c = 0; c < picture.getWidth(); c++) {
        int[] rgb = picture.getPixel(r, c).toMatrix();

        alteration.setPixel(r, c, new PixelImpl(
                (int) multiply(rgb, matrix[0]),
                (int) multiply(rgb, matrix[1]),
                (int) multiply(rgb, matrix[2])));
      }
    }

    return alteration;
  }

  /**
   * Multiplies a given 1x3 matrix to a 3x1 matrix representative of rgb values.
   *
   * @param rgb      3x1 of rgb values
   * @param matrix   1x3 matrix to be multiplied to a set of rgb values
   * @return the product of the two given matrix (value for any of rgb)
   * @throws IllegalArgumentException if the given matrix is not 1x3 or 3x1 according to what it
   *         represents
   */
  private double multiply(int[] rgb, double[] matrix) throws IllegalArgumentException {
    if (rgb.length != 3 || matrix.length != 3 || rgb.length != matrix.length) {
      throw new IllegalArgumentException("Given mismatching 3x1 rgb matrix or 1x3 matrix.");
    }

    double value = 0;

    for (int i = 0; i < rgb.length; i++) {
      value += rgb[i] * matrix[i];
    }

    return value;
  }
}
