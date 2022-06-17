package model.lambda;

import model.pixel.IPixel;

/**
 * Interface for lambdas mutating RGBPixels.
 */
public interface RGBPixelLambda extends IPixelLambda {
  /**
   *
   * @param p the pixel to be mutated.
   * @return
   */
  IPixel run(IPixel p);
}
