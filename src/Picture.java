import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Represents a picture with its pixels.
 */
public class Picture {
  private Pixel[][] pixels;
  private Picture altercation;
  private static final int maxValue = 255;

  public Picture(int width, int height) {
    pixels = new Pixel[width][height];
    altercation = new Picture(width, height);
  }

  public int getWidth() {
    return pixels[0].length;
  }

  public int getHeight() {
    return pixels.length;
  }

  public Pixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  public void setPixel(int x, int y, Pixel p) throws IllegalArgumentException {
    pixels[x][y].setRGB(p.getR(), p.getG(), p.getB());
  }

  /**
   * Greyscale this picture according to a given component specifying its type.
   * @param component   Type of greyscale to be applied
   * @return a greyscale picture
   */
  public Picture greyscale(String component) {
    PixelLambda componentLambda = null;

    switch (component) {
      case "red":
        componentLambda = (p) -> new Pixel(p.getR(), p.getR(), p.getR());
        break;
      case "green":
        componentLambda = (p) -> new Pixel(p.getG(), p.getG(), p.getG());
        break;
      case "blue":
        componentLambda = (p) -> new Pixel(p.getB(), p.getB(), p.getB());
        break;
      case "value":
        componentLambda = (p) -> new Pixel(p.getValue(), p.getValue(), p.getValue());
        break;
      case "intensity":
        componentLambda = (p) -> new Pixel(p.getIntensity(), p.getIntensity(), p.getIntensity());
        break;
      case "luma":
        componentLambda = (p) -> new Pixel(p.getLuma(), p.getLuma(), p.getLuma());
        break;
      default:
        throw new IllegalArgumentException("Invalid greyscale component.");
    }

    applyLambda(componentLambda);

    return altercation;
  }

  /**
   * Applies the given lambda to all pixels of this picture and stores it in its altercation.
   * @param lambda   Lambda function to be applied on a pixel
   */
  private void applyLambda(PixelLambda lambda){
    for (int r = 0; r < getHeight(); r++) {
      for (int c = 0; c < getWidth(); c++) {
        altercation.setPixel(r, c, lambda.run(this.getPixel(r, c)));
      }
    }
  }

  /**
   * Flips this image in a given String direction.
   * @param direction   Direction the picture is to be flipped in
   * @return a flipped picture
   */
  public Picture flip(String direction) {
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

  /**
   * Brighten the picture by a given increment.
   * @param n   Number increment to change brightness by
   * @return brighten image
   */
  public Picture brighten(int n) {
    PixelLambda componentLambda
            = (p) -> new Pixel(p.getR() + n, p.getG() + n, p.getB() + n);
    applyLambda(componentLambda);
    return altercation;
  }

  /**
   * Converts this picture into a PPM file.
   * @param filePath   Path of the ppm file to be written on.
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
