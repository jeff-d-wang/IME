package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import model.ImageUtil;
import model.picture.IPictureModel;
import view.PictureView;

/**
 * Implementation of a PictureController interface.
 */
public class PictureControllerImpl implements PictureController {

  private IPictureModel model;
  private PictureView view;
  private final Readable readable;
  private final Map<String, Runnable> runnables;
  private String[] script;
  private String[] command;

  /**
   * Basic constructor for the controller.
   * @param model      Model object
   * @param view       View object to display view-related content
   * @param readable   The Readable object for inputs
   * @throws IllegalArgumentException if the given Readable is null
   */

  public PictureControllerImpl(IPictureModel model, PictureView view, Readable readable)
          throws IllegalArgumentException {
    if (readable == null) {
      throw new IllegalArgumentException("Given null readable object.");
    }

    this.model = model;
    this.view = view;
    this.readable = readable;
    this.runnables = new HashMap<>();
    this.script = new String[4];
    this.command = new String[2];
    this.runnables.put("load", new Load());
    this.runnables.put("save", new Save());
    this.runnables.put("component", new Component());
    this.runnables.put("flip", new Flip());
    this.runnables.put("brighten", new Brighten());
    this.runnables.put("blur", new Blur());
    this.runnables.put("sharpen", new Sharpen());
    this.runnables.put("greyscale", new Greyscale());
    this.runnables.put("sepia", new Sepia());
    this.runnables.put("file", new File());
  }

  /**
   * Sends this controller's view to render a given message.
   * @param message to be rendered.
   * @throws IllegalStateException if the given message could not be rendered
   */
  private void message(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Error: " + e.getMessage() + System.lineSeparator());
    }
  }

  @Override
  public void run() throws IllegalStateException {
    run(this.readable);
  }

  @Override
  public void run(Readable readable) throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    String function = "";

    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      script = line.split(" ", 4);
      script[0] = script[0].toLowerCase();
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

      if (runnables.containsKey(function)) {
        runnables.get(function).run();
      } else {
        message("Invalid function." + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the load function.
   */
  private class Load implements Runnable {

    /**
     * Run function for the Runnable Load class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {
      try {
        model.putPicture(script[2], ImageUtil.readFile(script[1]));
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the save function.
   */
  private class Save implements Runnable {

    /**
     * Run function for the Runnable Save class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {
      try {
        ImageUtil.writeFile(model.getPicture(script[2]), script[1]);
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the greyscale component function.
   */
  private class Component implements Runnable {

    /**
     * Run function for the Runnable Component class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {

      try {
        model.putPicture(script[2], model.getPicture(script[1]).component(command[0]));
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the flip function.
   */
  private class Flip implements Runnable {

    /**
     * Run function for the Runnable Flip class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {

      try {
        model.putPicture(script[2], model.getPicture(script[1]).flip(command[0]));
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the brighten function.
   */
  private class Brighten implements Runnable {

    /**
     * Run function for the Runnable Brighten class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {
      int increment = 0;
      try {
        increment = Integer.parseInt(script[1]);
        model.putPicture(script[3], model.getPicture(script[2]).brighten(increment));
      } catch (NumberFormatException e) {
        message("Invalid increment input. " + e.getMessage() + System.lineSeparator());
      } catch (IndexOutOfBoundsException e) {
        message("Invalid line input. " + e.getMessage() + System.lineSeparator());
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the blur function.
   */
  private class Blur implements Runnable {

    /**
     * Run function for the Runnable Blur class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {
      try {
        model.putPicture(script[2], model.getPicture(script[1]).blur());
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the sharpen function.
   */
  private class Sharpen implements Runnable {

    /**
     * Run function for the Runnable Sharpen class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {
      try {
        model.putPicture(script[2], model.getPicture(script[1]).sharpen());
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the greyscale function.
   */
  private class Greyscale implements Runnable {

    /**
     * Run function for the Runnable Greyscale class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {
      try {
        model.putPicture(script[2], model.getPicture(script[1]).greyscale());
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the sepia function.
   */
  private class Sepia implements Runnable {

    /**
     * Run function for the Runnable Sepia class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {
      try {
        model.putPicture(script[2], model.getPicture(script[1]).sepia());
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  /**
   * Runnable class for the file function.
   */
  private class File implements Runnable {

    /**
     * Run function for the Runnable File class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {
      try {
        PictureControllerImpl.this.run(new FileReader(script[1]));
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }
}
