package model;

import model.Pixel.IPixel;
import model.Pixel.RGBPixel;

/**
 * This is the interface for Picture. It contains all the methods such as returning the width,
 * height, and pixel at a specific coordinate. It allows the user to mutate the picture by
 * greyscaling, flipping, or brightening a picture.
 */
public interface IPicture {
  /**
   * Returns the width of the picture.
   *
   * @return the width of the picture.
   */
  public int getWidth();

  /**
   * Returns the height of the picture.
   *
   * @return the height of the picture.
   */
  public int getHeight();

  /**
   * Returns the pixel at a specific x and y.
   *
   * @param x the x coordinate of the pixel.
   * @param y the y coordinate of the pixel.
   * @return the pixel at the specific x and y.
   */
  public IPixel getPixel(int x, int y);

  /**
   * Sets a Pixel at x and y to a new Pixel.
   *
   * @param x the x coordinate of the pixel.
   * @param y the y coordinate of the pixel.
   * @param p the new Pixel.
   * @throws IllegalArgumentException if pixel parameters are invalid.
   */
  public void setPixel(int x, int y, IPixel p) throws IllegalArgumentException;

  /**
   * Sets the max value of the pixel.
   *
   * @param maxValue the max value.
   */
  public void setMaxValue(int maxValue);

  /**
   * Greyscale this picture according to a given component specifying its type.
   *
   * @param component Type of greyscale to be applied.
   * @return a greyscale picture.
   */
  public IPicture greyscale(String component);

  /**
   * Flips this image in a given String direction.
   *
   * @param direction Direction the picture is to be flipped in
   * @return a flipped picture
   */
  public IPicture flip(String direction);

  /**
   * Brighten the picture by a given increment.
   *
   * @param n Number increment to change brightness by
   * @return brighten image
   */
  public IPicture brighten(int n);


}
