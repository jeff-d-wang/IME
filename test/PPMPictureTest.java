import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageUtil;
import model.Picture.PPMPicture;
import model.Picture.RGBPicture;
import model.Pixel.RGBPixel;
import model.Pixel.RGBPixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for picture.
 */
public class PPMPictureTest {
  PPMPicture smallImage;

  @Before
  public void setUp() {
    try {
      smallImage = ImageUtil.readPPM("src/pictures/smallImage/smallImage.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void assertCompare(RGBPicture result, RGBPicture expect) {
    for (int r = 0; r < result.getHeight(); r++) {
      for (int c = 0; c < result.getWidth(); c++) {
        assertEquals(result.getPixel(r, c), expect.getPixel(r, c));
      }
    }
  }

  @Test
  public void testGetWidth() {
    assertEquals(smallImage.getWidth(), 3);
  }

  @Test
  public void testGetHeight() {
    assertEquals(smallImage.getHeight(), 5);
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

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel1() {
    smallImage.getPixel(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel2() {
    smallImage.getPixel(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel3() {
    smallImage.getPixel(-1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel4() {
    smallImage.getPixel(5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel5() {
    smallImage.getPixel(0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel6() {
    smallImage.getPixel(5, 3);
  }

  @Test
  public void testSetPixel() {
    smallImage.setPixel(0, 0, new RGBPixelImpl(0, 0, 0));
    smallImage.setPixel(1, 1, new RGBPixelImpl(255, 255, 255));
    smallImage.setPixel(2, 2, new RGBPixelImpl(-1, 128, 256));
    smallImage.setPixel(3, 1, new RGBPixelImpl(255, 255, 255));
    smallImage.setPixel(4, 2, new RGBPixelImpl(0, 0, 0));

    assertEquals(smallImage.getPixel(0, 0), new RGBPixelImpl(0, 0, 0));
    assertEquals(smallImage.getPixel(1, 1), new RGBPixelImpl(255, 255, 255));
    assertEquals(smallImage.getPixel(2, 2), new RGBPixelImpl(0, 128, 255));
    assertEquals(smallImage.getPixel(3, 1), new RGBPixelImpl(255, 255, 255));
    assertEquals(smallImage.getPixel(4, 2), new RGBPixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel1() {
    smallImage.setPixel(-1, 0, new RGBPixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel2() {
    smallImage.setPixel(-1, 0, new RGBPixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel3() {
    smallImage.setPixel(0, -1, new RGBPixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel4() {
    smallImage.setPixel(-1, -1, new RGBPixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel5() {
    smallImage.setPixel(5, 0, new RGBPixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel6() {
    smallImage.setPixel(0, 3, new RGBPixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel7() {
    smallImage.setPixel(5, 3, new RGBPixelImpl(0, 0, 0));
  }

  @Test
  public void testGreyscale() {
    try {
      PPMPicture smallImageRedGreyscale = (PPMPicture) smallImage.greyscale("red");
      PPMPicture smallImageRedGreyscaleExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-red-greyscale.ppm");
      assertCompare(smallImageRedGreyscale, smallImageRedGreyscaleExpect);

      PPMPicture smallImageGreenGreyscale = (PPMPicture) smallImage.greyscale("green");
      PPMPicture smallImageGreenGreyscaleExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-green-greyscale.ppm");
      assertCompare(smallImageGreenGreyscale, smallImageGreenGreyscaleExpect);

      PPMPicture smallImageBlueGreyscale = (PPMPicture) smallImage.greyscale("blue");
      PPMPicture smallImageBlueGreyscaleExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-blue-greyscale.ppm");
      assertCompare(smallImageBlueGreyscale, smallImageBlueGreyscaleExpect);

      PPMPicture smallImageValueGreyscale = (PPMPicture) smallImage.greyscale("value");
      PPMPicture smallImageValueGreyscaleExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-value-greyscale.ppm");
      assertCompare(smallImageValueGreyscale, smallImageValueGreyscaleExpect);

      PPMPicture smallImageIntensityGreyscale = (PPMPicture) smallImage.greyscale("intensity");
      PPMPicture smallImageIntensityGreyscaleExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-intensity-greyscale.ppm");
      assertCompare(smallImageIntensityGreyscale, smallImageIntensityGreyscaleExpect);

      PPMPicture smallImageLumaGreyscale = (PPMPicture) smallImage.greyscale("luma");
      PPMPicture smallImageLumaGreyscaleExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-luma-greyscale.ppm");
      assertCompare(smallImageLumaGreyscale, smallImageLumaGreyscaleExpect);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testFlip() {
    try {
      PPMPicture smallImageHorizontal = (PPMPicture) smallImage.flip("horizontal");
      PPMPicture smallImageHorizontalExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-horizontal.ppm");
      assertCompare(smallImageHorizontal, smallImageHorizontalExpect);

      // flipping horizontally smallImage twice (should be original image)
      PPMPicture smallImageHorizontalAgain = (PPMPicture) smallImageHorizontal.flip("horizontal");
      assertCompare(smallImageHorizontalAgain, smallImage);

      PPMPicture smallImageVertical = (PPMPicture) smallImage.flip("vertical");
      PPMPicture smallImageVerticalExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-vertical.ppm");
      assertCompare(smallImageVertical, smallImageVerticalExpect);

      // flipping vertically smallImage twice (should be original image)
      PPMPicture smallImageVerticalAgain = (PPMPicture) smallImageVertical.flip("vertical");
      assertCompare(smallImageVerticalAgain, smallImage);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testBrighten() {
    try {
      PPMPicture smallImageBrighten = (PPMPicture) smallImage.brighten(10);
      PPMPicture smallImageBrightenExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-brighten-by-10.ppm");
      assertCompare(smallImageBrighten, smallImageBrightenExpect);

      PPMPicture smallImageDarken = (PPMPicture) smallImage.brighten(-10);
      PPMPicture smallImageDarkenExpect = ImageUtil.readPPM("src/pictures/smallImage/smallImage-darken-by-10.ppm");
      assertCompare(smallImageDarken, smallImageDarkenExpect);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
