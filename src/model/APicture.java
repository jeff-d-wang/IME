package model;

import model.Pixel.IPixel;
import model.Pixel.RGBPixel;

public abstract class APicture implements IPicture {
  protected IPixel[][] pixels;
  protected static int maxValue;

  @Override
  public int getWidth() {
    return pixels.length;
  }

  @Override
  public int getHeight() {
    return pixels[0].length;
  }

  @Override
  public IPixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  public abstract void setPixel(int x, int y, IPixel p) throws IllegalArgumentException;

  @Override
  public void setMaxValue(int maxValue) {
    this.maxValue = maxValue;
  }

  @Override
  public IPicture greyscale(String component) {
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
    IPicture altercation = new Picture(getWidth(), getHeight());
    for (int r = 0; r < getHeight(); r++) {
      for (int c = 0; c < getWidth(); c++) {
        altercation.setPixel(r, c, lambda.run(this.getPixel(r, c)));
      }
    }
  }

  @Override
  public IPicture flip(String direction) {
    IPicture altercation = new Picture(getWidth(), getHeight());
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

  @Override
  public IPicture brighten(int n) {
    IPicture altercation = new Picture(getWidth(), getHeight());
    PixelLambda componentLambda
            = (p) -> new RGBPixel(p.getR() + n, p.getG() + n, p.getB() + n);
    applyLambda(componentLambda);
    return altercation;
  }

}
