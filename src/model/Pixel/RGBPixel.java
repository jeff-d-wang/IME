package model.Pixel;

import java.util.Objects;

/**
 * Abstract class that represents a pixel with RGB values.
 */
/*
This was made to branch similar code between a pixel with RGB values, one that also has an
alpha component, and others that may feature other defining details.
 */
public abstract class RGBPixel implements IPixel {
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
  public RGBPixel(int r, int g, int b) {
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

  /**
   * Returns the red value of this pixel.
   *
   * @return the red value of this pixel
   */
  public int getR() {
    return r;
  }

  /**
   * Sets the red value of this pixel.
   */
  public void setR(int r) {
    this.setRGB(r, this.g, this.b);
  }

  /**
   * Returns the green value of this pixel.
   *
   * @return the green value of this pixel
   */
  public int getG() {
    return g;
  }

  /**
   * Sets the green value of this pixel.
   */
  public void setG(int g) {
    this.setRGB(this.r, g, this.b);
  }

  /**
   * Returns the blue value of this pixel.
   *
   * @return the blue value of this pixel
   */
  public int getB() {
    return b;
  }

  /**
   * Sets the blue value of this pixel.
   */
  public void setB(int b) {
    this.setRGB(this.r, this.g, b);
  }

  /**
   * Sets given RGB values to their respective variables. It will clamp inputs to its min and max
   * values.
   *
   * @param r Red value [0-255]
   * @param g Green value [0-255]
   * @param b Blue value [0-255]
   */
  public void setRGB(int r, int g, int b) throws IllegalArgumentException {
    this.r = clamp(r);
    this.g = clamp(g);
    this.b = clamp(b);
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
  public String toString() {
    return "(" + getR() + ", " + getG() + ", " + getB() + ")";
  }
}
