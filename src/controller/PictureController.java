package controller;

import java.io.IOException;

/**
 * Interface of a controller for the Picture class.
 */
public interface PictureController {

  /**
   * Method that runs the IME program by accepting user input and parsing it.
   *
   * @throws IOException if the program runs into an error reading a file
   */
  public void run() throws IllegalStateException;
}
