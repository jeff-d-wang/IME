package model.lambda;

import model.pixel.RGBPixel;

/**
 * Interface for lambdas mutating RGBPixels.
 */
public interface RGBPixelLambda extends IPixelLambda {
  RGBPixel run(RGBPixel p);
}
