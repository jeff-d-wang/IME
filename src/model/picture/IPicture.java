package model.picture;

import model.pixel.IPixel;

/**
 * Interface representative of a singular Picture.
 */
public interface IPicture {

  /**
   * Returns the width of this picture.
   * @return the width of this picture
   */
  int getWidth();

  /**
   * Returns the height of this picture.
   * @return the height of this picture
   */
  int getHeight();

  /**
   * Return the Pixel object at a given row and column value.
   *
   * @param r   Row value
   * @param c   Column value
   * @return the Pixel object at a given row and column value
   * @throws IllegalArgumentException if the given row and/or column value is less than 0 or greater
   *                                  than the height or width of the picture, respectively
   */
  IPixel getPixel(int r, int c) throws IllegalArgumentException;

  /**
   * Set the Pixel object at a given row and column value a given Pixel object.
   * @param r       Row value
   * @param c       Column value
   * @param pixel   Pixel object to be set
   * @throws IllegalArgumentException if the given row and/or column value is less than 0 or greater
   *      than the height or width of the picture, respectively
   */
  void setPixel(int r, int c, IPixel pixel) throws IllegalArgumentException;

  /**
   * Greyscale this picture according to a given component specifying its type.
   *
   * @param component   Type of greyscale to be applied
   * @return a greyscale component picture
   * @throws IllegalArgumentException if given a null or invalid component
   */
  IPicture component(String component) throws IllegalArgumentException;

  /**
   * Flips this image in a given String direction. The only directions supported thus far are
   * horizontal and vertical. Strings other than it will not produce a picture.
   *
   * @param direction Direction the picture is to be flipped in
   * @return a flipped picture
   * @throws IllegalArgumentException if given a null or invalid direction
   */
  IPicture flip(String direction) throws IllegalArgumentException;

  /**
   * Brighten the picture by a given increment.
   *
   * @param increment Number increment to change brightness by
   * @return brighten image
   */
  IPicture brighten(int increment);

  /**
   * Blur an image.
   *
   * @return blurred image
   */
  IPicture blur();

  /**
   * Sharpen an image.
   *
   * @return sharpened image
   */
  IPicture sharpen();

  /**
   * Apply a greyscale transformation on an image.
   *
   * @return greyscale image
   */
  IPicture greyscale();

  /**
   * Apply a sepia transformation on an image.
   *
   * @return sepia image
   */
  IPicture sepia();

  /**
   * Downscale an image by either width or height.
   *
   * @param x the number to scale the image width down by.
   * @param y the number to scale the image height down by.
   * @return downscaled image
   */
  IPicture downscale(double x, double y) throws IllegalArgumentException;

  /**
   * Partially manipulate an image based on a mask image.
   * @parm pic the mask image that tells which color blocks to change.
   * @param command the string command that tells how to change the color blocks.
   * @return partially manipulated image.
   */
  IPicture partialImageManipulation(IPicture pic, String command);

}
