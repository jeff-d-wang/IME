import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.PictureController;
import controller.PictureControllerImpl;
import model.picture.IPictureModel;
import model.picture.PictureModel;
import view.PictureTextView;
import view.PictureView;

/**
 * This is the main method class for IME and entry point to the program.
 */
public class IME {
  /**
   * The main method and entry point to the IME program.
   *
   * @param args Arguments
   */
  public static void main(String[] args) {
    Readable readable = new InputStreamReader(System.in);

    IPictureModel model = new PictureModel();
    PictureView view = new PictureTextView(model);
    PictureController controller = new PictureControllerImpl(model, view, readable);
    controller.run();

    /*
    Script that uses all the methods:
    (IF USING JAR TO RUN, NO NEED TO INCLUDE "res/" IN LOAD FUNCTION)
     load res/smallImage/smallImage.ppm smallImage
     brighten 10 smallImage smallImage-brighter-by-10
     save res/smallImage/smallImage-brighter-by-10.ppm smallImage-brighter-by-10
     brighten -10 smallImage smallImage-darken-by-10
     save res/smallImage/smallImage-darken-by-10.ppm smallImage-darken-by-10
     vertical-flip smallImage smallImage-vertical
     save res/smallImage/smallImage-vertical.ppm smallImage-vertical
     horizontal-flip smallImage smallImage-horizontal
     save res/smallImage/smallImage-horizontal.ppm smallImage-horizontal
     horizontal-flip smallImage-vertical smallImage-vertical-horizontal
     save res/smallImage/smallImage-vertical-horizontal.ppm smallImage-vertical-horizontal
     vertical-flip smallImage-horizontal smallImage-horizontal-vertical
     save res/smallImage/smallImage-horizontal-vertical.ppm smallImage-horizontal-vertical
     value-component smallImage smallImage-value-greyscale
     save res/smallImage/smallImage-value-grayscale.ppm smallImage-value-greyscale
     intensity-component smallImage smallImage-intensity-greyscale
     save res/smallImage/smallImage-intensity-grayscale.ppm smallImage-intensity-greyscale
     luma-component smallImage smallImage-luma-greyscale
     save res/smallImage/smallImage-luma-grayscale.ppm smallImage-luma-greyscale
     red-component smallImage smallImage-red-greyscale
     save res/smallImage/smallImage-red-grayscale.ppm smallImage-red-greyscale
     green-component smallImage smallImage-green-greyscale
     save res/smallImage/smallImage-green-grayscale.ppm smallImage-green-greyscale
     blue-component smallImage smallImage-blue-greyscale
     save res/smallImage/smallImage-blue-grayscale.ppm smallImage-blue-greyscale
     */
  }
}
