package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.picture.IPicture;

public interface IFeatures {

  /**
   * Load feature function for the controller.
   * @param filename   Name of the filepath to be loaded
   * @param name       Name of the picture to be saved
   * @throws IOException if it encounters an error reading/accessing from the file
   */
  void load(String filename, String name) throws IOException;

  /**
   * Save feature function for the controller.
   * @param picture    IPicture to be saved onto
   * @param filename   Name of the filepath to be saved
   * @throws IOException if it encounters an error reading/accessing from the file
   */
  void save(IPicture picture, String filename) throws IOException;

  /**
   * File feature function for the controller.
   * @param filename   Name of the filepath of the text file to be run
   * @throws FileNotFoundException if the file does not exist or comes to an error when doing so
   */
  void file(String filename) throws FileNotFoundException;

  /**
   * Component feature function for the controller.
   * @param component   Component type (red, green, blue, etc.)
   * @param picture     IPicture to be altered
   * @param name        Name of the picture to be saved
   */
  void component(String component, IPicture picture, String name);

  /**
   * Flip feature function for the controller.
   * @param direction   Direction the picture is to be flipped by
   * @param picture     IPicture to be altered
   * @param name        Name of the picture to be saved
   */
  void flip(String direction, IPicture picture, String name);

  /**
   * Brighten feature function for the controller.
   * @param increment   Increment to be brightened by
   * @param picture     IPicture to be altered
   * @param name        Name of the picture to be saved
   */
  void brighten(int increment, IPicture picture, String name);

  /**
   * Blur feature function for the controller.
   * @param picture   IPicture to be altered
   * @param name      Name of the picture to be saved
   */
  void blur(IPicture picture, String name);

  /**
   * Sharpen feature function for the controller.
   * @param picture   IPicture to be altered
   * @param name      Name of the picture to be saved
   */
  void sharpen(IPicture picture, String name);

  /**
   * Greyscale feature function for the controller.
   * @param picture   IPicture to be altered
   * @param name      Name of the picture to be saved
   */
  void greyscale(IPicture picture, String name);

  /**
   * Sepia feature function for the controller.
   * @param picture   IPicture to be altered
   * @param name      Name of the picture to be saved
   */
  void sepia(IPicture picture, String name);
}
