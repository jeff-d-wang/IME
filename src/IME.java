import java.io.InputStreamReader;

import controller.IPictureController;
import controller.PictureControllerImpl;
import model.picture.IPictureModel;
import model.picture.PictureModel;
import view.PictureTextView;
import view.IPictureView;

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
    IPictureView view = new PictureTextView(model);
    IPictureController controller = new PictureControllerImpl(model, view, readable);
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
    value-component smallImage smallImage-value-component
    save res/smallImage/smallImage-value-component.ppm smallImage-value-component
    intensity-component smallImage smallImage-intensity-component
    save res/smallImage/smallImage-intensity-component.ppm smallImage-intensity-component
    luma-component smallImage smallImage-luma-component
    save res/smallImage/smallImage-luma-component.ppm smallImage-luma-component
    red-component smallImage smallImage-red-component
    save res/smallImage/smallImage-red-component.ppm smallImage-red-component
    green-component smallImage smallImage-green-component
    save res/smallImage/smallImage-green-component.ppm smallImage-green-component
    blue-component smallImage smallImage-blue-component
    save res/smallImage/smallImage-blue-component.ppm smallImage-blue-component
    blur smallImage smallImage-blur
    save res/smallImage/smallImage-blur.ppm smallImage-blur
    sharpen smallImage smallImage-sharpen
    save res/smallImage/smallImage-blur.ppm smallImage-sharpen
    greyscale smallImage smallImage-greyscale
    save res/smallImage/smallImage-greyscale.ppm smallImage-greyscale
    sepia smallImage smallImage-sepia
    save res/smallImage/smallImage-sepia.ppm smallImage-sepia
    -file res/test-script.txt
     */
  }
}
