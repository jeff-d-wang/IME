package model.Picture;

import model.Pixel.IPixel;
import model.Pixel.RGBPixel;
import model.Lambda.RGBPixelLambda;

public abstract class RGBPicture implements IPicture {
  protected RGBPixel[][] pixels;

  RGBPicture altercation;

  public RGBPicture(int width, int height) {
    pixels = new RGBPixel[width][height];
  }

  protected abstract void resetAltercation();

  @Override
  public int getWidth() {
    return pixels.length;
  }

  @Override
  public int getHeight() {
    return pixels[0].length;
  }

  @Override
  public RGBPixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  @Override
  public abstract void setPixel(int x, int y, IPixel pixel) throws IllegalArgumentException;

  /**
   * Applies the given lambda to all pixels of this picture and stores it in its altercation.
   *
   * @param lambda Lambda function to be applied on a pixel
   */
  protected void applyLambda(RGBPixelLambda lambda) {
    resetAltercation();
    for (int r = 0; r < getHeight(); r++) {
      for (int c = 0; c < getWidth(); c++) {
        altercation.setPixel(r, c, lambda.run(this.getPixel(r, c)));
      }
    }
  }

  /**
   * Greyscale this picture according to a given component specifying its type.
   *
   * @param component Type of greyscale to be applied
   * @return a greyscale picture
   */
  /*
  This was made abstract to assign the altercation to the correct IPixel type. If we were to use a
  different implementation of the IPixel then when another RGBPicture implementation uses (like an
  alpha pixel), we would need to make sure the lambdas use the correct IPixel.
   */
  public abstract RGBPicture greyscale(String component);

  /**
   * Flips this image in a given String direction.
   *
   * @param direction Direction the picture is to be flipped in
   * @return a flipped picture
   */
  public RGBPicture flip(String direction) {
    resetAltercation();
    if (direction.equals("horizontal")) {
      for (int r = 0; r < this.getHeight(); r++) {
        for (int c = 0; c < this.getWidth(); c++) {
          altercation.setPixel(Math.abs((r + 1) - getHeight()), c, pixels[r][c]);
        }
      }
    } else if (direction.equals("vertical")) {
      for (int r = 0; r < this.getHeight(); r++) {
        for (int c = 0; c < this.getWidth(); c++) {
          altercation.setPixel(r, Math.abs((c + 1) - getWidth()), pixels[r][c]);
        }
      }
    } else {
      throw new IllegalStateException("Invalid flip transformation");
    }

    return altercation;
  }

  /**
   * Brighten the picture by a given increment.
   *
   * @param increment Number increment to change brightness by
   * @return brighten image
   */
  public abstract RGBPicture brighten(int increment);
}
