package model.pixel;

/**
 * Interface of a pixel which encapsulates all simulates between pixels (RGB, RGBA, HVI, Hex, etc.).
 */
public interface IPixel {

  /**
   * Returns the red value of this pixel.
   *
   * @return the red value of this pixel
   */
  int getR();

  /**
   * Returns the green value of this pixel.
   *
   * @return the green value of this pixel
   */
  int getG();

  /**
   * Returns the blue value of this pixel.
   *
   * @return the blue value of this pixel
   */
  int getB();

  /**
   * Return the maximum value of the three components of this pixel.
   *
   * @return the maximum value of the three components of this pixel
   */
  int getValue();

  /**
   * Return the average of the three components of this pixel.
   *
   * @return the average of the three components of this pixel
   */
  int getIntensity();

  /**
   * Return the luma value of this pixel.
   *
   * @return the luma value of this pixel
   */
  int getLuma();

  /**
   * Return the 3x1 matrix equivalent to this pixel's rgb values.
   *
   * @return the 3x1 matrix equivalent to this pixel's rgb values
   */
  int[] toMatrix();
}
