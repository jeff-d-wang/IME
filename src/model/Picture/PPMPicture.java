package model.Picture;

import java.io.PrintWriter;

import model.Pixel.IPixel;
import model.Pixel.RGBPixelImpl;
import model.Lambda.RGBPixelLambda;

/**
 * Represents a picture with its pixels.
 */
public class PPMPicture extends RGBPicture {
  private final String token;
  private static final int maxValue;

  static {
    maxValue = 255;
  }

  public PPMPicture(String token, int width, int height) {
    super(width, height);
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public int getMaxValue() {
    return maxValue;
  }

  @Override
  protected void resetAltercation() {
    altercation = new PPMPicture(token, getWidth(), getHeight());
  }

  @Override
  public void setPixel(int x, int y, IPixel pixel) throws IllegalArgumentException {
    pixels[x][y] = (RGBPixelImpl) pixel;
  }

  @Override
  public RGBPicture greyscale(String component) {
    RGBPixelLambda componentLambda = null;

    switch (component) {
      case "red":
        componentLambda = (p) -> new RGBPixelImpl(p.getR(), p.getR(), p.getR());
        break;
      case "green":
        componentLambda = (p) -> new RGBPixelImpl(p.getG(), p.getG(), p.getG());
        break;
      case "blue":
        componentLambda = (p) -> new RGBPixelImpl(p.getB(), p.getB(), p.getB());
        break;
      case "value":
        componentLambda = (p) -> new RGBPixelImpl(p.getValue(), p.getValue(), p.getValue());
        break;
      case "intensity":
        componentLambda = (p) -> new RGBPixelImpl(p.getIntensity(), p.getIntensity(), p.getIntensity());
        break;
      case "luma":
        componentLambda = (p) -> new RGBPixelImpl(p.getLuma(), p.getLuma(), p.getLuma());
        break;
      default:
        throw new IllegalArgumentException("Invalid greyscale component.");
    }

    applyLambda(componentLambda);

    return altercation;
  }

  @Override
  public RGBPicture brighten(int increment) {
    resetAltercation();
    RGBPixelLambda componentLambda
            = (p) -> new RGBPixelImpl(
                    p.getR() + increment, p.getG() + increment, p.getB() + increment);
    applyLambda(componentLambda);
    return altercation;
  }

  @Override
  public void toFile(String filename) {
    try {
      PrintWriter os = new PrintWriter(filename);

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
      e.printStackTrace();
    }
  }
}
