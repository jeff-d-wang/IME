package model;

import model.pixel.IPixel;

/**
 * Interface for lambdas mutating IPixels. A lambda function is made from this and is able to
 * reduce code while add ease of code comprehension.
 */
public interface IPixelLambda {

  /**
   * Run function that takes a pixel and returns a pixel applied with a lambda function.
   *
   * @param p the pixel to be mutated.
   * @return a pixel applied with a lambda function
   */
  IPixel run(IPixel p);
}
