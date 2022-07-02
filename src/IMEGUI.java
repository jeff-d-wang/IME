
import controller.IPictureController;
import controller.PictureJFrameController;
import model.picture.IPictureModel;
import model.picture.PictureModel;
import view.PictureJFrameView;

/**
 * This is the main method class to be able to open the program with GUI.
 */
public class IMEGUI {

  /**
   * The main method and entry point to the IME program.
   *
   * @param args Arguments
   */
  public static void main(String[] args) {
    IPictureModel model = new PictureModel();
    PictureJFrameView view = new PictureJFrameView(model);
    IPictureController controller = new PictureJFrameController(model, view);
  }
}
