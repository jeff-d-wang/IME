import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import model.ImageUtil;
import model.picture.IPicture;
import model.picture.PictureModel;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for the PictureModel class.
 */
public class PictureModelTest {
  private PictureModel model1;

  @Before
  public void setUp() {
    model1 = new PictureModel();
    PictureModel model2 = new PictureModel(new HashMap<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    PictureModel modelFail = new PictureModel(null);
  }

  @Test
  public void testPutAndGetPicture() throws IOException {
    IPicture filePicture1
            = ImageUtil.readFile("res/smallImage/smallImage.ppm");
    IPicture filePicture2
            = ImageUtil.readFile("res/smallImage/smallImage-horizontal.ppm");
    IPicture filePicture3
            = ImageUtil.readFile("res/smallImage/smallImage-value-component.ppm");

    model1.putPicture("smallImage1", filePicture1);
    model1.putPicture("smallImage2", filePicture2);
    model1.putPicture("smallImage3", filePicture3);

    for (int r = 0; r < model1.getPicture("smallImage1").getHeight(); r++) {
      for (int c = 0; c < model1.getPicture("smallImage1").getWidth(); c++) {
        assertEquals(model1.getPicture("smallImage1").getPixel(r, c),
                filePicture1.getPixel(r, c));
      }
    }

    for (int r = 0; r < model1.getPicture("smallImage2").getHeight(); r++) {
      for (int c = 0; c < model1.getPicture("smallImage2").getWidth(); c++) {
        assertEquals(model1.getPicture("smallImage2").getPixel(r, c),
                filePicture2.getPixel(r, c));
      }
    }

    for (int r = 0; r < model1.getPicture("smallImage3").getHeight(); r++) {
      for (int c = 0; c < model1.getPicture("smallImage3").getWidth(); c++) {
        assertEquals(model1.getPicture("smallImage3").getPixel(r, c),
                filePicture3.getPixel(r, c));
      }
    }


    assertEquals(model1.getPicture("nonexistantPicture"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPutPicture() throws IOException {
    IPicture filePicture = ImageUtil.readFile("res/smallImage/smallImage.ppm");

    model1.putPicture("smallImage", null);
    model1.putPicture(null, filePicture);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPicture() throws IOException {
    model1.getPicture(null);
  }
}
