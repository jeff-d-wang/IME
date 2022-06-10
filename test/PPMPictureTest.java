import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageUtil;
import model.picture.PPMPicture;
import model.picture.RGBPicture;
import model.pixel.RGBPixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for picture.
 */
public class PPMPictureTest {
  private PPMPicture smallImage;

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

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    PPMPicture pictureFail1 = new PPMPicture("P3", -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    PPMPicture pictureFail2 = new PPMPicture("P3", 1, -1);
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
      PPMPicture smallImageRedGreyscaleExpect = ImageUtil.readPPM("src/pictures/smallImage" +
              "/smallImage-red-greyscale.ppm");
      assertCompare(smallImageRedGreyscale, smallImageRedGreyscaleExpect);
      // save our results
      smallImageRedGreyscale.toFile("src/pictures/smallImage/result/redGreyscale.ppm");

      PPMPicture smallImageGreenGreyscale = (PPMPicture) smallImage.greyscale("green");
      PPMPicture smallImageGreenGreyscaleExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-green-greyscale.ppm");
      assertCompare(smallImageGreenGreyscale, smallImageGreenGreyscaleExpect);
      // save our results
      smallImageGreenGreyscale.toFile("src/pictures/smallImage/result/greenGreyscale.ppm");

      PPMPicture smallImageBlueGreyscale = (PPMPicture) smallImage.greyscale("blue");
      PPMPicture smallImageBlueGreyscaleExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-blue-greyscale.ppm");
      assertCompare(smallImageBlueGreyscale, smallImageBlueGreyscaleExpect);
      // save our results
      smallImageBlueGreyscale.toFile("src/pictures/smallImage/result/blueGreyscale.ppm");

      PPMPicture smallImageValueGreyscale = (PPMPicture) smallImage.greyscale("value");
      PPMPicture smallImageValueGreyscaleExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-value-greyscale.ppm");
      assertCompare(smallImageValueGreyscale, smallImageValueGreyscaleExpect);
      // save our results
      smallImageValueGreyscale.toFile("src/pictures/smallImage/result/valueGreyscale.ppm");

      PPMPicture smallImageIntensityGreyscale = (PPMPicture) smallImage.greyscale("intensity");
      PPMPicture smallImageIntensityGreyscaleExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-intensity-greyscale.ppm");
      assertCompare(smallImageIntensityGreyscale, smallImageIntensityGreyscaleExpect);
      // save our results
      smallImageIntensityGreyscale.toFile("src/pictures/" +
              "smallImage/result/intensityGreyscale.ppm");

      PPMPicture smallImageLumaGreyscale = (PPMPicture) smallImage.greyscale("luma");
      PPMPicture smallImageLumaGreyscaleExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-luma-greyscale.ppm");
      assertCompare(smallImageLumaGreyscale, smallImageLumaGreyscaleExpect);
      // save our results
      smallImageLumaGreyscale.toFile("src/pictures/smallImage/result/lumaGreyscale.ppm");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyscale1() {
    smallImage.greyscale("string");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyscale2() {
    smallImage.greyscale(null);
  }

  @Test
  public void testFlip() {
    try {
      PPMPicture smallImageHorizontal = (PPMPicture) smallImage.flip("horizontal");
      PPMPicture smallImageHorizontalExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-horizontal.ppm");
      assertCompare(smallImageHorizontal, smallImageHorizontalExpect);
      // save our results
      smallImageHorizontal.toFile("src/pictures/smallImage/result/horizontal.ppm");

      // flipping horizontally smallImage twice (should be original image)
      PPMPicture smallImageHorizontalAgain = (PPMPicture) smallImageHorizontal.flip("hori" +
              "zontal");
      assertCompare(smallImageHorizontalAgain, smallImage);

      PPMPicture smallImageVertical = (PPMPicture) smallImage.flip("vertical");
      PPMPicture smallImageVerticalExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-vertical.ppm");
      assertCompare(smallImageVertical, smallImageVerticalExpect);
      // save our results
      smallImageVertical.toFile("src/pictures/smallImage/result/vertical.ppm");

      // flipping vertically smallImage twice (should be original image)
      PPMPicture smallImageVerticalAgain = (PPMPicture) smallImageVertical.flip("vertical");
      assertCompare(smallImageVerticalAgain, smallImage);

      // flipping vertically and then horizontally for smallImage
      PPMPicture smallImageVerticalHorizontal = (PPMPicture) smallImage.flip("vert" +
              "ical").flip("horizontal");
      PPMPicture smallImageVerticalHorizontalExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-vertical-horizontal.ppm");
      assertCompare(smallImageVerticalHorizontal, smallImageVerticalHorizontalExpect);
      // save our results
      smallImageVertical.toFile("src/pictures/smallImage/result/vertical-horizontal.ppm");

      // flipping then horizontal and vertical for smallImage
      PPMPicture smallImageHorizontalVertical = (PPMPicture) smallImage.flip("horiz" +
              "ontal").flip("vertical");
      PPMPicture smallImageHorizontalVerticalExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-horizontal-vertical.ppm");
      assertCompare(smallImageHorizontalVertical, smallImageHorizontalVerticalExpect);
      // save our results
      smallImageVertical.toFile("src/pictures/smallImage/result/horizontal-vertical.ppm");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFlip1() {
    smallImage.greyscale("string");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFlip2() {
    smallImage.greyscale(null);
  }

  @Test
  public void testBrighten() {
    try {
      PPMPicture smallImageBrighten = (PPMPicture) smallImage.brighten(10);
      PPMPicture smallImageBrightenExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-brighten-by-10.ppm");
      assertCompare(smallImageBrighten, smallImageBrightenExpect);
      // save our results
      smallImageBrighten.toFile("src/pictures/smallImage/result/brighten-by-10.ppm");

      PPMPicture smallImageDarken = (PPMPicture) smallImage.brighten(-10);
      PPMPicture smallImageDarkenExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-darken-by-10.ppm");
      assertCompare(smallImageDarken, smallImageDarkenExpect);
      // save our results
      smallImageDarken.toFile("src/pictures/smallImage/result/darken-by-10.ppm");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testToFile() {
    try {
      PPMPicture smallImageRedGreyscale = (PPMPicture) smallImage.greyscale("red");
      smallImageRedGreyscale.toFile("src/pictures/smallImage/result/redGreyscale.ppm");

      PPMPicture smallImageRedGreyscaleExpect = ImageUtil.readPPM("src/pictures/" +
              "smallImage/smallImage-red-greyscale.ppm");
      assertCompare(smallImageRedGreyscale, smallImageRedGreyscaleExpect);

      // I'm testing for the fact that the file still stores data when the file is not a PPM file.
      // It still opens to the picture on my mac computer so that's cool.
      PPMPicture smallImageJPEG = smallImage;
      smallImageJPEG.toFile("src/pictures/smallImage/result/notPPM.jpeg");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = IOException.class)
  public void testInvalidToFile() throws IOException {
    PPMPicture smallImageHorizontal = (PPMPicture) smallImage.flip("horizontal");
    smallImageHorizontal.toFile(null);
  }
}
