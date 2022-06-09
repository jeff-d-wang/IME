package model;

import java.io.PrintWriter;

import model.Pixel.IPixel;
import model.Pixel.RGBPixel;

/**
 * Represents a picture with its pixels.
 */
public class Picture extends APicture {

  public Picture(int width, int height) {
    super.pixels = new RGBPixel[width][height];
  }

  @Override
  public void setPixel(int x, int y, IPixel p) throws IllegalArgumentException {
    pixels[x][y] = new RGBPixel(p.getR(), p.getG(), p.getB());
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
