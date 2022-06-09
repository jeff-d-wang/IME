package model;

import java.io.PrintWriter;

import model.Pixel.RGBPixel;

/**
 * Represents a picture with its pixels.
 */
public class Picture {
  private RGBPixel[][] pixels;
  private static int maxValue;

  public Picture(int width, int height) {
    pixels = new RGBPixel[width][height];
  }

  public int getWidth() {
    return pixels.length;
  }

  public int getHeight() {
    return pixels[0].length;
  }

  public RGBPixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  public void setPixel(int x, int y, RGBPixel p) throws IllegalArgumentException {
    pixels[x][y] = new RGBPixel(p.getR(), p.getG(), p.getB());
  }

  public void setMaxValue(int maxValue) {
    this.maxValue = maxValue;
  }

  /**
   * Greyscale this picture according to a given component specifying its type.
   *
   * @param component Type of greyscale to be applied
   * @return a greyscale picture
   */
  public Picture greyscale(String component) {
    PixelLambda componentLambda = null;
    Picture altercation = new Picture(getWidth(), getHeight());

    switch (component) {
      case "red":
        componentLambda = (p) -> new RGBPixel(p.getR(), p.getR(), p.getR());
        break;
      case "green":
        componentLambda = (p) -> new RGBPixel(p.getG(), p.getG(), p.getG());
        break;
      case "blue":
        componentLambda = (p) -> new RGBPixel(p.getB(), p.getB(), p.getB());
        break;
      case "value":
        componentLambda = (p) -> new RGBPixel(p.getValue(), p.getValue(), p.getValue());
        break;
      case "intensity":
        componentLambda = (p) -> new RGBPixel(p.getIntensity(), p.getIntensity(), p.getIntensity());
        break;
      case "luma":
        componentLambda = (p) -> new RGBPixel(p.getLuma(), p.getLuma(), p.getLuma());
        break;
      default:
        throw new IllegalArgumentException("Invalid greyscale component.");
    }

    applyLambda(componentLambda);

    return altercation;
  }

  /**
   * Applies the given lambda to all pixels of this picture and stores it in its altercation.
   *
   * @param lambda Lambda function to be applied on a pixel
   */
  private void applyLambda(PixelLambda lambda) {
    Picture altercation = new Picture(getWidth(), getHeight());
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
   */
  public Picture flip(String direction) {
    Picture altercation = new Picture(getWidth(), getHeight());
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
   * @param n Number increment to change brightness by
   * @return brighten image
   */
  public Picture brighten(int n) {
    Picture altercation = new Picture(getWidth(), getHeight());
    PixelLambda componentLambda
            = (p) -> new RGBPixel(p.getR() + n, p.getG() + n, p.getB() + n);
    applyLambda(componentLambda);
    return altercation;
  }

  /**
   * Converts this picture into a PPM file.
   *
   * @param filePath Path of the ppm file to be written on.
   */
  public void toPPM(String filePath) {
    try {
      PrintWriter os = new PrintWriter(filePath);

      // header
      os.println("P3");
      os.println(this.getHeight() + " " + this.getWidth());
      os.println(maxValue);

      for (int r = 0; r < getHeight(); r++) {
        for (int c = 0; c < getWidth(); c++) {
          os.println(getPixel(r, c).getR() + " "
                  + getPixel(r, c).getG() + " "
                  + getPixel(r, c).getB());
        }
      }

      os.close();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}
