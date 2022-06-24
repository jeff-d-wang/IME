package controller;

import java.io.IOException;

import model.ImageUtil;
import model.picture.IPicture;
import model.picture.IPictureModel;

public abstract class FeaturesImpl implements IFeatures {

  protected IPictureModel model;

  /**
   * Basic constructor for FeaturesImpl.
   * @param model   Model object
   */
  public FeaturesImpl(IPictureModel model) {
    this.model = model;
  }

  @Override
  public void load(String filename, String name) throws IOException {
    model.putPicture(name, ImageUtil.readFile(filename));
  }

  @Override
  public void save(IPicture picture, String filename) throws IOException {
    ImageUtil.writeFile(picture, filename);
  }

  @Override
  public void component(String component, IPicture picture, String name) {
    model.putPicture(name, picture.component(component));
  }

  @Override
  public void flip(String direction, IPicture picture, String name) {
    model.putPicture(name, picture.flip(direction));
  }

  @Override
  public void brighten(int increment, IPicture picture, String name) {
    model.putPicture(name, picture.brighten(increment));
  }

  @Override
  public void blur(IPicture picture, String name) {
    model.putPicture(name, picture.blur());
  }

  @Override
  public void sharpen(IPicture picture, String name) {
    model.putPicture(name, picture.sharpen());
  }

  @Override
  public void greyscale(IPicture picture, String name) {
    model.putPicture(name, picture.greyscale());
  }

  @Override
  public void sepia(IPicture picture, String name) {
    model.putPicture(name, picture.sepia());
  }
}
