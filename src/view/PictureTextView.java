package view;

import java.io.IOException;

public class PictureTextView implements PictureView {

  private Appendable appendable;

  /**
   * Default constructor for the PictureTextView class.
   */
  public PictureTextView() {
    this(System.out);
  }

  /**
   * Default constructor for the PictureTextView class.
   *
   * @param appendable   Appendable object that it will view as its destination
   *
   * @throws IllegalArgumentException if given Appendable object is null
   */
  public PictureTextView(Appendable appendable) throws IllegalArgumentException {
    if (appendable == null) {
      throw new IllegalArgumentException("Given null appendable.");
    }
    this.appendable = appendable;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message);
    }
    catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }
}
