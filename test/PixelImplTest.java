import org.junit.Before;
import org.junit.Test;

import model.pixel.IPixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for pixel.
 */
public class PixelImplTest {
  IPixel red;
  IPixel green;
  IPixel blue;
  IPixel black;
  IPixel white;

  @Before
  public void setUp() {
    red = new PixelImpl(255, 0, 0);
    green = new PixelImpl(0, 255, 0);
    blue = new PixelImpl(0, 0, 255);
    black = new PixelImpl(0, 0, 0);
    white = new PixelImpl(255, 255, 255);
  }


  @Test
  public void testConstructor() {
    IPixel pixelTest1 = new PixelImpl(-255, -1, 0);
    assertEquals(pixelTest1.getR(), 0);
    assertEquals(pixelTest1.getG(), 0);
    assertEquals(pixelTest1.getB(), 0);

    IPixel pixelTest2 = new PixelImpl(255, 256, 510);
    assertEquals(pixelTest2.getR(), 255);
    assertEquals(pixelTest2.getG(), 255);
    assertEquals(pixelTest2.getB(), 255);
  }

  @Test
  public void testGetRGB() {
    // getR
    assertEquals(red.getR(), 255);
    assertEquals(green.getR(), 0);
    assertEquals(blue.getR(), 0);
    assertEquals(white.getR(), 255);
    assertEquals(black.getR(), 0);

    // getG
    assertEquals(red.getG(), 0);
    assertEquals(green.getG(), 255);
    assertEquals(blue.getG(), 0);
    assertEquals(white.getG(), 255);
    assertEquals(black.getG(), 0);

    // getB
    assertEquals(red.getB(), 0);
    assertEquals(green.getB(), 0);
    assertEquals(blue.getB(), 255);
    assertEquals(white.getB(), 255);
    assertEquals(black.getB(), 0);
  }

  @Test
  public void testGetValue() {
    assertEquals(red.getValue(), 255);
    assertEquals(green.getValue(), 255);
    assertEquals(blue.getValue(), 255);
    assertEquals(white.getValue(), 255);
    assertEquals(black.getValue(), 0);
  }

  @Test
  public void testGetIntensity() {
    assertEquals(red.getIntensity(), 85);
    assertEquals(green.getIntensity(), 85);
    assertEquals(blue.getIntensity(), 85);
    assertEquals(white.getIntensity(), 255);
    assertEquals(black.getIntensity(), 0);
  }

  @Test
  public void testGetLuma() {
    assertEquals(red.getLuma(), 54);
    assertEquals(green.getLuma(), 182);
    assertEquals(blue.getLuma(), 18);
    assertEquals(white.getLuma(), 254);
    assertEquals(black.getLuma(), 0);
  }
}
