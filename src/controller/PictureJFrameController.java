package controller;

import java.io.FileNotFoundException;

import model.ImageUtil;
import model.picture.IPictureModel;
import view.IPictureView;
import view.PictureJFrameView;

public class PictureJFrameController extends FeaturesImpl implements IPictureController {

  private IPictureView view;

  /**
   * Basic constructor for PictureJFrameController.
   *
   * @param model Model object
   */
  public PictureJFrameController(IPictureModel model, IPictureView view) {
    super(model);
    this.view = view;
  }

  @Override
  public void file(String filename) throws FileNotFoundException {

  }

  @Override
  public void run() throws IllegalStateException {

  }

  @Override
  public void run(Readable readable) throws IllegalStateException {

  }
}
