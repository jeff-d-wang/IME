import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageUtil;
import model.picture.PictureImpl;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for ImageUtil.
 */
public class ImageUtilTest {

  PictureImpl smallImage;
  PictureImpl smallImagePNG;
  PictureImpl smallImageJPG;
  PictureImpl smallImageBMP;


  @Before
  public void setUp() {
    try {
      smallImage = ImageUtil.readFile("res/smallImage/smallImage.ppm");
      smallImagePNG = ImageUtil.readFile("res/smallImage/smallImage.png");
      smallImageJPG = ImageUtil.readFile("res/smallImage/smallImage.jpeg");
      smallImageBMP = ImageUtil.readFile("res/smallImage/smallImage.bmp");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testReadPPM() {
    // tests if readPPM correctly assigns pixel color values based on the file given
    assertEquals(smallImage.getPixel(0, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImage.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImage.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImage.getPixel(1, 0), new PixelImpl(0, 255, 0));
    assertEquals(smallImage.getPixel(1, 1), new PixelImpl(255, 0, 0));
    assertEquals(smallImage.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImage.getPixel(2, 0), new PixelImpl(27, 177, 241));
    assertEquals(smallImage.getPixel(2, 1), new PixelImpl(164, 18, 228));
    assertEquals(smallImage.getPixel(2, 2), new PixelImpl(164, 18, 228));
    assertEquals(smallImage.getPixel(3, 0), new PixelImpl(0, 255, 0));
    assertEquals(smallImage.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImage.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImage.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImage.getPixel(4, 1), new PixelImpl(18, 52, 86));
    assertEquals(smallImage.getPixel(4, 2), new PixelImpl(255, 255, 255));
  }

  @Test(expected = IOException.class)
  public void testInvalidReadPPM1() throws IOException {
    ImageUtil.readFile(null);
  }

  @Test(expected = IOException.class)
  public void testInvalidReadPPM2() throws IOException {
    ImageUtil.readFile("res/smallImage/doesNotExist.ppm");
  }

  @Test(expected = IOException.class)
  public void testInvalidReadPPM3() throws IOException {
    ImageUtil.readFile("res/smallImage/doesNotExist.txt");
  }

  @Test
  public void testWriteFile() {
    try {
      PictureImpl smallImageRedGreyscale = (PictureImpl) smallImage.component("red");
      ImageUtil.writeFile(smallImageRedGreyscale, "res/smallImage/" +
              "result/redComponent.ppm");

      PictureImpl smallImageRedGreyscaleExpect = ImageUtil.readFile("res/" +
              "smallImage/smallImage-red-component.ppm");

      for (int r = 0; r < smallImageRedGreyscale.getHeight(); r++) {
        for (int c = 0; c < smallImageRedGreyscale.getWidth(); c++) {
          assertEquals(smallImageRedGreyscale.getPixel(r, c),
                  smallImageRedGreyscaleExpect.getPixel(r, c));
        }
      }

      // I'm testing for the fact that the file still stores data when the file is not a PPM file.
      // It still opens to the picture on my mac computer so that's cool.
      PictureImpl smallImageJPEG = smallImage;
      ImageUtil.writeFile(smallImageJPEG, "res/smallImage/result/notPPM.jpeg");

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test(expected = IOException.class)
  public void testInvalidWriteFile() throws IOException {
    PictureImpl smallImageHorizontal = (PictureImpl) smallImage.flip("horizontal");
    ImageUtil.writeFile(smallImageHorizontal, null);
  }

  @Test(expected = IOException.class)
  public void testInvalidP3File() throws IOException {
    ImageUtil.readFile("res/smallImage/smallImageP6.pbm");
  }

  @Test
  public void testReadPNG() {
    assertEquals(smallImagePNG.getPixel(0, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePNG.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePNG.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePNG.getPixel(1, 0), new PixelImpl(0, 255, 0));
    assertEquals(smallImagePNG.getPixel(1, 1), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePNG.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImagePNG.getPixel(2, 0), new PixelImpl(27, 177, 241));
    assertEquals(smallImagePNG.getPixel(2, 1), new PixelImpl(164, 18, 228));
    assertEquals(smallImagePNG.getPixel(2, 2), new PixelImpl(164, 18, 228));
    assertEquals(smallImagePNG.getPixel(3, 0), new PixelImpl(0, 255, 0));
    assertEquals(smallImagePNG.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImagePNG.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImagePNG.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImagePNG.getPixel(4, 1), new PixelImpl(18, 52, 86));
    assertEquals(smallImagePNG.getPixel(4, 2), new PixelImpl(255, 255, 255));
  }

  @Test
  public void testReadJPG() {
    // numbers are off because of loosely compression
    assertEquals(smallImageJPG.getPixel(0, 0), new PixelImpl(254, 0, 7));
    assertEquals(smallImageJPG.getPixel(0, 1), new PixelImpl(0, 0, 18));
    assertEquals(smallImageJPG.getPixel(0, 2), new PixelImpl(0, 2, 239));
    assertEquals(smallImageJPG.getPixel(1, 0), new PixelImpl(0, 255, 0));
    assertEquals(smallImageJPG.getPixel(1, 1), new PixelImpl(255, 7, 0));
    assertEquals(smallImageJPG.getPixel(1, 2), new PixelImpl(0, 0, 246));
    assertEquals(smallImageJPG.getPixel(2, 0), new PixelImpl(28, 182, 242));
    assertEquals(smallImageJPG.getPixel(2, 1), new PixelImpl(150, 25, 217));
    assertEquals(smallImageJPG.getPixel(2, 2), new PixelImpl(175, 12, 249));
    assertEquals(smallImageJPG.getPixel(3, 0), new PixelImpl(0, 251, 11));
    assertEquals(smallImageJPG.getPixel(3, 1), new PixelImpl(7, 0, 9));
    assertEquals(smallImageJPG.getPixel(3, 2), new PixelImpl(237, 114, 55));
    assertEquals(smallImageJPG.getPixel(4, 0), new PixelImpl(244, 7, 0));
    assertEquals(smallImageJPG.getPixel(4, 1), new PixelImpl(53, 57, 105));
    assertEquals(smallImageJPG.getPixel(4, 2), new PixelImpl(255, 252, 245));
  }

  @Test
  public void testReadBMP() {
    assertEquals(smallImageBMP.getPixel(0, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImageBMP.getPixel(0, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImageBMP.getPixel(0, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImageBMP.getPixel(1, 0), new PixelImpl(0, 255, 0));
    assertEquals(smallImageBMP.getPixel(1, 1), new PixelImpl(255, 0, 0));
    assertEquals(smallImageBMP.getPixel(1, 2), new PixelImpl(0, 0, 255));
    assertEquals(smallImageBMP.getPixel(2, 0), new PixelImpl(27, 177, 241));
    assertEquals(smallImageBMP.getPixel(2, 1), new PixelImpl(164, 18, 228));
    assertEquals(smallImageBMP.getPixel(2, 2), new PixelImpl(164, 18, 228));
    assertEquals(smallImageBMP.getPixel(3, 0), new PixelImpl(0, 255, 0));
    assertEquals(smallImageBMP.getPixel(3, 1), new PixelImpl(0, 0, 0));
    assertEquals(smallImageBMP.getPixel(3, 2), new PixelImpl(242, 113, 65));
    assertEquals(smallImageBMP.getPixel(4, 0), new PixelImpl(255, 0, 0));
    assertEquals(smallImageBMP.getPixel(4, 1), new PixelImpl(18, 52, 86));
    assertEquals(smallImageBMP.getPixel(4, 2), new PixelImpl(255, 255, 255));
  }

  @Test
  public void testWritePNG() {
    try {
      // from ppm to png
      ImageUtil.writeFile(smallImage, "res/smallImage/" +
              "/result/smallImagePPMto.png");

      PictureImpl smallImagePPMtoPNG
              = ImageUtil.readFile("res/smallImage/result/smallImagePPMto.png");

      compareImages(smallImagePPMtoPNG, smallImagePNG);

      // from jpg to png
      ImageUtil.writeFile(smallImageJPG, "res/smallImage/" +
              "/result/smallImageJPGto.png");

      PictureImpl smallImageJPGtoPNG
              = ImageUtil.readFile("res/smallImage/result/smallImageJPGto.png");

      // image will not be same because of loosely compression, so I compared to the JPG file
      compareImages(smallImageJPGtoPNG, smallImageJPG);

      // from bmp to png
      ImageUtil.writeFile(smallImageBMP, "res/smallImage/" +
              "/result/smallImageBMPto.png");

      PictureImpl smallImageBMPtoPNG
              = ImageUtil.readFile("res/smallImage/result/smallImageBMPto.png");

      compareImages(smallImageBMPtoPNG, smallImagePNG);

      // from png to png
      ImageUtil.writeFile(smallImagePNG, "res/smallImage/" +
              "/result/smallImagePNGto.png");

      PictureImpl smallImagePNGtoPNG
              = ImageUtil.readFile("res/smallImage/result/smallImagePNGto.png");

      compareImages(smallImagePNGtoPNG, smallImagePNG);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testWriteJPG() {
    try {
      // from PPM to JPG
      ImageUtil.writeFile(smallImage, "res/smallImage/" +
              "/result/smallImagePPMto.jpg");

      PictureImpl smallImagePPMtoJPG
              = ImageUtil.readFile("res/smallImage/result/smallImagePPMto.jpg");

      // compareImages(smallImagePPMtoJPG, smallImageJPG);

      // from PNG to JPG
      ImageUtil.writeFile(smallImagePNG, "res/smallImage/" +
              "/result/smallImagePNGto.jpg");

      PictureImpl smallImagePNGtoJPG
              = ImageUtil.readFile("res/smallImage/result/smallImagePNGto.jpg");

      //compareImages(smallImagePNGtoJPG, smallImageJPG);

      // from BMP to JPG
      ImageUtil.writeFile(smallImageBMP, "res/smallImage/" +
              "/result/smallImageBMPto.jpg");

      PictureImpl smallImageBMPtoJPG
              = ImageUtil.readFile("res/smallImage/result/smallImageBMPto.jpg");

      //compareImages(smallImageBMPtoJPG, smallImageJPG);

      // from JPG to JPG
      ImageUtil.writeFile(smallImageJPG, "res/smallImage/" +
              "/result/smallImageJPGto.jpg");

      PictureImpl smallImageJPGtoJPG
              = ImageUtil.readFile("res/smallImage/result/smallImageJPGto.jpg");

      //compareImages(smallImageJPGtoJPG, smallImageJPG);


    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testWriteBMP() {
    try {
      // from ppm to bmp
      ImageUtil.writeFile(smallImage, "res/smallImage/" +
              "/result/smallImagePPMto.bmp");

      PictureImpl smallImagePPMtoBMP
              = ImageUtil.readFile("res/smallImage/result/smallImagePPMto.bmp");

      compareImages(smallImagePPMtoBMP, smallImageBMP);


      // from png to bmp
      ImageUtil.writeFile(smallImagePNG, "res/smallImage/" +
              "/result/smallImagePNGto.bmp");

      PictureImpl smallImagePNGtoBMP
              = ImageUtil.readFile("res/smallImage/result/smallImagePNGto.bmp");

      compareImages(smallImagePNGtoBMP, smallImageBMP);

      // from jpg to bmp
      ImageUtil.writeFile(smallImageJPG, "res/smallImage/" +
              "/result/smallImageJPGto.bmp");

      PictureImpl smallImageJPGtoBMP
              = ImageUtil.readFile("res/smallImage/result/smallImageJPGto.bmp");

      // image will not be same because of loosely compression, so I compared to the JPG file
      compareImages(smallImageJPGtoBMP, smallImageJPG);

      // from bmp to bmp
      ImageUtil.writeFile(smallImageBMP, "res/smallImage/" +
              "/result/smallImageBMPto.bmp");

      PictureImpl smallImageBMPtoBMP
              = ImageUtil.readFile("res/smallImage/result/smallImageBMPto.bmp");

      compareImages(smallImageBMPtoBMP, smallImageBMP);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private void compareImages(PictureImpl newPic, PictureImpl original) {
    for (int r = 0; r < newPic.getHeight(); r++) {
      for (int c = 0; c < newPic.getWidth(); c++) {
        assertEquals(newPic.getPixel(r, c),
                original.getPixel(r, c));
      }
    }
  }

}
