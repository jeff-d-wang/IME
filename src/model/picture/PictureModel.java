package model.picture;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a model for the Picture class. Essentially acts like a picture collection that saves
 * and retrieves pictures for a controller.
 */
public class PictureModel implements IPictureModel {
  private Map<String, IPicture> pictures;

  /**
   * Basic constructor for PictureModel.
   */
  public PictureModel() {
    this(new HashMap<>());
  }

  /**
   * Constructor for PictureModel.
   * @param pictures   Map of pictures to house all future pictures (typically empty)
   */
  public PictureModel(Map<String, IPicture> pictures) {
    if (pictures == null) {
      throw new IllegalArgumentException("Given null pictures Map.");
    }

    this.pictures = pictures;
  }

  @Override
  public void putPicture(String name, IPicture picture) throws IllegalArgumentException {
    if (name == null || picture == null) {
      throw new IllegalArgumentException("Null name or picture.");
    }

    pictures.put(name, picture);
  }

  @Override
  public IPicture getPicture(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Null name.");
    }

    return pictures.get(name);
  }

  @Override
  public String[] pictureNames() {
    return pictures.keySet().toArray(new String[0]);
  }
}
