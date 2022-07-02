package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.IFeatures;
import model.ImageUtil;
import model.picture.IPicture;
import model.picture.IPictureModel;

/**
 * JFrame view for the Picture program.
 */
public class PictureJFrameView extends JFrame implements IPictureView,
        ActionListener {

  private final int width;
  private final int height;
  private final IPictureModel model;
  private String command;
  private String pictureName;
  private String componentType;
  private String flipDirection;
  private final JPanel mainPanel;

  private BufferedImage pictureIcon;
  private final JPanel picturePanel;
  private final JLabel pictureLabel;

  private final JPanel messagePanel;
  private final JLabel messageLabel;

  private final JPanel commandPanel;
  private final JLabel commandsLabel;
  private final JComboBox commands;

  private HistogramPanel histogramPanel;

  private final JPanel fileOpenPanel;
  private final JButton fileOpenButton;
  private final JLabel fileOpenPathLabel;
  private File fileChosen;

  private final JPanel fileSavePanel;
  private final JLabel fileSavePathLabel;

  private final JPanel namePanel;
  private final JTextField nameField;

  private final JPanel pictureNamesPanel;
  private JLabel pictureNamesLabel;
  private JComboBox pictureNames;

  private final JPanel loadPanel;
  private final JButton loadButton;

  private final JPanel savePanel;
  private final JButton saveButton;

  private final JPanel filePanel;
  private final JButton fileButton;

  private final JPanel componentPanel;
  private final JButton componentButton;
  private final JPanel componentTypesPanel;
  private final JLabel componentTypesLabel;
  private final JComboBox componentTypes;

  private final JPanel flipPanel;
  private final JButton flipButton;
  private final JPanel flipDirectionsPanel;
  private final JLabel flipDirectionsLabel;
  private final JComboBox flipDirections;

  private final JPanel brightenPanel;
  private final JButton brightenButton;
  private final JPanel incrementPanel;
  private final JTextField incrementField;

  private final JPanel blurPanel;
  private final JButton blurButton;

  private final JPanel sharpenPanel;
  private final JButton sharpenButton;

  private final JPanel greyscalePanel;
  private final JButton greyscaleButton;

  private final JPanel sepiaPanel;
  private final JButton sepiaButton;
  /**
   * Default constructor for the PictureJFrameView class.
   *
   * @param model Model object
   * @throws IllegalArgumentException if given Appendable object is null
   */
  public PictureJFrameView(IPictureModel model) throws IllegalArgumentException {

    super();
    if (model == null) {
      throw new IllegalArgumentException("Given null model.");
    }
    this.model = model;
    String[] supportedCommands = new String[]{"Load", "Save", "File", "Component", "Flip",
            "Brighten", "Blur", "Sharpen", "Greyscale", "Sepia"};
    this.command = "";
    this.pictureName = null;

    this.width = 500;
    this.height = 500;
    setSize(width, height);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.add(mainPanel);

    // Picture
    picturePanel = new JPanel();
    picturePanel.setLayout(new BoxLayout(picturePanel, BoxLayout.PAGE_AXIS));

    pictureIcon = new BufferedImage(width, height, 1);
    pictureLabel = new JLabel(new ImageIcon(pictureIcon));
    JScrollPane pictureScrollPane = new JScrollPane(pictureLabel);
    pictureScrollPane.setPreferredSize(new Dimension(width, height));
    picturePanel.add(pictureScrollPane);

    // Message
    messagePanel = new JPanel();
    messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
    messageLabel = new JLabel("Messages (including errors) will appear here");
    messagePanel.add(messageLabel);

    // Command
    commandPanel = new JPanel();
    commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));
    commandsLabel = new JLabel("Select command: ");
    commands = new JComboBox<>(supportedCommands);
    commands.setActionCommand("Commands Combobox");
    commands.addActionListener(this);
    commandPanel.add(commandsLabel);
    commandPanel.add(commands);

    // display to the default panel
    resetMainPanel();

    // Open File
    fileOpenPanel = new JPanel();
    fileOpenPanel.setLayout(new BoxLayout(fileOpenPanel, BoxLayout.X_AXIS));
    JLabel fileOpenLabel = new JLabel("Open a file: ");
    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open File");
    fileOpenButton.addActionListener(this);
    fileOpenPathLabel = new JLabel();
    fileOpenPanel.add(fileOpenLabel);
    fileOpenPanel.add(fileOpenButton);
    fileOpenPanel.add(fileOpenPathLabel);

    // Save File
    fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new BoxLayout(fileSavePanel, BoxLayout.X_AXIS));
    JLabel fileSaveLabel = new JLabel("Save to a file: ");
    JButton fileSaveButton = new JButton("Save to a file");
    fileSaveButton.setActionCommand("Save File");
    fileSaveButton.addActionListener(this);
    fileSavePathLabel = new JLabel();
    fileSavePanel.add(fileSaveLabel);
    fileSavePanel.add(fileSaveButton);
    fileSavePanel.add(fileSavePathLabel);

    // Name Field
    namePanel = new JPanel();
    namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
    JLabel nameLabel = new JLabel("Enter a name: ");
    nameField = new JTextField();
    nameField.setPreferredSize(new Dimension(200, 24));
    namePanel.add(nameLabel);
    namePanel.add(nameField);

    // Picture Name
    pictureNamesPanel = new JPanel();
    pictureNamesPanel.setLayout(new BoxLayout(pictureNamesPanel, BoxLayout.X_AXIS));
    pictureNamesLabel = new JLabel("Select picture: ");
    pictureNames = new JComboBox<>(model.pictureNames());
    pictureNames.setActionCommand("Picture Names Combobox");
    pictureNames.addActionListener(this);
    pictureNamesPanel.add(pictureNamesLabel);
    pictureNamesPanel.add(pictureNames);

    // Load Panel
    loadPanel = new JPanel();
    loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.PAGE_AXIS));
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load Button");

    // Save Panel
    savePanel = new JPanel();
    savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.PAGE_AXIS));
    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save Button");

    // File Panel
    filePanel = new JPanel();
    filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.PAGE_AXIS));
    fileButton = new JButton("File");
    fileButton.setActionCommand("File Button");

    // Component Panel
    componentPanel = new JPanel();
    componentPanel.setLayout(new BoxLayout(componentPanel, BoxLayout.PAGE_AXIS));
    componentButton = new JButton("Component");
    componentButton.setActionCommand("Component Button");

    // Component Type Panel
    componentTypesPanel = new JPanel();
    componentTypesPanel.setLayout(new BoxLayout(componentTypesPanel, BoxLayout.X_AXIS));
    componentTypesLabel = new JLabel("Select type: ");
    componentTypes = new JComboBox<>(new String[]{"Red", "Green", "Blue", "Value", "Intensity",
            "Luma"});
    componentTypes.setActionCommand("Component Types Combobox");
    componentTypes.addActionListener(this);
    componentTypesPanel.add(componentTypesLabel);
    componentTypesPanel.add(componentTypes);

    // Flip Panel
    flipPanel = new JPanel();
    flipPanel.setLayout(new BoxLayout(flipPanel, BoxLayout.PAGE_AXIS));
    flipButton = new JButton("Flip");
    flipButton.setActionCommand("Flip Button");

    // Flip Direction Panel
    flipDirectionsPanel = new JPanel();
    flipDirectionsPanel.setLayout(new BoxLayout(flipDirectionsPanel, BoxLayout.X_AXIS));
    flipDirectionsLabel = new JLabel("Select direction: ");
    flipDirections = new JComboBox<>(new String[]{"Horizontal", "Vertical"});
    flipDirections.setActionCommand("Flip Directions Combobox");
    flipDirections.addActionListener(this);
    flipDirectionsPanel.add(flipDirectionsLabel);
    flipDirectionsPanel.add(flipDirections);

    // Brighten Panel
    brightenPanel = new JPanel();
    brightenPanel.setLayout(new BoxLayout(brightenPanel, BoxLayout.PAGE_AXIS));
    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten Button");

    // Increment Panel
    incrementPanel = new JPanel();
    incrementPanel.setLayout(new BoxLayout(incrementPanel, BoxLayout.X_AXIS));
    JLabel incrementLabel = new JLabel("Enter an increment: ");
    incrementField = new JTextField();
    incrementField.setPreferredSize(new Dimension(200, 24));
    incrementPanel.add(incrementLabel);
    incrementPanel.add(incrementField);

    // Blur Panel
    blurPanel = new JPanel();
    blurPanel.setLayout(new BoxLayout(blurPanel, BoxLayout.PAGE_AXIS));
    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur Button");

    // Sharpen Panel
    sharpenPanel = new JPanel();
    sharpenPanel.setLayout(new BoxLayout(sharpenPanel, BoxLayout.PAGE_AXIS));
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen Button");

    // Greyscale Panel
    greyscalePanel = new JPanel();
    greyscalePanel.setLayout(new BoxLayout(greyscalePanel, BoxLayout.PAGE_AXIS));
    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("Greyscale Button");

    // Sepia Panel
    sepiaPanel = new JPanel();
    sepiaPanel.setLayout(new BoxLayout(sepiaPanel, BoxLayout.PAGE_AXIS));
    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia Button");

    this.add(mainPanel);
    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Adds features to this view for action events.
   *
   * @param features Features to be connected with
   */
  public void addFeatures(IFeatures features) {

    loadButton.addActionListener(evt -> {
      try {
        features.load(fileChosen.getAbsolutePath(), nameField.getText());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });

    saveButton.addActionListener(evt -> {
      try {
        features.save(model.getPicture(pictureName), fileChosen.getAbsolutePath());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });

    fileButton.addActionListener(evt -> {
      try {
        features.file(fileChosen.getAbsolutePath());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });

    componentButton.addActionListener(evt -> {
      try {
        features.component(componentType.toLowerCase(), model.getPicture(pictureName),
                nameField.getText());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });

    flipButton.addActionListener(evt -> {
      try {
        features.flip(flipDirection.toLowerCase(), model.getPicture(pictureName),
                nameField.getText());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });

    brightenButton.addActionListener(evt -> {
      try {
        features.brighten(Integer.parseInt(incrementField.getText()),
                model.getPicture(pictureName), nameField.getText());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });

    blurButton.addActionListener(evt -> {
      try {
        features.blur(model.getPicture(pictureName), nameField.getText());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });

    sharpenButton.addActionListener(evt -> {
      try {
        features.sharpen(model.getPicture(pictureName), nameField.getText());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });

    greyscaleButton.addActionListener(evt -> {
      try {
        features.greyscale(model.getPicture(pictureName), nameField.getText());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });

    sepiaButton.addActionListener(evt -> {
      try {
        features.sepia(model.getPicture(pictureName), nameField.getText());
        resetMainPanel();
      } catch (Exception e) {
        renderMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    });
  }

  /**
   * Render a given picture to the main panel.
   *
   * @param name Name of picture to be rendered
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderPicture(String name) throws IOException {
    pictureName = name;
    pictureIcon = ImageUtil.toBufferedImage(model.getPicture(name));
    pictureLabel.setIcon(new ImageIcon(pictureIcon));
  }

  @Override
  public void renderMessage(String message) {
    messageLabel.setText(message);
  }

  private void resetHistogramPanel(IPicture picture) {
    histogramPanel = new HistogramPanel(width, 300, picture);
  }

  private void setFileOpenButton(String actionCommand) {
    fileOpenButton.setActionCommand(actionCommand);
  }

  private void resetPictureNamesPanel() {
    pictureNamesLabel = new JLabel("Select picture: ");
    pictureNames = new JComboBox<>(model.pictureNames());
    pictureNames.setActionCommand("Picture Names Combobox");
    pictureNames.addActionListener(this);
    pictureNamesPanel.removeAll();
    pictureNamesPanel.add(pictureNamesLabel);
    pictureNamesPanel.add(pictureNames);
  }

  /**
   * Resets the mainPanel to the default page.
   */
  private void resetMainPanel() {
    System.out.println("reset main panel");
    mainPanel.removeAll();
    mainPanel.add(picturePanel);
    if (pictureName != null) {
      resetHistogramPanel(model.getPicture(pictureName));
      mainPanel.add(histogramPanel);
    }
    messageLabel.setText("Messages (including errors) will appear here");
    mainPanel.add(messagePanel);
    commandsLabel.setText("Select command: ");
    mainPanel.add(commandPanel);
    mainPanel.repaint();
    this.repaint();
  }

  /**
   * Resets the load panel for display.
   */
  private void resetLoadPanel() {
    loadPanel.removeAll();
    setFileOpenButton("Open File");
    loadPanel.add(fileOpenPanel);
    loadPanel.add(namePanel);
    loadPanel.add(loadButton);
  }

  /**
   * Resets the save panel for display.
   */
  private void resetSavePanel() {
    savePanel.removeAll();
    resetPictureNamesPanel();
    savePanel.add(pictureNamesPanel);
    savePanel.add(fileSavePanel);
    savePanel.add(saveButton);
  }

  /**
   * Resets the file panel for display.
   */
  private void resetFilePanel() {
    filePanel.removeAll();
    setFileOpenButton("Open Text File");
    filePanel.add(fileOpenPanel);
    filePanel.add(fileButton);
  }

  /**
   * Resets the component panel for display.
   */
  private void resetComponentPanel() {
    componentPanel.removeAll();
    componentPanel.add(componentTypesPanel);
    resetPictureNamesPanel();
    componentPanel.add(pictureNamesPanel);
    componentPanel.add(namePanel);
    componentPanel.add(componentButton);
  }

  /**
   * Resets the flip panel for display.
   */
  private void resetFlipPanel() {
    flipPanel.removeAll();
    flipPanel.add(flipDirectionsPanel);
    resetPictureNamesPanel();
    flipPanel.add(pictureNamesPanel);
    flipPanel.add(namePanel);
    flipPanel.add(flipButton);
  }

  /**
   * Resets the brighten panel for display.
   */
  private void resetBrightenPanel() {
    brightenPanel.removeAll();
    brightenPanel.add(incrementPanel);
    resetPictureNamesPanel();
    brightenPanel.add(pictureNamesPanel);
    brightenPanel.add(namePanel);
    brightenPanel.add(brightenButton);
  }

  /**
   * Resets the blur panel for display.
   */
  private void resetBlurPanel() {
    blurPanel.removeAll();
    resetPictureNamesPanel();
    blurPanel.add(pictureNamesPanel);
    blurPanel.add(namePanel);
    blurPanel.add(blurButton);
  }

  /**
   * Resets the sharpen panel for display.
   */
  private void resetSharpenPanel() {
    sharpenPanel.removeAll();
    resetPictureNamesPanel();
    sharpenPanel.add(pictureNamesPanel);
    sharpenPanel.add(namePanel);
    sharpenPanel.add(sharpenButton);
  }

  /**
   * Resets the greyscale panel for display.
   */
  private void resetGreyscalePanel() {
    greyscalePanel.removeAll();
    resetPictureNamesPanel();
    greyscalePanel.add(pictureNamesPanel);
    greyscalePanel.add(namePanel);
    greyscalePanel.add(sharpenButton);
  }

  /**
   * Resets the sepia panel for display.
   */
  private void resetSepiaPanel() {
    sepiaPanel.removeAll();
    resetPictureNamesPanel();
    sepiaPanel.add(pictureNamesPanel);
    sepiaPanel.add(namePanel);
    sepiaPanel.add(sepiaButton);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    switch (e.getActionCommand()) {
      case "Commands Combobox": {
        command = (String) commands.getSelectedItem();
        commandsLabel.setText(command + " selected");
        resetMainPanel();
        switch (command) {
          case "Load":
            resetLoadPanel();
            mainPanel.add(loadPanel);
            break;
          case "Save":
            resetSavePanel();
            mainPanel.add(savePanel);
            break;
          case "File":
            resetFilePanel();
            mainPanel.add(filePanel);
            break;
          case "Component":
            resetComponentPanel();
            mainPanel.add(componentPanel);
            break;
          case "Flip":
            resetFlipPanel();
            mainPanel.add(flipPanel);
            break;
          case "Brighten":
            resetBrightenPanel();
            mainPanel.add(brightenPanel);
            break;
          case "Blur":
            resetBlurPanel();
            mainPanel.add(blurPanel);
            break;
          case "Sharpen":
            resetSharpenPanel();
            mainPanel.add(sharpenPanel);
            break;
          case "Greyscale":
            resetGreyscalePanel();
            mainPanel.add(greyscalePanel);
            break;
          case "Sepia":
            resetSepiaPanel();
            mainPanel.add(sepiaPanel);
            break;
          default:
            break;
        }
      }
      break;

      case "Open File": {
        final JFileChooser fileChooser = new JFileChooser("./res");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PPM, BMP, JPG, JPEG, & PNG Files",
                "ppm", "bmp", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);
        int fileDialog = fileChooser.showOpenDialog(this);
        if (fileDialog == JFileChooser.APPROVE_OPTION) {
          fileChosen = fileChooser.getSelectedFile();
          fileOpenPathLabel.setText(fileChosen.getAbsolutePath());
        }
      }
      break;

      case "Open Text File": {
        final JFileChooser fileChooser = new JFileChooser("./res");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TXT Files",
                "txt");
        fileChooser.setFileFilter(filter);
        int fileDialog = fileChooser.showOpenDialog(this);
        if (fileDialog == JFileChooser.APPROVE_OPTION) {
          fileChosen = fileChooser.getSelectedFile();
          fileOpenPathLabel.setText(fileChosen.getAbsolutePath());
        }
      }
      break;

      case "Save File": {
        final JFileChooser fileChooser = new JFileChooser("./res");
        int fileDialog = fileChooser.showSaveDialog(this);
        if (fileDialog == JFileChooser.APPROVE_OPTION) {
          fileChosen = fileChooser.getSelectedFile();
          fileSavePathLabel.setText(fileChosen.getAbsolutePath());
        }
      }
      break;

      case "Picture Names Combobox": {
        pictureName = (String) pictureNames.getSelectedItem();
        pictureNamesLabel.setText(pictureName + " selected");
      }
      break;

      case "Component Types Combobox": {
        componentType = (String) componentTypes.getSelectedItem();
        componentTypesLabel.setText(componentType + " selected");
      }
      break;

      case "Flip Directions Combobox": {
        flipDirection = (String) flipDirections.getSelectedItem();
        flipDirectionsLabel.setText(flipDirection + " selected");
      }
      break;

      default:
        break;
    }
  }
}
