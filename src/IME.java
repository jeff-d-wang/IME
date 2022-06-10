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
   * @param args   Arguments
   */
  public static void main(String[] args) {
    IPictureModel model = new PictureModel();
    PictureView view = new PictureTextView(model);
    Readable readable = new InputStreamReader(System.in);
    PictureController controller = new PictureControllerImpl(model, view, readable);
    controller.run();
    /*
    Script that uses all the methods:
     load src/pictures/smallImage/smallImage.ppm smallImage
     brighten 10 smallImage smallImage-brighter-by-10
     save src/pictures/smallImage/smallImage-brighter-by-10.ppm smallImage-brighter-by-10
     brighten -10 smallImage smallImage-darken-by-10
     save src/pictures/smallImage/smallImage-darken-by-10.ppm smallImage-darken-by-10
     vertical-flip smallImage smallImage-vertical
     save src/pictures/smallImage/smallImage-vertical.ppm smallImage-vertical
     horizontal-flip smallImage smallImage-horizontal
     save src/pictures/smallImage/smallImage-horizontal.ppm smallImage-horizontal
     horizontal-flip smallImage-vertical smallImage-vertical-horizontal
     save src/pictures/smallImage/smallImage-vertical-horizontal.ppm smallImage-vertical-horizontal
     vertical-flip smallImage-horizontal smallImage-horizontal-vertical
     save src/pictures/smallImage/smallImage-horizontal-vertical.ppm smallImage-horizontal-vertical
     value-component smallImage smallImage-value-greyscale
     save src/pictures/smallImage/smallImage-value-grayscale.ppm smallImage-value-greyscale
     intensity-component smallImage smallImage-intensity-greyscale
     save src/pictures/smallImage/smallImage-intensity-grayscale.ppm smallImage-intensity-greyscale
     luma-component smallImage smallImage-luma-greyscale
     save src/pictures/smallImage/smallImage-luma-grayscale.ppm smallImage-luma-greyscale
     red-component smallImage smallImage-red-greyscale
     save src/pictures/smallImage/smallImage-red-grayscale.ppm smallImage-red-greyscale
     green-component smallImage smallImage-green-greyscale
     save src/pictures/smallImage/smallImage-green-grayscale.ppm smallImage-green-greyscale
     blue-component smallImage smallImage-blue-greyscale
     save src/pictures/smallImage/smallImage-blue-grayscale.ppm smallImage-blue-greyscale
     */
  }
}
