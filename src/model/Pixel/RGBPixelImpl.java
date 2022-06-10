package model.Pixel;

import java.util.Objects;

/**
 * Represents a pixel with its RGB values.
 */
public class RGBPixelImpl extends RGBPixel {

  public RGBPixelImpl(int r, int g, int b) {
    super(r, g, b);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof IPixel)) {
      return false;
    } else {
      RGBPixel that = (RGBPixel) (other);
      return (getR() == that.getR()) && (getG() == that.getG()) && (getB() == that.getB());
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(getR(), getG(), getB());
  }
}
