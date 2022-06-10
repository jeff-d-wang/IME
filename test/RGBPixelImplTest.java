import org.junit.Before;
import org.junit.Test;

import model.pixel.RGBPixel;
import model.pixel.RGBPixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for pixel.
 */
public class RGBPixelImplTest {
  RGBPixel red;
  RGBPixel green;
  RGBPixel blue;
  RGBPixel black;
  RGBPixel white;

  @Before
  public void setUp() {
    red = new RGBPixelImpl(255, 0, 0);
    green = new RGBPixelImpl(0, 255, 0);
    blue = new RGBPixelImpl(0, 0, 255);
    black = new RGBPixelImpl(0, 0, 0);
    white = new RGBPixelImpl(255, 255, 255);
  }


  @Test
  public void testConstructor() {
    RGBPixel pixelTest1 = new RGBPixelImpl(-255, -1, 0);
    assertEquals(pixelTest1.getR(), 0);
    assertEquals(pixelTest1.getG(), 0);
    assertEquals(pixelTest1.getB(), 0);

    RGBPixel pixelTest2 = new RGBPixelImpl(255, 256, 510);
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
  public void testSetRGB() {
    // setR
    red.setR(1);
    green.setR(2);
    blue.setR(3);
    assertEquals(red.getR(), 1);
    assertEquals(green.getR(), 2);
    assertEquals(blue.getR(), 3);

    // setG
    red.setG(1);
    green.setG(2);
    blue.setG(3);
    assertEquals(red.getG(), 1);
    assertEquals(green.getG(), 2);
    assertEquals(blue.getG(), 3);

    // setB
    red.setB(1);
    green.setB(2);
    blue.setB(3);
    assertEquals(red.getB(), 1);
    assertEquals(green.getB(), 2);
    assertEquals(blue.getB(), 3);

    // setRGB
    red.setRGB(128, 128, 128);
    assertEquals(red.getR(), 128);
    assertEquals(red.getG(), 128);
    assertEquals(red.getB(), 128);
    blue.setRGB(-255, -1, 0);
    assertEquals(blue.getR(), 0);
    assertEquals(blue.getG(), 0);
    assertEquals(blue.getB(), 0);
    green.setRGB(510, 256, 255);
    assertEquals(green.getR(), 255);
    assertEquals(green.getG(), 255);
    assertEquals(green.getB(), 255);
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
