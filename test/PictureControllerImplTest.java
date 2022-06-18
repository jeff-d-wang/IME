import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import controller.IPictureController;
import controller.PictureControllerImpl;
import controller.PictureControllerImplMock;
import model.ImageUtil;
import model.picture.IPicture;
import model.picture.PictureModel;
import model.picture.PictureImpl;
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
  private IPictureController controller;

  @Before
  public void setUp() {
    model = new PictureModel();
    view = new PictureTextView(model);
  }

  private void test(ByteArrayInputStream in, ByteArrayOutputStream out) {
    rd = new InputStreamReader(in);
    bytes = out;
    ap = new PrintStream(out);
  }

  private void assertCompare(IPicture result, IPicture expect) {
    for (int r = 0; r < result.getHeight(); r++) {
      for (int c = 0; c < result.getWidth(); c++) {
        assertEquals(result.getPixel(r, c), expect.getPixel(r, c));
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    IPictureController controllerFail1 = new PictureControllerImpl(null, view, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    IPictureController controllerFail2 = new PictureControllerImpl(model, null, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    IPictureController controllerFail3 = new PictureControllerImpl(model, view, null);
  }

  @Test
  public void testInputs() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm "
            + "smallImage").getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    IPictureController controllerMock = new PictureControllerImplMock(model, view, rd);
    controllerMock.run();

    assertEquals("load\n"
            + "res/smallImage/smallImage.ppm\n"
            + "smallImage\n"
            + "load\n", bytes.toString());
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testLoad() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm "
            + "smallImage").getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare((PictureImpl) model.getPicture("smallImage"),
            ImageUtil.readFile("res/smallImage/smallImage.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testSave() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm smallImage "
                    + "\nsave smallImage res/smallImage/result/default.ppm").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(ImageUtil.readFile("res/smallImage/result/default.ppm"),
            ImageUtil.readFile("res/smallImage/smallImage.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testRedGreyscale() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm "
                    + "smallImage \nred-component smallImage smallImageRedGreyscale").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImageRedGreyscale"),
            ImageUtil.readFile("res/smallImage/smallImage-red-component.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testGreenGreyscale() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm smallImage "
                    + "\ngreen-component smallImage smallImageGreenGreyscale").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImageGreenGreyscale"),
            ImageUtil.readFile("res/smallImage/smallImage-green-component.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testBlueGreyscale() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm "
                    + "smallImage \nblue-component smallImage smallImageBlueGreyscale").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImageBlueGreyscale"),
            ImageUtil.readFile("res/smallImage/smallImage-blue-component.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testFlip() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm "
                    + "smallImage \nvertical-flip smallImage smallImageVerticalFlip").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImageVerticalFlip"),
            ImageUtil.readFile("res/smallImage/smallImage-vertical.ppm"));
  }


  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testBrighten() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm "
                    + "smallImage \nbrighten 10 smallImage smallImageBrighten10").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImageBrighten10"),
            ImageUtil.readFile("res/smallImage/smallImage-brighten-by-10.ppm"));
  }

  @Test
  public void testInvalidBrighten1() {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm smallImage "
                    + "\nbrighten string smallImage smallImageBrighten10").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("Invalid increment input. For input string: \"string\"", message);
  }

  @Test
  public void testInvalidBrighten2() {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm smallImage "
            + "\nbrighten 10 smallImage").getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("Invalid line input. Index 3 out of bounds for length 3", message);
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testBlur() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm smallImage "
                    + "\nblur smallImage smallImageBlur").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImageBlur"),
            ImageUtil.readFile("res/smallImage/smallImage-blur.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testSharpen() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm smallImage "
                    + "\nsharpen smallImage smallImageSharpen").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImageSharpen"),
            ImageUtil.readFile("res/smallImage/smallImage-sharpen.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testGreyscale() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm smallImage "
                    + "\ngreyscale smallImage smallImageGreyscale").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImageGreyscale"),
            ImageUtil.readFile("res/smallImage/smallImage-greyscale.ppm"));
  }

  // all actual commands are tested with valid & invalid arguments in PictureImplTest.java
  @Test
  public void testSepia() throws IOException {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm smallImage "
                    + "\nsepia smallImage smallImageSepia").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImageSepia"),
            ImageUtil.readFile("res/smallImage/smallImage-sepia.ppm"));
  }

  // This tests if the controller can handle the new -file command and load in a simple script txt
  // file. In it, it only loads in a picture, but we verify that the program has successfully loaded
  // the picture, which it has.
  @Test
  public void testFile() throws IOException {
    test(new ByteArrayInputStream(("-file res/test-script.txt").getBytes()),
            new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    assertCompare(model.getPicture("smallImage-zero-filter"),
            ImageUtil.readFile("res/smallImage/smallImage-zero-filter.ppm"));
  }

  // Always listens to new commands after
  @Test
  public void testTooLittleInput() {
    test(new ByteArrayInputStream(("load res/smallImage/smallImage.ppm smallImage "
            + "\nsave smallImage").getBytes()), new ByteArrayOutputStream());
    view = new PictureTextView(model, ap);
    controller = new PictureControllerImpl(model, view, rd);
    controller.run();

    String[] split = bytes.toString().split("\n", 5);
    String message = split[0];

    assertEquals("Could not execute function. Index 2 out of bounds for length 2", message);
  }

  // Invalid functions or just random inputs should just tell the user that the function they were
  // trying to use was invalid but that they're still listening for a new command.
  @Test
  public void testNonsenseCommand() {
    test(new ByteArrayInputStream("nonsense smallImage smallImageNonsense".getBytes()),
            new ByteArrayOutputStream());
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
  I DID NOT MAKE ANY MOCKS FOR EACH COMMAND BECAUSE WE ALSO ASSERTEQUALS A PICTURE EQUIVALENT TO
  WHAT SHOULD HAPPEN. WE ALSO TEST ALL POSSIBLE INPUTS TO EACH FUNCTION IN A PICTURE CLASS.
  THEREFORE, WE KNOW THAT WE ARE TRUTHFULLY SENDING THE INTENDED STRING TO THE MODEL, PICTURE, etc.
   */
}
