import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import model.picture.PictureModel;
import view.PictureTextView;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for PictureTextView class.
 */
public class PictureTextViewTest {
  private PictureTextView view;
  private PictureModel model;

  @Before
  public void setUp() {
    this.model = new PictureModel();
    this.view = new PictureTextView(model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    PictureTextView viewFail = new PictureTextView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    PictureTextView viewFail = new PictureTextView(model, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    PictureTextView viewFail = new PictureTextView(null,
            new PrintStream(new ByteArrayOutputStream()));
  }

  @Test
  public void testRenderMessage() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    Appendable ap = new PrintStream(bytes);

    view = new PictureTextView(model, ap);

    try {
      view.renderMessage("Test");
      assertEquals(bytes.toString(), "Test");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
