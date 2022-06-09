package model.Picture;

import model.Pixel.IPixel;
import model.Pixel.RGBPixel;

public interface IPicture {

  public IPixel getPixel(int x, int y);

  public void setPixel(int x, int y, IPixel pixel);

  /**
   * Greyscale this picture according to a given component specifying its type.
   *
   * @param component Type of greyscale to be applied
   * @return a greyscale picture
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
   * @param increment Number increment to change brightness by
   * @return brighten image
   */
  public IPicture brighten(int increment);

  /**
   * Converts this picture into its respective file type.
   *
   * @param filename Path of the file to be written on.
   */
  public void toFile(String filename);

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
}
