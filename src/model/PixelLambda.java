package model;

import model.Pixel.IPixel;
import model.Pixel.RGBPixel;

public interface PixelLambda {
  RGBPixel run(IPixel p);
}
