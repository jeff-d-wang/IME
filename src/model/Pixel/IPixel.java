package model.Pixel;

/**
 * This is the interface for IPixel. It contains methods that can extract and mutate the
 * R, G, B values of a Pixel, and get the max, intensity and luma of a Pixel.
 */
public interface IPixel {
  /**
   * Returns the red value of this pixel.
   *
   * @return the red value of this pixel
   */
  public int getR();

  /**
   * Sets the red value of this pixel.
   */
  public void setR(int r);

  /**
   * Returns the green value of this pixel.
   *
   * @return the green value of this pixel
   */
  public int getG();

  /**
   * Sets the green value of this pixel.
   */
  public void setG(int g);

  /**
   * Returns the blue value of this pixel.
   *
   * @return the blue value of this pixel
   */
  public int getB();

  /**
   * Sets the blue value of this pixel.
   */
  public void setB(int b);

  /**
   * Sets given RGB values to their respective variables.
   *
   * @param r Red value (0-255)
   * @param g Green value (0-255)
   * @param b Blue value (0-255)
   * @throws IllegalArgumentException if any of the values aren't between 0 and 255 (inclusive).
   */
  public void setRGB(int r, int g, int b) throws IllegalArgumentException;

  /**
   * Return the maximum value of the three components of this pixel.
   *
   * @return the maximum value of the three components of this pixel
   */
  public int getValue();

  /**
   * Return the average of the three components of this pixel.
   *
   * @return the average of the three components of this pixel
   */
  public int getIntensity();

  /**
   * Return the luma value of this pixel.
   *
   * @return the luma value of this pixel
   */
  public int getLuma();
}
