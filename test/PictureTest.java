import org.junit.Before;
import org.junit.Test;

import model.ImageUtil;
import model.Picture;
import model.Pixel.IPixel;
import model.Pixel.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for picture.
 */
public class PictureTest {
  Picture pic1;
  Picture koala;

  IPixel black;
  IPixel red;
  IPixel blue;
  IPixel green;
  IPixel white;

  @Before
  public void setUp() {
    black = new RGBPixel(0, 0, 0);
    red = new RGBPixel(255, 0, 0);
    blue = new RGBPixel(0, 0, 255);
    green = new RGBPixel(0, 255, 0);
    white = new RGBPixel(255, 255, 255);
    pic1 = new Picture(3, 4);
    // first row
    pic1.setPixel(0, 0, black);
    pic1.setPixel(0, 1, black);
    pic1.setPixel(0, 2, black);
    pic1.setPixel(0, 3, black);

    // second row
    pic1.setPixel(1, 0, red);
    pic1.setPixel(1, 1, blue);
    pic1.setPixel(1, 2, green);
    pic1.setPixel(1, 3, black);

    // third row
    pic1.setPixel(2, 0, white);
    pic1.setPixel(2, 1, blue);
    pic1.setPixel(2, 2, white);
    pic1.setPixel(2, 3, green);

    System.out.println(pic1);

    // koala = ImageUtil.readPicture("Koala.ppm");
  }

  @Test
  public void testGetWidth() {
    assertEquals(pic1.getWidth(), 3);
    assertEquals(koala.getWidth(), 5);
  }

  @Test
  public void testGetHeight() {
    assertEquals(pic1.getHeight(), 4);
    assertEquals(koala.getHeight(), 6);

  }

  @Test
  public void testGetPixel() {
    assertEquals(pic1.getPixel(0, 0), black);
    assertEquals(pic1.getPixel(0, 1), black);
    assertEquals(pic1.getPixel(0, 2), black);
  }

  @Test
  public void testSetPixel() {
    pic1.setPixel(0, 0, new RGBPixel(0, 0, 0));
    assertEquals(pic1.getPixel(0, 0), new RGBPixel(0, 0, 0));

    pic1.setPixel(0, 1, new RGBPixel(255, 255, 255));
    assertEquals(pic1.getPixel(0, 1), new RGBPixel(255, 255, 255));
    assertEquals(pic1.getPixel(0, 1).getR(), 255);
    assertEquals(pic1.getPixel(0, 1).getG(), 255);
    assertEquals(pic1.getPixel(0, 1).getB(), 255);
  }

  @Test
  public void testGreyscaleRed() {
    assertEquals(pic1.greyscale("red"), null);
  }

  @Test
  public void testGreyscaleGreen() {
    assertEquals(pic1.greyscale("green"), null);
  }

  @Test
  public void testGreyscaleBlue() {
    assertEquals(pic1.greyscale("red"), null);
  }

  @Test
  public void testGreyscaleValue() {
    assertEquals(pic1.greyscale("red"), null);
  }
  @Test
  public void testGreyscaleIntensity() {
    assertEquals(pic1.greyscale("red"), null);
  }
  @Test
  public void testGreyscaleLuma() {
    assertEquals(pic1.greyscale("red"), null);
  }

  @Test
  public void testFlipVertical() {
    // black black black black
    // red blue green black
    // white blue white green
    pic1.flip("vertical");
    // first row
    assertEquals(pic1.getPixel(0, 0), white);
    assertEquals(pic1.getPixel(0, 1), blue);
    assertEquals(pic1.getPixel(0, 2), white);
    assertEquals(pic1.getPixel(0, 3), green);

    assertEquals(pic1.getPixel(1, 0), red);
    assertEquals(pic1.getPixel(1, 1), blue);
    assertEquals(pic1.getPixel(1, 2), green);
    assertEquals(pic1.getPixel(1, 3), black);

    assertEquals(pic1.getPixel(2, 0), black);
    assertEquals(pic1.getPixel(2, 1), black);
    assertEquals(pic1.getPixel(2, 2), black);
    assertEquals(pic1.getPixel(2, 3), black);

  }

  @Test
  public void testFlipHorizontal() {

  }

  @Test
  public void testIllegalFlip() {

  }

  @Test
  public void testBrighten() {
    // increase

    // decrease

    // stay same
  }

  @Test
  public void testToPPM() {

  }

}
