import java.io.InputStreamReader;

import controller.IPictureController;
import controller.PictureJFrameController;
import model.picture.IPictureModel;
import model.picture.PictureModel;
import view.IPictureView;
import view.PictureJFrameView;

public class IMEGUI {
  public static void main(String[] args) {
    IPictureModel model = new PictureModel();
    IPictureView view = new PictureJFrameView(model);
    IPictureController controller = new PictureJFrameController(model, view);
  }
}
