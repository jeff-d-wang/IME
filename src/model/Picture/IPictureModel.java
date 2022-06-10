package model.Picture;

/**
 * Interface for a PictureModel class that branches all types of collections of Pictures.
 */
public interface IPictureModel {
  /**
   * Puts a given picture value to a new key name in pictures.
   * @param name      Name of the picture
   * @param picture   Picture to be saved in pictures
   * @throws IllegalArgumentException if given a null name or picture.
   */
  public void putPicture(String name, IPicture picture) throws IllegalArgumentException;

  /**
   * Returns the picture value of a given key name.
   * @param name   Name of the picture
   * @return the picture value of a given key name
   * @throws IllegalArgumentException if the given name is null
   */
  public IPicture getPicture(String name) throws IllegalArgumentException;
}
