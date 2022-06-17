package controller;

import java.io.IOException;

/**
 * Interface of a controller for the Picture class.
 */
public interface PictureController {

  /**
   * Method that runs the IME program by accepting user input and parsing it. This passes its own
   * readable object to the run function that asks for a readable to reduce code duplication and
   * allow for client flexibility/convenience.
   *
   * @throws IOException if the program runs into an error reading a file
   */
  public void run() throws IllegalStateException;

  /**
   * Method that runs the IME program by accepting user input and parsing it. This method version
   * accepts a readable given to it, allowing for the multiple sources of input to be valid.
   *
   * @param readable   A readable to extract user inputs from (could be from a txt file)
   * @throws IOException if the program runs into an error reading a file
   */
  public void run(Readable readable) throws IllegalStateException;
}
