package model.Picture;

import java.io.IOException;
import java.io.PrintWriter;

import model.Pixel.IPixel;
import model.Pixel.RGBPixel;
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
  public void setPixel(int r, int c, IPixel pixel) throws IllegalArgumentException {
    if (r < 0 || r >= getWidth() || c < 0 || c >= getHeight()) {
      throw new IllegalArgumentException("Illegal (" + r + ", " + c + ") position.");
    }
    pixels[r][c] = (RGBPixelImpl) pixel;
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
  public void toFile(String filename) throws IOException {
    PrintWriter os = new PrintWriter(filename);

    // header
    os.println("P3");
    os.println(this.getHeight() + " " + this.getWidth());
    os.println(maxValue);

    for (int i = 0; i < getWidth(); i++) {
      for (int j = 0; j < getHeight(); j++) {
        RGBPixel pixel = getPixel(i, j);
        int r = pixel.getR();
        int g = pixel.getG();
        int b = pixel.getB();
        os.println(r + " " + g + " " + b);
      }
    }

    os.close();
  }
}
