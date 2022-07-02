package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import model.picture.IPictureModel;
import view.IPictureView;

/**
 * Implementation of a IPictureController interface.
 */
public class PictureControllerImpl extends FeaturesImpl implements IPictureController {
  private IPictureView view;
  private final Readable readable;
  private final Map<String, Runnable> runnables;
  private String[] script;
  private String[] command;

  /**
   * Basic constructor for the controller.
   *
   * @param model    Model object
   * @param view     View object to display view-related content
   * @param readable The Readable object for inputs
   * @throws IllegalStateException if the given Readable is null
   */

  public PictureControllerImpl(IPictureModel model, IPictureView view, Readable readable)
          throws IllegalStateException {
    super(model);

    if (readable == null) {
      throw new IllegalArgumentException("Given null readable object.");
    }

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
    this.runnables.put("partialimage", new PartialImageManipulation());

  }

  /**
   * Sends this controller's view to render a given message.
   *
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

      if (script.length == 5) {
        runnables.get("partialimage").run();
      } else if (runnables.containsKey(function)) {
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
        load(script[1], script[2]);
      } catch (IndexOutOfBoundsException e) {
        message("Missing inputs. " + System.lineSeparator());
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
        save(model.getPicture(script[2]), script[1]);
      } catch (IndexOutOfBoundsException e) {
        message("Missing inputs. " + System.lineSeparator());
      } catch (NullPointerException e) {
        message("Image " + script[2] + " does not exist. " + System.lineSeparator());
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  @Override
  public void file(String filename) throws FileNotFoundException {
    PictureControllerImpl.this.run(new FileReader(filename));
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
        file(script[1]);
      } catch (FileNotFoundException e) {
        message("File could not be found. " + System.lineSeparator());
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
        component(command[0], model.getPicture(script[1]), script[2]);
      } catch (IndexOutOfBoundsException e) {
        message("Missing inputs. " + System.lineSeparator());
      } catch (NullPointerException e) {
        message("Image " + script[2] + " does not exist. " + System.lineSeparator());
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
        flip(command[0], model.getPicture(script[1]), script[2]);
      } catch (IndexOutOfBoundsException e) {
        message("Missing inputs. " + System.lineSeparator());
      } catch (NullPointerException e) {
        message("Image " + script[2] + " does not exist. " + System.lineSeparator());
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
        brighten(increment, model.getPicture(script[1]), script[2]);
      } catch (NumberFormatException e) {
        message("Invalid increment input. " + e.getMessage() + System.lineSeparator());
      } catch (IndexOutOfBoundsException e) {
        message("Missing inputs. " + System.lineSeparator());
      } catch (NullPointerException e) {
        message("Image " + script[2] + " does not exist. " + System.lineSeparator());
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
        blur(model.getPicture(script[1]), script[2]);
      } catch (IndexOutOfBoundsException e) {
        message("Missing inputs. " + System.lineSeparator());
      } catch (NullPointerException e) {
        message("Image " + script[2] + " does not exist. " + System.lineSeparator());
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
        sharpen(model.getPicture(script[1]), script[2]);
      } catch (IndexOutOfBoundsException e) {
        message("Missing inputs. " + System.lineSeparator());
      } catch (NullPointerException e) {
        message("Image " + script[2] + " does not exist. " + System.lineSeparator());
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
        greyscale(model.getPicture(script[1]), script[2]);
      } catch (IndexOutOfBoundsException e) {
        message("Missing inputs. " + System.lineSeparator());
      } catch (NullPointerException e) {
        message("Image " + script[2] + " does not exist. " + System.lineSeparator());
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
        sepia(model.getPicture(script[1]), script[2]);
      } catch (IndexOutOfBoundsException e) {
        message("Missing inputs. " + System.lineSeparator());
      } catch (NullPointerException e) {
        message("Image " + script[2] + " does not exist. " + System.lineSeparator());
      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

  private class PartialImageManipulation implements Runnable {
    /**
     * Run function for the Runnable PartialImageManipulation class.
     *
     * @throws IllegalStateException if it could not properly render a message.
     */
    public void run() throws IllegalStateException {
      try {
        partialImage(model.getPicture(script[1]), model.getPicture(script[2]), command[0],
                script[3]);
        // brighten 10 smallImage mask.ppm smallImage-partialBrighten

      } catch (Exception e) {
        message("Could not execute function. " + e.getMessage() + System.lineSeparator());
      }
    }
  }

}
