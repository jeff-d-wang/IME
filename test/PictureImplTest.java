import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageUtil;
import model.picture.IPicture;
import model.picture.PictureImpl;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for picture.
 */
public class PictureImplTest {
  private IPicture smallImagePPM;
  private IPicture manhattan;

  @Before
  public void setUp() {
    try {
      smallImagePPM = ImageUtil.readFile("src/pictures/smallImage/smallImage.ppm");
      manhattan = ImageUtil.readFile("src/pictures/manhattan.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void assertCompare(IPicture result, IPicture expect) {
    for (int r = 0; r < result.getHeight(); r++) {
      for (int c = 0; c < result.getWidth(); c++) {
        assertEquals(result.getPixel(r, c), expect.getPixel(r, c));
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    PictureImpl pictureFail1 = new PictureImpl(-1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    PictureImpl pictureFail2 = new PictureImpl(1, -1);
  }

  @Test
  public void testGetWidth() {
    assertEquals(smallImagePPM.getWidth(), 3);
  }

  @Test
  public void testGetHeight() {
    assertEquals(smallImagePPM.getHeight(), 5);
  }

  @Test
  public void testGetPixel() {
    assertEquals(smallImagePPM.getPixel(0, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePPM.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePPM.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePPM.getPixel(1, 0), new PixelImpl(0, 255, 0));
    assertEquals(smallImagePPM.getPixel(1, 1), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePPM.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePPM.getPixel(2, 0), new PixelImpl(27, 177, 241));
    assertEquals(smallImagePPM.getPixel(2, 1), new PixelImpl(164, 18, 228));
    assertEquals(smallImagePPM.getPixel(2, 2), new PixelImpl(164, 18, 228));
    assertEquals(smallImagePPM.getPixel(3, 0), new PixelImpl(0, 255, 0));
    assertEquals(smallImagePPM.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePPM.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePPM.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePPM.getPixel(4, 1), new PixelImpl(18, 52, 86));
    assertEquals(smallImagePPM.getPixel(4, 2), new PixelImpl(255, 255, 255));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel1() {
    smallImagePPM.getPixel(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel2() {
    smallImagePPM.getPixel(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel3() {
    smallImagePPM.getPixel(-1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel4() {
    smallImagePPM.getPixel(5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel5() {
    smallImagePPM.getPixel(0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPixel6() {
    smallImagePPM.getPixel(5, 3);
  }

  @Test
  public void testSetPixel() {
    smallImagePPM.setPixel(0, 0, new PixelImpl(0, 0, 0));
    smallImagePPM.setPixel(1, 1, new PixelImpl(255, 255, 255));
    smallImagePPM.setPixel(2, 2, new PixelImpl(-1, 128, 256));
    smallImagePPM.setPixel(3, 1, new PixelImpl(255, 255, 255));
    smallImagePPM.setPixel(4, 2, new PixelImpl(0, 0, 0));

    assertEquals(smallImagePPM.getPixel(0, 0), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePPM.getPixel(1, 1), new PixelImpl(255, 255, 255));
    assertEquals(smallImagePPM.getPixel(2, 2), new PixelImpl(0, 128, 255));
    assertEquals(smallImagePPM.getPixel(3, 1), new PixelImpl(255, 255, 255));
    assertEquals(smallImagePPM.getPixel(4, 2), new PixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel1() {
    smallImagePPM.setPixel(-1, 0, new PixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel2() {
    smallImagePPM.setPixel(-1, 0, new PixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel3() {
    smallImagePPM.setPixel(0, -1, new PixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel4() {
    smallImagePPM.setPixel(-1, -1, new PixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel5() {
    smallImagePPM.setPixel(5, 0, new PixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel6() {
    smallImagePPM.setPixel(0, 3, new PixelImpl(0, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetPixel7() {
    smallImagePPM.setPixel(5, 3, new PixelImpl(0, 0, 0));
  }

  @Test
  public void testComponent() {
    try {
      PictureImpl smallImageRedComponent = (PictureImpl) smallImagePPM.component("red");
      PictureImpl smallImageRedComponentExpect = ImageUtil.readFile("src/pictures/smallImage" +
              "/smallImage-red-component.ppm");
      assertCompare(smallImageRedComponent, smallImageRedComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageRedComponent,
              "src/pictures/smallImage/result/redComponent.ppm");

      PictureImpl smallImageGreenComponent = (PictureImpl) smallImagePPM.component("green");
      PictureImpl smallImageGreenComponentExpect = ImageUtil.readFile("src/pictures/" +
              "smallImage/smallImage-green-component.ppm");
      assertCompare(smallImageGreenComponent, smallImageGreenComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageGreenComponent,
              "src/pictures/smallImage/result/greenComponent.ppm");

      PictureImpl smallImageBlueComponent = (PictureImpl) smallImagePPM.component("blue");
      PictureImpl smallImageBlueComponentExpect = ImageUtil.readFile("src/pictures/" +
              "smallImage/smallImage-blue-component.ppm");
      assertCompare(smallImageBlueComponent, smallImageBlueComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageBlueComponent,
              "src/pictures/smallImage/result/blueComponent.ppm");

      PictureImpl smallImageValueComponent = (PictureImpl) smallImagePPM.component("value");
      PictureImpl smallImageValueComponentExpect = ImageUtil.readFile("src/pictures/" +
              "smallImage/smallImage-value-component.ppm");
      assertCompare(smallImageValueComponent, smallImageValueComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageValueComponent,
              "src/pictures/smallImage/result/valueComponent.ppm");

      PictureImpl smallImageIntensityComponent = (PictureImpl) smallImagePPM.component("intensity");
      PictureImpl smallImageIntensityComponentExpect = ImageUtil.readFile("src/pictures/" +
              "smallImage/smallImage-intensity-component.ppm");
      assertCompare(smallImageIntensityComponent, smallImageIntensityComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageIntensityComponent, "src/pictures/" +
              "smallImage/result/intensityComponent.ppm");

      PictureImpl smallImageLumaComponent = (PictureImpl) smallImagePPM.component("luma");
      PictureImpl smallImageLumaComponentExpect = ImageUtil.readFile("src/pictures/" +
              "smallImage/smallImage-luma-component.ppm");
      assertCompare(smallImageLumaComponent, smallImageLumaComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageLumaComponent,
              "src/pictures/smallImage/result/lumaComponent.ppm");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidComponent1() {
    smallImagePPM.component("string");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidComponent2() {
    smallImagePPM.component(null);
  }

  @Test
  public void testFlip() {
    try {
      PictureImpl smallImageHorizontal = (PictureImpl) smallImagePPM.flip("horizontal");
      PictureImpl smallImageHorizontalExpect =
              ImageUtil.readFile("src/pictures/smallImage/smallImage-horizontal.ppm");
      assertCompare(smallImageHorizontal, smallImageHorizontalExpect);
      // save our results
      ImageUtil.writeFile(smallImageHorizontal,
              "src/pictures/smallImage/result/horizontal.ppm");

      // flipping horizontally smallImage twice (should be original image)
      PictureImpl smallImageHorizontalAgain =
              (PictureImpl) smallImageHorizontal.flip("horizontal");
      assertCompare(smallImageHorizontalAgain, smallImagePPM);

      PictureImpl smallImageVertical = (PictureImpl) smallImagePPM.flip("vertical");
      PictureImpl smallImageVerticalExpect =
              ImageUtil.readFile("src/pictures/smallImage/smallImage-vertical.ppm");
      assertCompare(smallImageVertical, smallImageVerticalExpect);
      // save our results
      ImageUtil.writeFile(smallImageVertical,
              "src/pictures/smallImage/result/vertical.ppm");

      // flipping vertically smallImage twice (should be original image)
      PictureImpl smallImageVerticalAgain =
              (PictureImpl) smallImageVertical.flip("vertical");
      assertCompare(smallImageVerticalAgain, smallImagePPM);

      // flipping vertically and then horizontally for smallImage
      PictureImpl smallImageVerticalHorizontal =
              (PictureImpl) smallImagePPM.flip("vertical").flip("horizontal");
      PictureImpl smallImageVerticalHorizontalExpect = ImageUtil.readFile(
              "src/pictures/smallImage/smallImage-vertical-horizontal.ppm");
      assertCompare(smallImageVerticalHorizontal, smallImageVerticalHorizontalExpect);
      // save our results
      ImageUtil.writeFile(smallImageVerticalHorizontal,
              "src/pictures/smallImage/result/vertical-horizontal.ppm");

      // flipping then horizontal and vertical for smallImage
      PictureImpl smallImageHorizontalVertical =
              (PictureImpl) smallImagePPM.flip("horizontal").flip("vertical");
      PictureImpl smallImageHorizontalVerticalExpect = ImageUtil.readFile(
              "src/pictures/smallImage/smallImage-horizontal-vertical.ppm");
      assertCompare(smallImageHorizontalVertical, smallImageHorizontalVerticalExpect);
      // save our results
      ImageUtil.writeFile(smallImageHorizontalVertical,
              "src/pictures/smallImage/result/horizontal-vertical.ppm");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFlip1() {
    smallImagePPM.component("string");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFlip2() {
    smallImagePPM.component(null);
  }

  @Test
  public void testBrighten() {
    try {
      PictureImpl smallImageBrighten = (PictureImpl) smallImagePPM.brighten(10);
      PictureImpl smallImageBrightenExpect = ImageUtil.readFile("src/pictures/" +
              "smallImage/smallImage-brighten-by-10.ppm");
      assertCompare(smallImageBrighten, smallImageBrightenExpect);
      // save our results
      ImageUtil.writeFile(smallImageBrightenExpect,
              "src/pictures/smallImage/result/brighten-by-10.ppm");

      PictureImpl smallImageDarken = (PictureImpl) smallImagePPM.brighten(-10);
      PictureImpl smallImageDarkenExpect = ImageUtil.readFile("src/pictures/" +
              "smallImage/smallImage-darken-by-10.ppm");
      assertCompare(smallImageDarken, smallImageDarkenExpect);
      // save our results
      ImageUtil.writeFile(smallImageDarken,
              "src/pictures/smallImage/result/darken-by-10.ppm");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testBlur() {
    try {
      IPicture smallPictureBlur = smallImagePPM.blur();
      PictureImpl smallPictureBlurExpect =
              ImageUtil.readFile("src/pictures/smallImage/smallImage-blur.ppm");
      assertCompare(smallPictureBlur, smallPictureBlurExpect);
      // save our results
      ImageUtil.writeFile(smallPictureBlur, "src/pictures/smallImage/result/blur.ppm");

      IPicture manhattanBlur = manhattan.blur();
      ImageUtil.writeFile(manhattanBlur, "src/pictures/manhattan-blur.ppm");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testSharpen() {
    try {
      IPicture smallPictureSharpen = smallImagePPM.sharpen();
      PictureImpl smallPictureSharpenExpect =
              ImageUtil.readFile("src/pictures/smallImage/smallImage-sharpen.ppm");
      assertCompare(smallPictureSharpen, smallPictureSharpenExpect);
      // save our results
      ImageUtil.writeFile(smallPictureSharpen, "src/pictures/smallImage/result/sharpen.ppm");

      IPicture manhattanSharpen = manhattan.sharpen();
      ImageUtil.writeFile(manhattanSharpen, "src/pictures/manhattan-sharpen.ppm");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testGreyscale() {
    try {
      IPicture smallPictureGreyscale = smallImagePPM.greyscale();
      PictureImpl smallPictureGreyscaleExpect =
              ImageUtil.readFile("src/pictures/smallImage/smallImage-greyscale.ppm");
      assertCompare(smallPictureGreyscale, smallPictureGreyscaleExpect);
      // save our results
      ImageUtil.writeFile(smallPictureGreyscale, "src/pictures/smallImage/result/greyscale.ppm");

      IPicture manhattanGreyscale = manhattan.greyscale();
      ImageUtil.writeFile(manhattanGreyscale, "src/pictures/manhattan-greyscale.ppm");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testSepia() {
    try {
      IPicture smallPictureSepia = smallImagePPM.sepia();
      PictureImpl smallPictureSepiaExpect =
              ImageUtil.readFile("src/pictures/smallImage/smallImage-sepia.ppm");
      assertCompare(smallPictureSepia, smallPictureSepiaExpect);
      // save our results
      ImageUtil.writeFile(smallPictureSepia, "src/pictures/smallImage/result/sepia.ppm");

      IPicture manhattanSharpen = manhattan.sepia();
      ImageUtil.writeFile(manhattanSharpen, "src/pictures/manhattan-sepia.ppm");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testToFile() {
    try {
      PictureImpl smallImageRedComponent = (PictureImpl) smallImagePPM.component("red");
      ImageUtil.writeFile(smallImageRedComponent,
              "src/pictures/smallImage/result/redComponent.ppm");

      PictureImpl smallImageRedComponentExpect = ImageUtil.readFile("src/pictures/" +
              "smallImage/smallImage-red-component.ppm");
      assertCompare(smallImageRedComponent, smallImageRedComponentExpect);

      // I'm testing for the fact that the file still stores data when the file is not a PPM file.
      // It still opens to the picture on my mac computer so that's cool.
      IPicture smallImageJPEG = smallImagePPM;
      ImageUtil.writeFile(smallImageJPEG,
              "src/pictures/smallImage/result/notPPM.jpeg");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = IOException.class)
  public void testInvalidToFile() throws IOException {
    PictureImpl smallImageHorizontal = (PictureImpl) smallImagePPM.flip("horizontal");
    ImageUtil.writeFile(smallImageHorizontal,null);
  }
}
