package model.filter;

import model.picture.IPicture;
import model.picture.PictureImpl;
import model.pixel.IPixel;
import model.pixel.PixelImpl;

/**
 * Implementation of a IFilter interface. Represents a collection of filter commands applied to
 * Pictures.
 */
public class FilterImpl implements IFilter {

  @Override
  public IPicture apply(IPicture picture, double[][] filter) throws IllegalArgumentException {
    if (filter.length % 2 == 0) {
      throw new IllegalArgumentException("Given filter with odd dimensions.");
    }

    for (int i = 0; i < filter.length; i++) {
      if (filter.length != filter[i].length) {
        throw new IllegalArgumentException("Given filter with unequal/uneven dimensions.");
      }
    }

    IPicture alteration = new PictureImpl(picture.getWidth(), picture.getHeight());

    int filterArm = (int) Math.floor(filter.length / 2.0);

    for (int r = 0; r < picture.getHeight(); r++) {
      for (int c = 0; c < picture.getWidth(); c++) {
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        for (int i = 0; i < filter.length; i++) {
          for (int j = 0; j < filter[i].length; j++) {
            int kernelR = r - (filterArm - i);
            int kernelC = c - (filterArm - j);
            try {
              IPixel pixel = picture.getPixel(kernelR, kernelC);
              sumR += (int) (pixel.getR() * filter[i][j]);
              sumG += (int) (pixel.getG() * filter[i][j]);
              sumB += (int) (pixel.getB() * filter[i][j]);
            } catch (IllegalArgumentException e) {
              // Continue because an error shouldn't stop us from applying to other pixels
              continue;
            }
          }
        }
        alteration.setPixel(r, c, new PixelImpl(sumR, sumG, sumB));
      }
    }

    return alteration;
  }
}
