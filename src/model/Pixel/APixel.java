package model.Pixel;

import java.util.Objects;

/**
 * This is an abstract class for a pixel. It contains all the methods needed for every kind of
 * pixel.
 */
public class APixel implements IPixel {
  protected int r;
  protected int g;
  protected int b;
  protected static int max;
  protected static int min;
  /**
   * Constructs an abstract pixel with red, green, blue values.
   *
   * @param r the red value of the pixel.
   * @param g the green value of the pixel.
   * @param b the blue value of the pixel.
   */
  public APixel(int r, int g, int b) {
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
  public void setR(int r) {
    this.setRGB(r, this.g, this.b);
  }

  @Override
  public int getG() {
    return g;
  }

  @Override
  public void setG(int g) {
    this.setRGB(this.r, g, this.b);
  }

  @Override
  public int getB() {
    return b;
  }

  @Override
  public void setB(int b) {
    this.setRGB(this.r, this.g, b);
  }

  @Override
  public void setRGB(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Illegal rgb value(s) " + r + ", " + g + ", " + b);
    }

    this.r = r;
    this.g = g;
    this.b = b;
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
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof IPixel)) {
      return false;
    } else {
      APixel that = (APixel) (other);
      return (this.r == that.r) && (this.g == that.g) && (this.b == that.b);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(r, g, b);
  }
}
