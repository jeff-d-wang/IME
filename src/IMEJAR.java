import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.IPictureController;
import controller.PictureControllerImpl;
import controller.PictureJFrameController;
import model.picture.IPictureModel;
import model.picture.PictureModel;
import view.PictureJFrameView;
import view.PictureTextView;
import view.IPictureView;

/**
 * This is the main method class to be able to open the program with jar.
 */
public class IMEJAR {

  /**
   * The main method and entry point to the IME program.
   *
   * @param args Arguments
   */
  public static void main(String[] args) {

    IPictureModel model = new PictureModel();
    IPictureView view;
    IPictureController controller;

    if (args.length == 2 && args[0].equals("-file")) {
      InputStream inputFile = new ByteArrayInputStream(args[1].getBytes());
      Readable readable = new InputStreamReader(inputFile);
      view = new PictureTextView(model);
      controller =  new PictureControllerImpl(model, view, readable);
      controller.run(readable);

    } else if (args.length > 1 && args[0].equals("-text")) {
      // interactive text mode
      // java - jar program.jar -text brighten -10 smallImage smallImage-darken-by-10
      String userInput = new String();
      for (int i = 1; i < args.length; i++) {
        userInput += args[i];
      }
      InputStream targetStream = new ByteArrayInputStream(userInput.getBytes());
      Readable readable = new InputStreamReader(targetStream);
      view = new PictureTextView(model);
      controller = new PictureControllerImpl(model, view, readable);
      controller.run();
    } else {
      PictureJFrameView graphicalView = new PictureJFrameView(model);
      controller = new PictureJFrameController(model, graphicalView);
      controller.run();
    }
  }
}
