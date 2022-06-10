import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageUtil;
import model.Picture.PPMPicture;
import model.Pixel.RGBPixelImpl;

import static org.junit.Assert.assertEquals;

public class ImageUtilTest {

  PPMPicture smallImage;

  @Before
  public void setUp() {
    try {
      smallImage = ImageUtil.readPPM("src/pictures/smallImage/smallImage.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGetPixel() {
    assertEquals(smallImage.getPixel(0, 0), new RGBPixelImpl(255, 0, 0));
    assertEquals(smallImage.getPixel(0, 1), new RGBPixelImpl(0, 0, 0));
    assertEquals(smallImage.getPixel(0, 2), new RGBPixelImpl(0, 0, 255));
    assertEquals(smallImage.getPixel(1, 0), new RGBPixelImpl(0, 255, 0));
    assertEquals(smallImage.getPixel(1, 1), new RGBPixelImpl(255, 0, 0));
    assertEquals(smallImage.getPixel(1, 2), new RGBPixelImpl(0, 0, 255));
    assertEquals(smallImage.getPixel(2, 0), new RGBPixelImpl(27, 177, 241));
    assertEquals(smallImage.getPixel(2, 1), new RGBPixelImpl(164, 18, 228));
    assertEquals(smallImage.getPixel(2, 2), new RGBPixelImpl(164, 18, 228));
    assertEquals(smallImage.getPixel(3, 0), new RGBPixelImpl(0, 255, 0));
    assertEquals(smallImage.getPixel(3, 1), new RGBPixelImpl(0, 0, 0));
    assertEquals(smallImage.getPixel(3, 2), new RGBPixelImpl(242, 113, 65));
    assertEquals(smallImage.getPixel(4, 0), new RGBPixelImpl(255, 0, 0));
    assertEquals(smallImage.getPixel(4, 1), new RGBPixelImpl(18, 52, 86));
    assertEquals(smallImage.getPixel(4, 2), new RGBPixelImpl(255, 255, 255));
  }
}
