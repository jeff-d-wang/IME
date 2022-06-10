package model.picture;

import java.io.IOException;

import model.pixel.IPixel;

/**
 * Interface representative of a singular Picture.
 */
public interface IPicture {

  /**
   * Returns the width of this picture.
   * @return the width of this picture
   */
  public int getWidth();

  /**
   * Returns the height of this picture.
   * @return the height of this picture
   */
  public int getHeight();

  /**
   * Return the Pixel object at a given row and column value.
   * @param r   Row value
   * @param c   Column value
   * @return the Pixel object at a given row and column value
   */
  public IPixel getPixel(int r, int c);

  /**
   * Set the Pixel object at a given row and column value a given Pixel object.
   * @param r       Row value
   * @param c       Column value
   * @param pixel   Pixel object to be set
   */
  public void setPixel(int r, int c, IPixel pixel);

  /**
   * Greyscale this picture according to a given component specifying its type.
   *
   * @param component   Type of greyscale to be applied
   * @return a greyscale picture
   * @throws IllegalArgumentException if given a null or invalid component
   */
  public IPicture greyscale(String component) throws IllegalArgumentException;

  /**
   * Flips this image in a given String direction.
   *
   * @param direction Direction the picture is to be flipped in
   * @return a flipped picture
   * @throws IllegalArgumentException if given a null or invalid direction
   */
  public IPicture flip(String direction) throws IllegalArgumentException;

  /**
   * Brighten the picture by a given increment.
   *
   * @param increment Number increment to change brightness by
   * @return brighten image
   */
  public IPicture brighten(int increment);

  /**
   * Converts this picture into its respective file type.
   *
   * @param filename Path of the file to be written on.
   * @throws IOException if it encounters an error accessing the file
   */
  public void toFile(String filename) throws IOException;
}
