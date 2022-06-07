/**
 * Represents a picture with its pixels.
 */
public class Picture {
  private Pixel[][] pixels;
  private Picture altercation;

  public Picture(int width, int height) {
    pixels = new Pixel[width][height];
    altercation = new Picture(width, height);
  }

  public int getWidth() {
    return pixels.length;
  }

  public int getHeight() {
    return pixels[0].length;
  }

  public Pixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  public void setPixel(int x, int y, Pixel p) throws IllegalArgumentException {
    pixels[x][y].setRGB(p.getR(), p.getG(), p.getB());
  }

  /**
   * Grayscale this picture according to a given component specifying its type.
   * @param component   Type of grayscale to be applied
   * @return a grayscale picture
   */
  public Picture grayscale(String component) {
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
    }

    applyLambda(componentLambda);

    return altercation;
  }

  /**
   * Applies the given lambda to all pixels of this picture and stores it in its altercation.
   * @param lambda   Lambda function to be applied on a pixel
   */
  private void applyLambda(PixelLambda lambda){
    for (int r = 0; r < pixels.length; r++) {
      for (int c = 0; c < pixels[r].length; c++) {
        altercation.setPixel(r, c, lambda.run(this.getPixel(r, c)));
      }
    }
  }
}
