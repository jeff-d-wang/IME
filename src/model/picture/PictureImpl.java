package model.picture;

import model.filter.FilterImpl;
import model.IPixelLambda;
import model.pixel.IPixel;
import model.pixel.PixelImpl;
import model.transformation.TransformationImpl;

/**
 * Represents a picture with a double array of IPixels and handles various functions relating to it.
 */
public class PictureImpl implements IPicture {

  private IPixel[][] pixels;
  private PictureImpl alteration;
  private static final int maxValue;

  static {
    maxValue = 255;
  }

  /**
   * Basic constructor for this PictureImpl class.
   *
   * @param width  Width of this picture
   * @param height Height of this picture
   * @throws IllegalArgumentException if the width or height is negative
   */
  public PictureImpl(int width, int height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Invalid " + width + " and/or " + height + " dimensions.");
    }
    pixels = new PixelImpl[height][width];
  }

  @Override
  public int getHeight() {
    return pixels.length;
  }

  @Override
  public int getWidth() {
    return pixels[0].length;
  }

  /**
   * Returns the max value of this picture if need be.
   *
   * @return the max value of this picture
   */
  public int getMaxValue() {
    return maxValue;
  }

  @Override
  public IPixel getPixel(int r, int c) throws IllegalArgumentException {
    if (r < 0 || r >= getHeight() || c < 0 || c >= getWidth()) {
      throw new IllegalArgumentException("Illegal (" + r + ", " + c + ") position.");
    }
    return pixels[r][c];
  }

  @Override
  public void setPixel(int r, int c, IPixel pixel) throws IllegalArgumentException {
    if (r < 0 || r >= getHeight() || c < 0 || c >= getWidth()) {
      throw new IllegalArgumentException("Illegal (" + r + ", " + c + ") position.");
    }
    pixels[r][c] = pixel;
  }

  /**
   * Resets the alteration object for continued use and return.
   */
  private void resetAlteration() {
    alteration = new PictureImpl(getWidth(), getHeight());
  }

  /**
   * Applies the given lambda to all pixels of this picture and stores it in its altercation.
   *
   * @param lambda Lambda function to be applied on a pixel
   */
  private void applyLambda(IPixelLambda lambda) {
    resetAlteration();
    for (int r = 0; r < getHeight(); r++) {
      for (int c = 0; c < getWidth(); c++) {
        alteration.setPixel(r, c, lambda.run(this.getPixel(r, c)));
      }
    }
  }

  @Override
  public IPicture component(String component) throws IllegalArgumentException {
    if (component == null) {
      throw new IllegalArgumentException("Null greyscale component.");
    }

    IPixelLambda componentLambda = null;

    switch (component) {
      case "red":
        componentLambda = (p) -> new PixelImpl(p.getR(), p.getR(), p.getR());
        break;
      case "green":
        componentLambda = (p) -> new PixelImpl(p.getG(), p.getG(), p.getG());
        break;
      case "blue":
        componentLambda = (p) -> new PixelImpl(p.getB(), p.getB(), p.getB());
        break;
      case "value":
        componentLambda = (p) -> new PixelImpl(p.getValue(), p.getValue(), p.getValue());
        break;
      case "intensity":
        componentLambda = (p) -> new PixelImpl(p.getIntensity(), p.getIntensity(),
                p.getIntensity());
        break;
      case "luma":
        componentLambda = (p) -> new PixelImpl(p.getLuma(), p.getLuma(), p.getLuma());
        break;
      default:
        throw new IllegalArgumentException("Invalid greyscale component.");
    }

    applyLambda(componentLambda);

    return alteration;
  }

  @Override
  public IPicture flip(String direction) throws IllegalArgumentException {
    if (direction == null) {
      throw new IllegalArgumentException("Null direction.");
    }

    resetAlteration();

    switch (direction) {
      case "horizontal":
        for (int c = 0; c < getWidth(); c++) {
          for (int r = 0; r < getHeight(); r++) {
            alteration.setPixel(Math.abs((r + 1) - getHeight()), c, pixels[r][c]);
          }
        }
        break;
      case "vertical":
        for (int r = 0; r < getHeight(); r++) {
          for (int c = 0; c < getWidth(); c++) {
            alteration.setPixel(r, Math.abs((c + 1) - getWidth()), pixels[r][c]);
          }
        }
        break;
      default:
        throw new IllegalArgumentException("Invalid flip transformation");
    }

    return alteration;
  }

  @Override
  public IPicture brighten(int increment) {
    resetAlteration();
    IPixelLambda componentLambda
            = (p) -> new PixelImpl(
            p.getR() + increment, p.getG() + increment, p.getB() + increment);
    applyLambda(componentLambda);
    return alteration;
  }

  @Override
  public IPicture blur() {
    double[][] blurFilter = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
    return new FilterImpl().apply(this, blurFilter);
  }

  @Override
  public IPicture sharpen() {
    double[][] sharpenFilter = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
    return new FilterImpl().apply(this, sharpenFilter);
  }

  @Override
  public IPicture greyscale() {
    double[][] greyscaleMatrix = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    return new TransformationImpl().apply(this, greyscaleMatrix);
  }

  @Override
  public IPicture sepia() {
    double[][] sepiaMatrix = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
    return new TransformationImpl().apply(this, sepiaMatrix);
  }

  @Override
  public IPicture downscale(double scale) throws IllegalArgumentException {
    if (scale > 1) {
      throw new IllegalArgumentException("To downscale number must be less than 1");
    }
    resetAlteration();
    System.out.println(alteration);
    System.out.print((int) (getHeight() * scale));
    System.out.print((int) (getWidth() * scale));

    for (int r = 0; r < (int) (getHeight() * scale); r++) {
      for (int c = 0; c < (int) (getWidth() * scale); c++) {

        int rFloor = (int) Math.floor(r * scale);
        int rCeil = (int) Math.ceil(r * scale);
        int cFloor = (int) Math.floor(c * scale);
        int cCeil = (int) Math.ceil(c * scale);

        IPixel A = pixels[rFloor][cFloor];
        IPixel B = pixels[rCeil][cFloor];
        IPixel C = pixels[rFloor][cCeil];
        IPixel D = pixels[rCeil][cCeil];

        int mRed = (B.getR() * (r - rFloor)) + (A.getR() * (rCeil - r));
        int nRed = (D.getR() * (r - rFloor)) + (C.getR() * (rCeil - r));
        int cRed = (nRed * (c - cFloor)) + (mRed * (cCeil - c));

        int mGreen = (B.getG() * (r - rFloor)) + (A.getG() * (rCeil - r));
        int nGreen = (D.getG() * (r - rFloor)) + (C.getG() * (rCeil - r));
        int cGreen = (nGreen * (c - cFloor)) + (mGreen * (cCeil - c));

        int mBlue = (B.getB() * (r - rFloor)) + (A.getB() * (rCeil - r));
        int nBlue = (D.getB() * (r - rFloor)) + (C.getB() * (rCeil - r));
        int cBlue = (nBlue * (c - cFloor)) + (mBlue * (cCeil - c));

        IPixel newPixel = new PixelImpl(cRed, cGreen, cBlue);
        alteration.setPixel(r, c, newPixel);

      }
    }

    return alteration;
  }

  @Override
  public IPicture partialImageManipulation() {
    return null;
  }

}
