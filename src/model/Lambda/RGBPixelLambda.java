package model.Lambda;

import model.Lambda.IPixelLambda;
import model.Pixel.RGBPixel;

/**
 * Interface for lambdas mutating RGBPixels.
 */
public interface RGBPixelLambda extends IPixelLambda {
  RGBPixel run(RGBPixel p);
}
