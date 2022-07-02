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
      smallImagePPM = ImageUtil.readFile("res/smallImage/smallImage.ppm");
      // manhattan = ImageUtil.readFile("res/manhattan.png");
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
      IPicture smallImageRedComponent = smallImagePPM.component("red");
      IPicture smallImageRedComponentExpect = ImageUtil.readFile("res/smallImage"
              + "/smallImage-red-component.ppm");
      assertCompare(smallImageRedComponent, smallImageRedComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageRedComponent,
              "res/smallImage/result/redComponent.ppm");

      IPicture smallImageGreenComponent = smallImagePPM.component("green");
      IPicture smallImageGreenComponentExpect = ImageUtil.readFile("res/"
              + "smallImage/smallImage-green-component.ppm");
      assertCompare(smallImageGreenComponent, smallImageGreenComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageGreenComponent,
              "res/smallImage/result/greenComponent.ppm");

      IPicture smallImageBlueComponent = smallImagePPM.component("blue");
      IPicture smallImageBlueComponentExpect = ImageUtil.readFile("res/"
              + "smallImage/smallImage-blue-component.ppm");
      assertCompare(smallImageBlueComponent, smallImageBlueComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageBlueComponent,
              "res/smallImage/result/blueComponent.ppm");

      IPicture smallImageValueComponent = smallImagePPM.component("value");
      IPicture smallImageValueComponentExpect = ImageUtil.readFile("res/"
              + "smallImage/smallImage-value-component.ppm");
      assertCompare(smallImageValueComponent, smallImageValueComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageValueComponent,
              "res/smallImage/result/valueComponent.ppm");

      IPicture smallImageIntensityComponent = smallImagePPM.component("intensity");
      IPicture smallImageIntensityComponentExpect = ImageUtil.readFile("res/"
              + "smallImage/smallImage-intensity-component.ppm");
      assertCompare(smallImageIntensityComponent, smallImageIntensityComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageIntensityComponent, "res/"
              + "smallImage/result/intensityComponent.ppm");

      IPicture smallImageLumaComponent = smallImagePPM.component("luma");
      IPicture smallImageLumaComponentExpect = ImageUtil.readFile("res/"
              + "smallImage/smallImage-luma-component.ppm");
      assertCompare(smallImageLumaComponent, smallImageLumaComponentExpect);
      // save our results
      ImageUtil.writeFile(smallImageLumaComponent,
              "res/smallImage/result/lumaComponent.ppm");

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
      IPicture smallImageHorizontal = smallImagePPM.flip("horizontal");
      IPicture smallImageHorizontalExpect =
              ImageUtil.readFile("res/smallImage/smallImage-horizontal.ppm");
      assertCompare(smallImageHorizontal, smallImageHorizontalExpect);
      // save our results
      ImageUtil.writeFile(smallImageHorizontal,
              "res/smallImage/result/horizontal.ppm");

      // flipping horizontally smallImage twice (should be original image)
      PictureImpl smallImageHorizontalAgain =
              (PictureImpl) smallImageHorizontal.flip("horizontal");
      assertCompare(smallImageHorizontalAgain, smallImagePPM);

      IPicture smallImageVertical = smallImagePPM.flip("vertical");
      IPicture smallImageVerticalExpect =
              ImageUtil.readFile("res/smallImage/smallImage-vertical.ppm");
      assertCompare(smallImageVertical, smallImageVerticalExpect);
      // save our results
      ImageUtil.writeFile(smallImageVertical,
              "res/smallImage/result/vertical.ppm");

      // flipping vertically smallImage twice (should be original image)
      PictureImpl smallImageVerticalAgain =
              (PictureImpl) smallImageVertical.flip("vertical");
      assertCompare(smallImageVerticalAgain, smallImagePPM);

      // flipping vertically and then horizontally for smallImage
      IPicture smallImageVerticalHorizontal =
              smallImagePPM.flip("vertical").flip("horizontal");
      IPicture smallImageVerticalHorizontalExpect = ImageUtil.readFile(
              "res/smallImage/smallImage-vertical-horizontal.ppm");
      assertCompare(smallImageVerticalHorizontal, smallImageVerticalHorizontalExpect);
      // save our results
      ImageUtil.writeFile(smallImageVerticalHorizontal,
              "res/smallImage/result/vertical-horizontal.ppm");

      // flipping then horizontal and vertical for smallImage
      IPicture smallImageHorizontalVertical =
              smallImagePPM.flip("horizontal").flip("vertical");
      IPicture smallImageHorizontalVerticalExpect = ImageUtil.readFile(
              "res/smallImage/smallImage-horizontal-vertical.ppm");
      assertCompare(smallImageHorizontalVertical, smallImageHorizontalVerticalExpect);
      // save our results
      ImageUtil.writeFile(smallImageHorizontalVertical,
              "res/smallImage/result/horizontal-vertical.ppm");

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
      IPicture smallImageBrighten = smallImagePPM.brighten(10);
      IPicture smallImageBrightenExpect = ImageUtil.readFile("res/" +
              "smallImage/smallImage-brighten-by-10.ppm");
      assertCompare(smallImageBrighten, smallImageBrightenExpect);
      // save our results
      ImageUtil.writeFile(smallImageBrightenExpect,
              "res/smallImage/result/brighten-by-10.ppm");

      IPicture smallImageDarken = smallImagePPM.brighten(-10);
      IPicture smallImageDarkenExpect = ImageUtil.readFile("res/" +
              "smallImage/smallImage-darken-by-10.ppm");
      assertCompare(smallImageDarken, smallImageDarkenExpect);
      // save our results
      ImageUtil.writeFile(smallImageDarken,
              "res/smallImage/result/darken-by-10.ppm");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testBlur() {
    try {
      IPicture smallPictureBlur = smallImagePPM.blur();
      IPicture smallPictureBlurExpect =
              ImageUtil.readFile("res/smallImage/smallImage-blur.ppm");
      assertCompare(smallPictureBlur, smallPictureBlurExpect);
      // save our results
      ImageUtil.writeFile(smallPictureBlur, "res/smallImage/result/blur.ppm");

      // Test on manhattan works
      // IPicture manhattanBlur = manhattan.blur();
      // ImageUtil.writeFile(manhattanBlur, "res/manhattan-blur.ppm");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testSharpen() {
    try {
      IPicture smallPictureSharpen = smallImagePPM.sharpen();
      IPicture smallPictureSharpenExpect =
              ImageUtil.readFile("res/smallImage/smallImage-sharpen.ppm");
      assertCompare(smallPictureSharpen, smallPictureSharpenExpect);
      // save our results
      ImageUtil.writeFile(smallPictureSharpen, "res/smallImage/result/sharpen.ppm");

      // Test on manhattan works
      // IPicture manhattanSharpen = manhattan.sharpen();
      // ImageUtil.writeFile(manhattanSharpen, "res/manhattan-sharpen.ppm");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testGreyscale() {
    try {
      IPicture smallPictureGreyscale = smallImagePPM.greyscale();
      IPicture smallPictureGreyscaleExpect =
              ImageUtil.readFile("res/smallImage/smallImage-greyscale.ppm");
      assertCompare(smallPictureGreyscale, smallPictureGreyscaleExpect);
      // save our results
      ImageUtil.writeFile(smallPictureGreyscale, "res/smallImage/result/greyscale.ppm");

      // Test on manhattan works
      // IPicture manhattanGreyscale = manhattan.greyscale();
      // ImageUtil.writeFile(manhattanGreyscale, "res/manhattan-greyscale.ppm");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testSepia() {
    try {
      IPicture smallPictureSepia = smallImagePPM.sepia();
      IPicture smallPictureSepiaExpect =
              ImageUtil.readFile("res/smallImage/smallImage-sepia.ppm");
      assertCompare(smallPictureSepia, smallPictureSepiaExpect);
      // save our results
      ImageUtil.writeFile(smallPictureSepia, "res/smallImage/result/sepia.ppm");

      // Test on manhattan works
      // IPicture manhattanSharpen = manhattan.sepia();
      // ImageUtil.writeFile(manhattanSharpen, "res/manhattan-sepia.ppm");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testToFile() {
    try {
      PictureImpl smallImageRedComponent = (PictureImpl) smallImagePPM.component("red");
      ImageUtil.writeFile(smallImageRedComponent,
              "res/smallImage/result/redComponent.ppm");

      IPicture smallImageRedComponentExpect = ImageUtil.readFile("res/" +
              "smallImage/smallImage-red-component.ppm");
      assertCompare(smallImageRedComponent, smallImageRedComponentExpect);

      // I'm testing for the fact that the file still stores data when the file is not a PPM file.
      // It still opens to the picture on my mac computer so that's cool.
      IPicture smallImageJPEG = smallImagePPM;
      ImageUtil.writeFile(smallImageJPEG,
              "res/smallImage/result/notPPM.jpeg");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = IOException.class)
  public void testInvalidToFile() throws IOException {
    PictureImpl smallImageHorizontal = (PictureImpl) smallImagePPM.flip("horizontal");
    ImageUtil.writeFile(smallImageHorizontal, null);
  }

  @Test
  public void testPartialImage() throws IOException {
    IPicture maskPPM = ImageUtil.readFile("res/smallImage/mask.ppm");

    // BRIGHTEN
    IPicture smallImagePartialBrighten
            = smallImagePPM.partialImageManipulation(maskPPM, "brighten 10");

    // change
    assertEquals(smallImagePartialBrighten.getPixel(0, 0), new PixelImpl(255, 10, 10));
    assertEquals(smallImagePartialBrighten.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialBrighten.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialBrighten.getPixel(1, 0), new PixelImpl(0, 255, 0));
    //change
    assertEquals(smallImagePartialBrighten.getPixel(1, 1), new PixelImpl(255, 10, 10));
    assertEquals(smallImagePartialBrighten.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialBrighten.getPixel(2, 0), new PixelImpl(27, 177, 241));
    //change
    assertEquals(smallImagePartialBrighten.getPixel(2, 1), new PixelImpl(174, 28, 238));
    assertEquals(smallImagePartialBrighten.getPixel(2, 2), new PixelImpl(164, 18, 228));
    //change
    assertEquals(smallImagePartialBrighten.getPixel(3, 0), new PixelImpl(10, 255, 10));
    assertEquals(smallImagePartialBrighten.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialBrighten.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePartialBrighten.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePartialBrighten.getPixel(4, 1), new PixelImpl(18, 52, 86));
    // change
    assertEquals(smallImagePartialBrighten.getPixel(4, 2), new PixelImpl(255, 255, 255));

    ImageUtil.writeFile(smallImagePartialBrighten,
            "res/smallImage/result/smallImagePartialBrighten.png");

    // RED COMPONENT
    IPicture smallImagePartialRComponent
            = smallImagePPM.partialImageManipulation(maskPPM, "component red");

    // change
    assertEquals(smallImagePartialRComponent.getPixel(0, 0), new PixelImpl(255, 255, 255));
    assertEquals(smallImagePartialRComponent.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialRComponent.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialRComponent.getPixel(1, 0), new PixelImpl(0, 255, 0));
    //change
    assertEquals(smallImagePartialRComponent.getPixel(1, 1), new PixelImpl(255, 255, 255));
    assertEquals(smallImagePartialRComponent.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialRComponent.getPixel(2, 0), new PixelImpl(27, 177, 241));
    //change
    assertEquals(smallImagePartialRComponent.getPixel(2, 1), new PixelImpl(164, 164, 164));
    assertEquals(smallImagePartialRComponent.getPixel(2, 2), new PixelImpl(164, 18, 228));
    //change
    assertEquals(smallImagePartialRComponent.getPixel(3, 0), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialRComponent.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialRComponent.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePartialRComponent.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePartialRComponent.getPixel(4, 1), new PixelImpl(18, 52, 86));
    // change
    assertEquals(smallImagePartialRComponent.getPixel(4, 2), new PixelImpl(255, 255, 255));

    ImageUtil.writeFile(smallImagePartialRComponent,
            "res/smallImage/result/smallImagePartialRComponent.png");

    // BLUE COMPONENT
    IPicture smallImagePartialBComponent
            = smallImagePPM.partialImageManipulation(maskPPM, "component blue");

    // change
    assertEquals(smallImagePartialBComponent.getPixel(0, 0), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialBComponent.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialBComponent.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialBComponent.getPixel(1, 0), new PixelImpl(0, 255, 0));
    //change
    assertEquals(smallImagePartialBComponent.getPixel(1, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialBComponent.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialBComponent.getPixel(2, 0), new PixelImpl(27, 177, 241));
    //change
    assertEquals(smallImagePartialBComponent.getPixel(2, 1), new PixelImpl(228, 228, 228));
    assertEquals(smallImagePartialBComponent.getPixel(2, 2), new PixelImpl(164, 18, 228));
    //change
    assertEquals(smallImagePartialBComponent.getPixel(3, 0), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialBComponent.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialBComponent.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePartialBComponent.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePartialBComponent.getPixel(4, 1), new PixelImpl(18, 52, 86));
    // change
    assertEquals(smallImagePartialBComponent.getPixel(4, 2), new PixelImpl(255, 255, 255));

    ImageUtil.writeFile(smallImagePartialBComponent,
            "res/smallImage/result/smallImagePartialBComponent.png");

    // GREEN COMPONENT
    IPicture smallImagePartialGComponent
            = smallImagePPM.partialImageManipulation(maskPPM, "component green");

    // change
    assertEquals(smallImagePartialGComponent.getPixel(0, 0), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialGComponent.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialGComponent.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialGComponent.getPixel(1, 0), new PixelImpl(0, 255, 0));
    //change
    assertEquals(smallImagePartialGComponent.getPixel(1, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialGComponent.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialGComponent.getPixel(2, 0), new PixelImpl(27, 177, 241));
    //change
    assertEquals(smallImagePartialGComponent.getPixel(2, 1), new PixelImpl(18, 18, 18));
    assertEquals(smallImagePartialGComponent.getPixel(2, 2), new PixelImpl(164, 18, 228));
    //change
    assertEquals(smallImagePartialGComponent.getPixel(3, 0), new PixelImpl(255, 255, 255));
    assertEquals(smallImagePartialGComponent.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialGComponent.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePartialGComponent.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePartialGComponent.getPixel(4, 1), new PixelImpl(18, 52, 86));
    // change
    assertEquals(smallImagePartialGComponent.getPixel(4, 2), new PixelImpl(255, 255, 255));

    ImageUtil.writeFile(smallImagePartialGComponent,
            "res/smallImage/result/smallImagePartialGComponent.png");

    // BLUR
    IPicture smallImagePartialBlur
            = smallImagePPM.partialImageManipulation(maskPPM, "blur");

    // change
    assertEquals(smallImagePartialBlur.getPixel(0, 0), new PixelImpl(78, 31, 0));
    assertEquals(smallImagePartialBlur.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialBlur.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialBlur.getPixel(1, 0), new PixelImpl(0, 255, 0));
    //change
    assertEquals(smallImagePartialBlur.getPixel(1, 1), new PixelImpl(109, 45, 103));
    assertEquals(smallImagePartialBlur.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialBlur.getPixel(2, 0), new PixelImpl(27, 177, 241));
    //change
    assertEquals(smallImagePartialBlur.getPixel(2, 1), new PixelImpl(110, 65, 134));
    assertEquals(smallImagePartialBlur.getPixel(2, 2), new PixelImpl(164, 18, 228));
    //change
    assertEquals(smallImagePartialBlur.getPixel(3, 0), new PixelImpl(45, 89, 49));
    assertEquals(smallImagePartialBlur.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialBlur.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePartialBlur.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePartialBlur.getPixel(4, 1), new PixelImpl(18, 52, 86));
    // change
    assertEquals(smallImagePartialBlur.getPixel(4, 2), new PixelImpl(95, 83, 81));

    ImageUtil.writeFile(smallImagePartialBlur,
            "res/smallImage/result/smallImagePartialBlur.png");

    // SHARPEN
    IPicture smallImagePartialSharpen
            = smallImagePPM.partialImageManipulation(maskPPM, "sharpen");
    // change
    assertEquals(smallImagePartialSharpen.getPixel(0, 0), new PixelImpl(255, 37, 0));
    assertEquals(smallImagePartialSharpen.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialSharpen.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialSharpen.getPixel(1, 0), new PixelImpl(0, 255, 0));
    //change
    assertEquals(smallImagePartialSharpen.getPixel(1, 1), new PixelImpl(255, 70, 255));
    assertEquals(smallImagePartialSharpen.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialSharpen.getPixel(2, 0), new PixelImpl(27, 177, 241));
    //change
    assertEquals(smallImagePartialSharpen.getPixel(2, 1), new PixelImpl(239, 183, 255));
    assertEquals(smallImagePartialSharpen.getPixel(2, 2), new PixelImpl(164, 18, 228));
    //change
    assertEquals(smallImagePartialSharpen.getPixel(3, 0), new PixelImpl(2, 238, 40));
    assertEquals(smallImagePartialSharpen.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialSharpen.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePartialSharpen.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePartialSharpen.getPixel(4, 1), new PixelImpl(18, 52, 86));
    // change
    assertEquals(smallImagePartialSharpen.getPixel(4, 2), new PixelImpl(245, 239, 206));

    ImageUtil.writeFile(smallImagePartialSharpen,
            "res/smallImage/result/smallImagePartialSharpen.png");

    // GREYSCALE
    IPicture smallImagePartialGreyscale
            = smallImagePPM.partialImageManipulation(maskPPM, "greyscale");
    // change
    assertEquals(smallImagePartialGreyscale.getPixel(0, 0), new PixelImpl(54, 54, 54));
    assertEquals(smallImagePartialGreyscale.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialGreyscale.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialGreyscale.getPixel(1, 0), new PixelImpl(0, 255, 0));
    //change
    assertEquals(smallImagePartialGreyscale.getPixel(1, 1), new PixelImpl(54, 54, 54));
    assertEquals(smallImagePartialGreyscale.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialGreyscale.getPixel(2, 0), new PixelImpl(27, 177, 241));
    //change
    assertEquals(smallImagePartialGreyscale.getPixel(2, 1), new PixelImpl(64, 64, 64));
    assertEquals(smallImagePartialGreyscale.getPixel(2, 2), new PixelImpl(164, 18, 228));
    //change
    assertEquals(smallImagePartialGreyscale.getPixel(3, 0), new PixelImpl(182, 182, 182));
    assertEquals(smallImagePartialGreyscale.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialGreyscale.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePartialGreyscale.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePartialGreyscale.getPixel(4, 1), new PixelImpl(18, 52, 86));
    // change
    assertEquals(smallImagePartialGreyscale.getPixel(4, 2), new PixelImpl(254, 254, 254));

    ImageUtil.writeFile(smallImagePartialGreyscale,
            "res/smallImage/result/smallImagePartialGreyscale.png");

    // SEPIA
    IPicture smallImagePartialSepia
            = smallImagePPM.partialImageManipulation(maskPPM, "sepia");

    // change
    assertEquals(smallImagePartialSepia.getPixel(0, 0), new PixelImpl(100, 88, 69));
    assertEquals(smallImagePartialSepia.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialSepia.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialSepia.getPixel(1, 0), new PixelImpl(0, 255, 0));
    //change
    assertEquals(smallImagePartialSepia.getPixel(1, 1), new PixelImpl(100, 88, 69));
    assertEquals(smallImagePartialSepia.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePartialSepia.getPixel(2, 0), new PixelImpl(27, 177, 241));
    //change
    assertEquals(smallImagePartialSepia.getPixel(2, 1), new PixelImpl(121, 107, 84));
    assertEquals(smallImagePartialSepia.getPixel(2, 2), new PixelImpl(164, 18, 228));
    //change
    assertEquals(smallImagePartialSepia.getPixel(3, 0), new PixelImpl(196, 174, 136));
    assertEquals(smallImagePartialSepia.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePartialSepia.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePartialSepia.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePartialSepia.getPixel(4, 1), new PixelImpl(18, 52, 86));
    // change
    assertEquals(smallImagePartialSepia.getPixel(4, 2), new PixelImpl(255, 255, 238));

    ImageUtil.writeFile(smallImagePartialSepia,
            "res/smallImage/result/smallImagePartialSepia.png");
  }
}
