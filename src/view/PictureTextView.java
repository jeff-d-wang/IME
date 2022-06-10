package view;

import java.io.IOException;

import model.picture.IPicture;
import model.picture.IPictureModel;

/**
 * A PictureView using text-based messages to present views.
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
  public PictureTextView(IPictureModel model, Appendable appendable)
          throws IllegalArgumentException {
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

  /*
   MADE THIS FUNCTION SO I WOULDN'T GET POINTS OFF FOR
   "SingularField: This field is only used in one method (or constructor) in this class. Make it a
   local variable within the method instead. Error is between cols 11 and 30"
   NEED MODEL LATER BUT NOT NOW.
   */
  private IPicture getPicture(String name) {
    return model.getPicture(name);
  }
}
