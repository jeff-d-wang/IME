package controller;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import model.ImageUtil;
import model.Picture.IPictureModel;
import view.PictureView;

/**
 * Implementation of a PictureController interface.
 */
public class PictureControllerImpl implements PictureController {

  private IPictureModel model;
  private PictureView view;
  private final Readable readable;
  private Map<String, Runnable> runnables;
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
    this.runnables.put("component", new Greyscale());
    this.runnables.put("flip", new Flip());
    this.runnables.put("brighten", new Brighten());
  }

  @Override
  public void run() throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    String function = "";

    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      System.out.println(line);
      script = line.split(" ", 4);
      script[0].toLowerCase();
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

      // added in case we implement longer expressions
      if (script.length != 3 || (function.equals("brighten") && script.length != 4)) {
        try {
          view.renderMessage("Invalid line input." + System.lineSeparator());
        } catch (IOException e) {
          throw new IllegalStateException("Error: " + e.getMessage() + System.lineSeparator());
        }
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
    public void run() throws IllegalStateException {
      try {
        model.putPicture(script[2], ImageUtil.readPPM(script[1]));
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
    public void run() throws IllegalStateException {
      try {
        ImageUtil.writeFile(model.getPicture(script[1]), script[2]);
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
    public void run() throws IllegalStateException {

      try {
        System.out.println(command[0] + " greyscaling " + script[1] + " to " + script[2]);
        model.putPicture(script[2], model.getPicture(script[1]).greyscale(command[0]));
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
    public void run() throws IllegalStateException {

      try {
        model.putPicture(script[2], model.getPicture(script[1]).flip(command[0]));
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
    public void run() throws IllegalStateException {
      int increment = 0;
      try {
        increment = Integer.parseInt(script[1]);
        System.out.println("Brightening " + script[2] + " by " + increment + " to " + script[3]);
        model.putPicture(script[3], model.getPicture(script[2]).brighten(increment));
      } catch (NumberFormatException e) {
        try {
          view.renderMessage("Invalid increment input. "
                  + e.getMessage()
                  + System.lineSeparator());
        } catch (IOException f) {
          throw new IllegalStateException("Error: " + f.getMessage() + System.lineSeparator());
        }
      } catch (IndexOutOfBoundsException e) {
        try {
          view.renderMessage("Invalid line input. "
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
