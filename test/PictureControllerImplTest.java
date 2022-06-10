import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import controller.PictureController;
import controller.PictureControllerImpl;
import model.ImageUtil;
import model.Picture.PictureModel;
import model.Picture.RGBPicture;
import view.PictureTextView;
import view.PictureView;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for PictureControllerImpl.
 */
public class PictureControllerImplTest {
  private PictureModel model;
  private PictureView view;
  private Readable rd;
  private Appendable ap;
  private ByteArrayOutputStream bytes;
  private PictureController controller;

  @Before
  public void setUp() {
    model = new PictureModel();
    view = new PictureTextView(model);
  }

  /**
   *
   * @param in
   * @param out
   */
  private void test(ByteArrayInputStream in, ByteArrayOutputStream out) {
    rd = new InputStreamReader(in);
    bytes = out;
    ap = new PrintStream(out);
  }

  /**
   *
   * @param result
   * @param expect
   */
  private void assertCompare(RGBPicture result, RGBPicture expect) {
    for (int r = 0; r < result.getHeight(); r++) {
      for (int c = 0; c < result.getWidth(); c++) {
        assertEquals(result.getPixel(r, c), expect.getPixel(r, c));
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    PictureController controllerFail1 = new PictureControllerImpl(null, view, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    PictureController controllerFail2 = new PictureControllerImpl(model, null, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    PictureController controllerFail3 = new PictureControllerImpl(model, view, null);
  }

  // all actual commands are tested with valid & invalid arguments in PPMPictureTest.java
  @Test
  public void testLoad() throws IOException {
    test(new ByteArrayInputStream(("load src/pictures/smallImage/smallImage.ppm " +
            "smallImage").getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare((RGBPicture) model.getPicture("smallImage"),
            ImageUtil.readPPM("src/pictures/smallImage/smallImage.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PPMPictureTest.java
  @Test
  public void testSave() throws IOException {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nsave smallImage src/pictures/smallImage/result/default.ppm".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(ImageUtil.readPPM("src/pictures/smallImage/result/default.ppm"), ImageUtil.readPPM("src/pictures/smallImage/smallImage.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PPMPictureTest.java
  @Test
  public void testRedGreyscale() throws IOException {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nred-component smallImage smallImageRedGreyscale".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare((RGBPicture) model.getPicture("smallImageRedGreyscale"), ImageUtil.readPPM("src/pictures/smallImage/smallImage-red-greyscale.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PPMPictureTest.java
  @Test
  public void testGreenGreyscale() throws IOException {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \ngreen-component smallImage smallImageGreenGreyscale".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare((RGBPicture) model.getPicture("smallImageGreenGreyscale"), ImageUtil.readPPM("src/pictures/smallImage/smallImage-green-greyscale.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PPMPictureTest.java
  @Test
  public void testBlueGreyscale() throws IOException {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nblue-component smallImage smallImageBlueGreyscale".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare((RGBPicture) model.getPicture("smallImageBlueGreyscale"), ImageUtil.readPPM("src/pictures/smallImage/smallImage-blue-greyscale.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PPMPictureTest.java
  @Test
  public void testFlip() throws IOException {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nvertical-flip smallImage smallImageVerticalFlip".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare((RGBPicture) model.getPicture("smallImageVerticalFlip"), ImageUtil.readPPM("src/pictures/smallImage/smallImage-vertical.ppm"));
  }


  // all actual commands are tested with valid & invalid arguments in PPMPictureTest.java
  @Test
  public void testBrighten() throws IOException {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nbrighten 10 smallImage smallImageBrighten10".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare((RGBPicture) model.getPicture("smallImageBrighten10"), ImageUtil.readPPM("src/pictures/smallImage/smallImage-brighten-by-10.ppm"));
  }

  @Test
  public void testInvalidBrighten1() {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nbrighten string smallImage smallImageBrighten10".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("Invalid increment input. For input string: \"string\"", message);
  }

  @Test
  public void testInvalidBrighten2() {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nbrighten 10 smallImage".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("Invalid line input. Index 3 out of bounds for length 3", message);
  }

  @Test
  public void testInvalidBrighten3() {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nbrighten 10 smallImage smallImageBrighten10 aFifthString".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("Invalid line input.", message);
  }

  @Test
  public void testTooLittleInput() {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nsave smallImage".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("Invalid line input.", message);
  }

  @Test
  public void testTooMuchInput() {
    test(new ByteArrayInputStream("load src/pictures/smallImage/smallImage.ppm smallImage \nsave smallImage src/pictures/smallImage/result/default.ppm aFourthString".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("Invalid line input.", message);
  }

  @Test
  public void testNonsenseCommand() {
    test(new ByteArrayInputStream("nonsense smallImage smallImageNonsense".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("Invalid function.", message);
  }

  // In the event we pass in nothing, we just expect the program to loop back and listen for a new
  // input line.
  @Test
  public void testNoInput() {
    test(new ByteArrayInputStream("".getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("", message);
  }

  /*
  I DID NOT MAKE ANY MOCKS BECAUSE AFTER TESTING EACH COMMAND, WE ALSO ASSERTEQUALS A PICTURE
  EQUIVALENT TO WHAT SHOULD HAPPEN. WE ALSO TEST ALL POSSIBLE INPUTS TO EACH FUNCTION IN A PICTURE
  CLASS. THEREFORE, WE KNOW THAT WE ARE TRUTHFULLY SENDING THE INTENDED STRING TO THE MODEL, PICTURE
  etc.
   */
}
