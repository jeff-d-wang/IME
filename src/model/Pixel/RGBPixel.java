package model.Pixel;

/**
 * Represents a pixel with its RGB values.
 */
public class RGBPixel extends APixel {


  // altercation[x][y] = new pixel(new values)
  public RGBPixel(int r, int g, int b) {
    super(r, g, b);
    super.max = 255;
    super.min = 0;
  }

}