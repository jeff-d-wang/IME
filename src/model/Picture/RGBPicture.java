package model.Picture;

import java.util.Objects;

import model.Pixel.IPixel;
import model.Pixel.RGBPixel;
import model.Lambda.RGBPixelLambda;

public abstract class RGBPicture implements IPicture {
  protected RGBPixel[][] pixels;

  protected RGBPicture altercation;

  public RGBPicture(int width, int height) {
    pixels = new RGBPixel[height][width];
  }

  protected abstract void resetAltercation();

  @Override
  public int getHeight() {
    return pixels.length;
  }

  @Override
  public int getWidth() {
    return pixels[0].length;
  }

  @Override
  public RGBPixel getPixel(int r, int c) throws IllegalArgumentException {
    if (r < 0 || r >= getHeight() || c < 0 || c >= getWidth()) {
      throw new IllegalArgumentException("Illegal (" + r + ", " + c + ") position.");
    }
    return pixels[r][c];
  }

  @Override
  public abstract void setPixel(int r, int c, IPixel pixel) throws IllegalArgumentException;

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
   * Flips this image in a given String direction.
   *
   * @param direction Direction the picture is to be flipped in
   * @return a flipped picture
   * @throws IllegalArgumentException if the given direction is invalid
   */
  @Override
  public RGBPicture flip(String direction) throws IllegalArgumentException {
    resetAltercation();
    if (direction.equals("horizontal")) {
      for (int c = 0; c < getWidth(); c++) {
        for (int r = 0; r < getHeight(); r++) {
          altercation.setPixel(Math.abs((r + 1) - getHeight()), c, pixels[r][c]);
        }
      }
    } else if (direction.equals("vertical")) {
      for (int r = 0; r < getHeight(); r++) {
        for (int c = 0; c < getWidth(); c++) {
          altercation.setPixel(r, Math.abs((c + 1) - getWidth()), pixels[r][c]);
        } 
      }
    } else {
      throw new IllegalArgumentException("Invalid flip transformation");
    }

    return altercation;
  }
}
