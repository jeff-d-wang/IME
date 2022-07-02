package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.picture.IPicture;
import model.picture.IPictureModel;
import view.PictureJFrameView;

/**
 * This is the PictureJFrameController class. This class support being able to graphically interact
 * with the user.
 */
public class PictureJFrameController extends FeaturesImpl implements IPictureController {

  private PictureJFrameView view;

  /**
   * Basic constructor for PictureJFrameController.
   *
   * @param model Model object
   */
  public PictureJFrameController(IPictureModel model, PictureJFrameView view) {
    super(model);
    this.view = view;
    view.addFeatures(this);
  }

  /**
   * Sends this controller's view to render a given picture.
   * @param name   Name of picture to be rendered
   * @throws IllegalStateException if the given message could not be rendered
   */
  private void picture(String name) throws IllegalStateException {
    try {
      view.renderPicture(name);
    } catch (IOException e) {
      throw new IllegalStateException("Error: " + e.getMessage() + System.lineSeparator());
    }
  }

  @Override
  public void file(String filename) throws FileNotFoundException {
    // This is left empty because we are not required to support interactive scripting through the
    // GUI.
  }

  @Override
  public void run() throws IllegalStateException {
    // This is left empty because a JFrame controller does not need a run function.
  }

  @Override
  public void run(Readable readable) throws IllegalStateException {
    // This is left empty because a JFrame controller does not need a run function.
  }

  @Override
  public void load(String filename, String name) throws IOException {
    super.load(filename, name);
    picture(name);
  }

  @Override
  public void save(IPicture picture, String name) throws IOException {
    super.save(picture, name);
    picture(name);
  }

  @Override
  public void component(String component, IPicture picture, String name) {
    super.component(component, picture, name);
    picture(name);
  }

  @Override
  public void flip(String direction, IPicture picture, String name) {
    super.flip(direction, picture, name);
    picture(name);
  }

  @Override
  public void brighten(int increment, IPicture picture, String name) {
    super.brighten(increment, picture, name);
    picture(name);
  }

  @Override
  public void blur(IPicture picture, String name) {
    super.blur(picture, name);
    picture(name);
  }

  @Override
  public void sharpen(IPicture picture, String name) {
    super.sharpen(picture, name);
    picture(name);
  }

  @Override
  public void greyscale(IPicture picture, String name) {
    super.greyscale(picture, name);
    picture(name);
  }

  @Override
  public void sepia(IPicture picture, String name) {
    super.sepia(picture, name);
    picture(name);
  }
}
