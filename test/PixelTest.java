import org.junit.Before;
import org.junit.Test;

import model.Pixel.IPixel;
import model.Pixel.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for pixel.
 */
public class PixelTest {
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
  }

  @Test
  public void testGetRGB() {
    // getR
    assertEquals(black.getR(), 0);
    assertEquals(red.getR(), 255);
    assertEquals(blue.getR(), 0);
    assertEquals(green.getR(), 0);
    assertEquals(white.getR(), 255);

    // getG
    assertEquals(black.getG(), 0);
    assertEquals(red.getG(), 0);
    assertEquals(blue.getG(), 0);
    assertEquals(green.getG(), 255);
    assertEquals(white.getG(), 255);

    // getB
    assertEquals(black.getB(), 0);
    assertEquals(red.getB(), 0);
    assertEquals(blue.getB(), 255);
    assertEquals(green.getB(), 0);
    assertEquals(white.getB(), 255);
  }

  @Test
  public void testSetRGB() {
    // setR
    black.setR(12);
    red.setR(240);
    assertEquals(black.getR(), 12);
    assertEquals(red.getR(), 240);

    // setG
    black.setG(12);
    red.setG(240);
    assertEquals(black.getG(), 12);
    assertEquals(red.getG(), 240);

    // setB
    black.setB(12);
    red.setB(240);
    assertEquals(black.getB(), 12);
    assertEquals(red.getB(), 240);

    // setRGB
    blue.setRGB(1, 2, 3);
    assertEquals(blue.getR(), 1);
    assertEquals(blue.getG(), 2);
    assertEquals(blue.getB(), 3);
    green.setRGB(13, 15, 18);
    assertEquals(green.getR(), 13);
    assertEquals(green.getG(), 15);
    assertEquals(green.getB(), 18);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentSetRGB() {
    // red less than 0
    white.setRGB(-1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentSetRGB2() {
    // green less than 0
    white.setRGB(0, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentSetRGB3() {
    // blue less than 0
    white.setRGB(0, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentSetRGB4() {
    // red greater than 255
    white.setRGB(800, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentSetRGB5() {
    // green greater than 255
    white.setRGB(0, 800, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArgumentSetRGB6() {
    // blue greater than 255
    white.setRGB(0, 0, 800);
  }

  @Test
  public void testGetValue() {
    assertEquals(black.getValue(), 0);
    assertEquals(red.getValue(), 255);
    assertEquals(green.getValue(), 255);
    assertEquals(blue.getValue(), 255);
    assertEquals(white.getValue(), 255);
  }

  @Test
  public void testGetIntensity() {
    assertEquals(black.getIntensity(), 0);
    assertEquals(red.getIntensity(), 85);
    assertEquals(green.getIntensity(), 85);
    assertEquals(blue.getIntensity(), 85);
    assertEquals(white.getIntensity(), 255);
  }

  @Test
  public void testGetLuma() {
    assertEquals(black.getLuma(), 0);
    assertEquals(red.getLuma(), 54);
    assertEquals(green.getLuma(), 182);
    assertEquals(blue.getLuma(), 18);
    assertEquals(white.getLuma(), 254);
  }

  @Test
  public void testConstructor() {
    IPixel p1 = new RGBPixel(1, 2, 256);
    assertEquals(p1.getB(), 255);
    IPixel p2 = new RGBPixel(-1, 2, 256);
    assertEquals(p2.getR(), 0);
  }
}