package controller;

import java.io.IOException;
import java.util.Scanner;

import model.picture.IPictureModel;
import view.PictureView;

/**
 * A Mock class for PictureControllerImpl to see if it receives inputs correctly.
 */
public class PictureControllerImplMock implements IPictureController {

  private PictureView view;
  private final Readable readable;
  private String[] script;
  private String[] command;

  /**
   * Basic constructor for PictureControllerImplMock.
   * @param model      Model object to interact with
   * @param view       View object that displays view related objects
   * @param readable   Readable object
   * @throws IllegalArgumentException if the null is empty
   */
  public PictureControllerImplMock(IPictureModel model, PictureView view, Readable readable)
          throws IllegalArgumentException {
    if (readable == null) {
      throw new IllegalArgumentException("Given null readable object.");
    }

    this.view = view;
    this.readable = readable;
    this.script = new String[4];
    this.command = new String[2];
  }

  @Override
  public void run() throws IllegalStateException {
    run(this.readable);
  }

  @Override
  public void run(Readable readable) throws IllegalStateException {
    Scanner sc = new Scanner(readable);

    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      script = line.split(" ", 4);
      script[0].toLowerCase();
      command = script[0].split("-", 2);

      try {
        for (int i = 0; i < script.length; i++) {
          view.renderMessage(script[i] + System.lineSeparator());
        }
        for (int i = 0; i < command.length; i++) {
          view.renderMessage(command[i] + System.lineSeparator());
        }
      } catch (IOException e) {
        throw new IllegalStateException("Error: " + e.getMessage() + System.lineSeparator());
      }
    }
  }
}
