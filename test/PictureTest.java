import org.junit.Before;
import org.junit.Test;

import model.Picture;
import model.Pixel.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for picture.
 */
public class PictureTest {
  Picture pic1;
  Picture pic2;

  @Before
  public void setUp(){
    pic1 = new Picture(3, 4);
    pic2 = new Picture (5, 6);
  }

  @Test
  public void testGetWidth() {
    assertEquals(pic1.getWidth(), 3);
    assertEquals(pic2.getWidth(), 5);
  }

  @Test
  public void testGetHeight() {
    assertEquals(pic1.getHeight(), 4);
    assertEquals(pic2.getHeight(), 6);

  }

  @Test
  public void testGetPixel() {
    assertEquals(pic1.getPixel(0, 0), null);
    assertEquals(pic1.getPixel(0, 1), null);
    assertEquals(pic1.getPixel(0, 2), null);
  }

  @Test
  public void testSetPixel() {
    pic1.setPixel(0, 0, new RGBPixel(0, 0, 0));
    assertEquals(pic1.getPixel(0, 0), new RGBPixel(0, 0, 0));

    pic2.setPixel(0, 0, new RGBPixel(255, 255, 255));
    assertEquals(pic2.getPixel(0, 0), new RGBPixel(255, 255, 255));
    assertEquals(pic2.getPixel(0, 0).getR(), 255);
    assertEquals(pic2.getPixel(0, 0).getG(), 255);
    assertEquals(pic2.getPixel(0, 0).getB(), 255);
  }

  @Test
  public void testGreyscale() {
    assertEquals(pic2.greyscale("red"), null);
  }

}
