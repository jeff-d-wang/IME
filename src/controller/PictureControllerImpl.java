package controller;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import model.ImageUtil;
import model.Picture.IPicture;
import model.Picture.PPMPicture;
import view.PictureTextView;

public class PictureControllerImpl implements PictureController {

  private PictureTextView view;
  private final Readable readable;
  private Map<String, IPicture> pictures;
  private Map<String, Runnable> runnables;
  private String[] script;
  private String[] command;

  /**
   * Basic constructor for the controller.
   * @param readable   The Readable object for inputs
   * @throws IllegalArgumentException if the given Readable is null
   */
  public PictureControllerImpl(Readable readable) throws IllegalArgumentException {
    if (readable == null) {
      throw new IllegalArgumentException("Given null readable object.");
    }

    this.readable = readable;
    this.pictures = new HashMap<>();
    this.runnables = new HashMap<>();
    this.script = new String[4];
    this.command = new String[2];
    this.runnables.put("load", new Load());
    this.runnables.put("save", new Save());
    this.runnables.put("component", new Greyscale());
    this.runnables.put("flip", new Flip());
    this.runnables.put("brighten", new Brighten());
  }

  @Override
  public void run() throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    String function = "";

    while (sc.hasNextLine()) {
      script = sc.nextLine().split(" ", 4);
      command = script[0].split("-", 2);

      switch (command.length) {
        case 1:
          function = command[0];
          break;
        case 2:
          function = command[1];
          break;
        default:
          break;
      }

      if (script.length < 3 || script.length > 4) {
        throw new IllegalStateException("Invalid line input.");
      }

      if (runnables.containsKey(function)) {
        runnables.get(function).run();
      } else {
        try {
          view.renderMessage("Invalid function." + System.lineSeparator());
        } catch (IOException e) {
          throw new IllegalStateException("Error: " + e.getMessage() + System.lineSeparator());
        }
      }
    }
  }

  /**
   * Runnable class for the load function.
   */
  private class Load implements Runnable {
    public void run() {
      try {
        pictures.put(script[2], ImageUtil.readPPM(script[1]));
      } catch (Exception e) {
        try {
          view.renderMessage("Could not execute function. "
                  + e.getMessage()
                  + System.lineSeparator());
        } catch (IOException f) {
          throw new IllegalStateException("Error: " + f.getMessage() + System.lineSeparator());
        }
      }
    }
  }

  /**
   * Runnable class for the save function.
   */
  private class Save implements Runnable {
    public void run() {
      try {
        ImageUtil.writePPM((PPMPicture) pictures.get(script[2]), script[1]);
      } catch (Exception e) {
        try {
          view.renderMessage("Could not execute function. "
                  + e.getMessage()
                  + System.lineSeparator());
        } catch (IOException f) {
          throw new IllegalStateException("Error: " + f.getMessage() + System.lineSeparator());
        }
      }
    }
  }

  /**
   * Runnable class for the greyscale function.
   */
  private class Greyscale implements Runnable {
    public void run() {

      try {
        pictures.put(script[2], pictures.get(script[1]).greyscale(command[1]));
      } catch (Exception e) {
        try {
          view.renderMessage("Could not execute function. "
                  + e.getMessage()
                  + System.lineSeparator());
        } catch (IOException f) {
          throw new IllegalStateException("Error: " + f.getMessage() + System.lineSeparator());
        }
      }
    }
  }

  /**
   * Runnable class for the flip function.
   */
  private class Flip implements Runnable {
    public void run() {

      try {
        pictures.put(script[2], pictures.get(script[1]).flip(command[1]));
      } catch (Exception e) {
        try {
          view.renderMessage("Could not execute function. "
                  + e.getMessage()
                  + System.lineSeparator());
        } catch (IOException f) {
          throw new IllegalStateException("Error: " + f.getMessage() + System.lineSeparator());
        }
      }
    }
  }

  /**
   * Runnable class for the brighten function.
   */
  private class Brighten implements Runnable {
    public void run() {
      int increment = 0;
      try {
        increment = Integer.parseInt(script[1]);
        pictures.put(script[2], pictures.get(script[1]).brighten(increment));
      } catch (NumberFormatException e) {
        try {
          view.renderMessage("Invalid increment input."
                  + e.getMessage()
                  + System.lineSeparator());
        } catch (IOException f) {
          throw new IllegalStateException("Error: " + f.getMessage() + System.lineSeparator());
        }
      } catch (Exception e) {
        try {
          view.renderMessage("Could not execute function. "
                  + e.getMessage()
                  + System.lineSeparator());
        } catch (IOException f) {
          throw new IllegalStateException("Error: " + f.getMessage() + System.lineSeparator());
        }
      }
    }
  }
}
