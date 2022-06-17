import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageUtil;
import model.filter.FilterImpl;
import model.picture.IPicture;

import static org.junit.Assert.assertEquals;

public class FilterImplTest {
  private IPicture smallImage;
  private double[][] filterZero;
  private double[][] filterHalf;
  private double[][] filterOne;

  @Before
  public void setUp() {
    try {
      smallImage = ImageUtil.readFile("src/pictures/smallImage/smallImage.ppm");
      filterZero = new double[][] {
              {0, 0, 0},
              {0, 0, 0},
              {0, 0, 0}};
      filterHalf = new double[][] {
              {0.5, 0.5, 0.5},
              {0.5, 0.5, 0.5},
              {0.5, 0.5, 0.5}};
      filterOne = new double[][] {
              {1}};
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

  @Test
  public void testApply() throws IOException {

    IPicture smallImageZeroFilter = new FilterImpl().apply(smallImage, filterZero);
    IPicture smallImageZeroFilterExpect =
            ImageUtil.readFile("src/pictures/smallImage/smallImage-zero-filter.ppm");
    assertCompare(smallImageZeroFilter, smallImageZeroFilterExpect);

    IPicture smallImageHalfFilter = new FilterImpl().apply(smallImage, filterHalf);
    IPicture smallImageHalfFilterExpect =
            ImageUtil.readFile("src/pictures/smallImage/smallImage-half-filter.ppm");
    assertCompare(smallImageHalfFilter, smallImageHalfFilterExpect);

    IPicture smallImageOneFilter = new FilterImpl().apply(smallImage, filterOne);
    IPicture smallImageOneFilterExpect =
            ImageUtil.readFile("src/pictures/smallImage/smallImage-one-filter.ppm");
    assertCompare(smallImageOneFilter, smallImageOneFilterExpect);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidApply1() {
    IPicture smallImageFilterFail = new FilterImpl().apply(smallImage,
            new double[][] {
            {1, 1},
            {1, 1}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidApply2() {

    IPicture smallImageFilterFail = new FilterImpl().apply(smallImage,
            new double[][] {
                    {1, 1, 1},
                    {1, 1, 1}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidApply3() {

    IPicture smallImageFilterFail = new FilterImpl().apply(smallImage,
            new double[][] {
                    {1, 1},
                    {1, 1},
                    {1, 1}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidApply4() {

    IPicture smallImageFilterFail = new FilterImpl().apply(smallImage,
            new double[][] {
                    {1, 1, 1},
                    {1, 1},
                    {1, 1, 1}});
  }
}
