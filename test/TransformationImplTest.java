import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageUtil;
import model.picture.IPicture;
import model.transformation.TransformationImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for transformation impl class.
 */
public class TransformationImplTest {
  private IPicture smallImage;
  private double[][] transformHalf;

  @Before
  public void setUp() {
    try {
      smallImage = ImageUtil.readFile("res/smallImage/smallImage.ppm");
      transformHalf = new double[][] {
              {0.5, 0.5, 0.5},
              {0.5, 0.5, 0.5},
              {0.5, 0.5, 0.5}};
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

    IPicture smallImageHalfTransform = new TransformationImpl().apply(smallImage, transformHalf);
    IPicture smallImageHalfTransformExpect =
            ImageUtil.readFile("res/smallImage/smallImage-half-transform.ppm");
    assertCompare(smallImageHalfTransform, smallImageHalfTransformExpect);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidApply1() {
    IPicture smallImageTransformFail = new TransformationImpl().apply(smallImage,
            new double[][] {
                    {1, 1},
                    {1, 1}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidApply2() {

    IPicture smallImageTransformFail = new TransformationImpl().apply(smallImage,
            new double[][] {
                    {1, 1, 1, 1},
                    {1, 1, 1, 1}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidApply3() {

    IPicture smallImageTransformFail = new TransformationImpl().apply(smallImage,
            new double[][] {
                    {1, 1},
                    {1, 1},
                    {1, 1}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidApply4() {

    IPicture smallImageTransformFail = new TransformationImpl().apply(smallImage,
            new double[][] {
                    {1, 1, 1},
                    {1, 1},
                    {1, 1, 1}});
  }
}
