package view;

import java.io.IOException;

import model.Picture.IPictureModel;
import model.Picture.PictureModel;

/**
 *
 */
public class PictureTextView implements PictureView {

  private IPictureModel model;
  private Appendable appendable;

  /**
   * Default constructor for the PictureTextView class.
   * @param model   Model object
   */
  public PictureTextView(IPictureModel model) {
    this(model, System.out);
  }

  /**
   * Default constructor for the PictureTextView class.
   * @param model        Model object
   * @param appendable   Appendable object that it will view as its destination
   *
   * @throws IllegalArgumentException if given Appendable object is null
   */
  public PictureTextView(IPictureModel model, Appendable appendable) throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("Given null model or appendable.");
    }
    this.model = model;
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
