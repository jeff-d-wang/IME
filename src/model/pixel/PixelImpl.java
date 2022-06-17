package model.pixel;

import java.util.Objects;

/**
 * Represents a pixel with its RGB values.
 */
public class PixelImpl implements IPixel {

  private int r;
  private int g;
  private int b;
  private static final int max;
  private static final int min;

  static {
    max = 255;
    min = 0;
  }

  /**
   * Constructs an abstract pixel with red, green, blue values.
   *
   * @param r the red value of the pixel.
   * @param g the green value of the pixel.
   * @param b the blue value of the pixel.
   */
  public PixelImpl(int r, int g, int b) {
    this.r = clamp(r);
    this.g = clamp(g);
    this.b = clamp(b);
  }

  /**
   * Clamps color values to their respective max and min possible values.
   *
   * @param color Color value to be evaluated
   * @return clamped color value
   */
  private int clamp(int color) {
    if (color > max) {
      return max;
    } else if (color < min) {
      return min;
    } else {
      return color;
    }
  }

  @Override
  public int getR() {
    return r;
  }

  @Override
  public int getG() {
    return g;
  }

  @Override
  public int getB() {
    return b;
  }

  @Override
  public int getValue() {
    return Math.max(Math.max(r, g), b);
  }

  @Override
  public int getIntensity() {
    return (r + g + b) / 3;
  }

  @Override
  public int getLuma() {
    return (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
  }

  @Override
  public int[] toMatrix() {
    return new int[] {r, g, b};
  }

  @Override
  public String toString() {
    return "(" + getR() + ", " + getG() + ", " + getB() + ")";
  }
  
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof IPixel)) {
      return false;
    } else {
      PixelImpl that = (PixelImpl) (other);
      return (getR() == that.getR()) && (getG() == that.getG()) && (getB() == that.getB());
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(getR(), getG(), getB());
  }
}
