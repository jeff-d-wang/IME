/**
 * Represents a pixel with its RGB values.
 */
public class Pixel {
  private int r;
  private int g;
  private int b;

  public Pixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Returns the red value of this pixel.
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
   * Sets given RGB values to their respective variables.
   * @param r   Red value (0-255)
   * @param g   Green value (0-255)
   * @param b   Blue value (0-255)
   * @throws IllegalArgumentException if any of the values aren't between 0 and 255 (inclusive).
   */
  public void setRGB(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Illegal rgb value(s) " + r + ", " + g + ", " + b);
    }

    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Return the maximum value of the three components of this pixel.
   * @return the maximum value of the three components of this pixel
   */
  public int getValue() {
    return Math.max(Math.max(r, g), b);
  }

  /**
   * Return the average of the three components of this pixel.
   * @return the average of the three components of this pixel
   */
  public int getIntensity() {
    return (r + g + b) / 3;
  }

  /**
   * Return the luma value of this pixel.
   * @return the luma value of this pixel
   */
  public int getLuma() {
    return (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
  }
}
