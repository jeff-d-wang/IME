package model.pixel;

/**
 * Interface of a pixel which encapsulates all simulates between pixels (RGB, RGBA, HVI, Hex, etc.).
 */
public interface IPixel {

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
