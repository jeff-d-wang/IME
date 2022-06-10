import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageUtil;
import model.picture.PPMPicture;
import model.pixel.RGBPixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for ImageUtil.
 */
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
  public void testReadPPM() {
    // tests if reamPPM correctly assigns pixel color values based on the file given
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

  @Test(expected = IOException.class)
  public void testInvalidReadPPM1() throws IOException {
    ImageUtil.readPPM(null);
  }

  @Test(expected = IOException.class)
  public void testInvalidReadPPM2() throws IOException {
    ImageUtil.readPPM("src/pictures/smallImage/doesNotExist.ppm");
  }

  @Test(expected = IOException.class)
  public void testInvalidReadPPM3() throws IOException {
    ImageUtil.readPPM("src/pictures/smallImage/doesNotExist.txt");
  }

  @Test
  public void testWriteFile() {
    try {
      PPMPicture smallImageRedGreyscale = (PPMPicture) smallImage.greyscale("red");
      ImageUtil.writeFile(smallImageRedGreyscale, "src/pictures/smallImage/" +
              "result/redGreyscale.ppm");

      PPMPicture smallImageRedGreyscaleExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-red-greyscale.ppm");

      for (int r = 0; r < smallImageRedGreyscale.getHeight(); r++) {
        for (int c = 0; c < smallImageRedGreyscale.getWidth(); c++) {
          assertEquals(smallImageRedGreyscale.getPixel(r, c),
                  smallImageRedGreyscaleExpect.getPixel(r, c));
        }
      }

      // I'm testing for the fact that the file still stores data when the file is not a PPM file.
      // It still opens to the picture on my mac computer so that's cool.
      PPMPicture smallImageJPEG = smallImage;
      ImageUtil.writeFile(smallImageJPEG, "src/pictures/smallImage/result/notPPM.jpeg");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = IOException.class)
  public void testInvalidWriteFile() throws IOException {
    PPMPicture smallImageHorizontal = (PPMPicture) smallImage.flip("horizontal");
    ImageUtil.writeFile(smallImageHorizontal, null);
  }

  @Test(expected = IOException.class)
  public void testInvalidP3File() throws IOException {
    ImageUtil.readPPM("src/pictures/smallImage/smallImageP6.pbm");
  }
}
