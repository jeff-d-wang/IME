package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImageUtil;
import model.picture.IPicture;
import model.picture.IPictureModel;

public class PictureJFrameView extends JFrame implements IPictureView, ActionListener, ItemListener, ListSelectionListener {

  private IPictureModel model;
  private String[] supportedCommands;
  private String command;
  private JPanel mainPanel;

  private JPanel picturePanel;
  private JLabel pictureLabel;

  private JPanel commandPanel;
  private JLabel commandsLabel;
  private JComboBox commands;

  private JPanel messagePanel;
  private JLabel messageLabel;

  private JPanel loadPanel;
  private JButton fileOpenButton;
  private JLabel fileOpenLabel;

  private JButton run;

  /**
   * Default constructor for the PictureJFrameView class.
   * @param model   Model object
   *
   * @throws IllegalArgumentException if given Appendable object is null
   */
  public PictureJFrameView(IPictureModel model) throws IllegalArgumentException {

    super();
    if (model == null) {
      throw new IllegalArgumentException("Given null model.");
    }
    this.model = model;
    this.supportedCommands = new String[]{"Load", "Save", "File", "Component", "Flip", "Brighten",
            "Blur", "Sharpen", "Greyscale", "Sepia"};
    this.command = "";

    setSize(400, 400);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.add(mainPanel);

    // Picture
    picturePanel = new JPanel();
    picturePanel.setLayout(new BoxLayout(picturePanel, BoxLayout.PAGE_AXIS));

    pictureLabel = new JLabel(new ImageIcon("res/smallImage/smallImage.png"));
    JScrollPane pictureScrollPane = new JScrollPane(pictureLabel);
    pictureScrollPane.setPreferredSize(new Dimension(400, 400));
    picturePanel.add(pictureScrollPane);
    mainPanel.add(picturePanel);

    // Message
    messagePanel = new JPanel();
    messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
    mainPanel.add(messagePanel);
    messageLabel = new JLabel("Messages (including errors) will appear here");
    messagePanel.add(messageLabel);

    // Command
    commandPanel = new JPanel();
    commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));
    mainPanel.add(commandPanel);

    // Commands combobox
    commands = new JComboBox<>(supportedCommands);
    commandsLabel = new JLabel("None selected");
    commandPanel.add(commands);
    commandPanel.add(commandsLabel);
    commands.setActionCommand("Command Combobox");
    commands.addActionListener(this);

    // Load Command
    loadPanel = new JPanel();
    commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));
    // not adding to mainpanelyet

    this.add(mainPanel);
    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Render a given picture to the main panel.
   * @param picture   IPicture to be rendered.
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderPicture(IPicture picture) {
    pictureLabel.setIcon(new ImageIcon(ImageUtil.toBufferedImage(picture)));
  }

  @Override
  public void renderMessage(String message) {
    messageLabel.setText(message);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    switch (e.getActionCommand()) {
      case "Command Combobox":
        command = (String) commands.getSelectedItem();
        commandsLabel.setText(command + " selected");



        mainPanel.add(new Button("Run"));
        break;
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
  }

  private void loadCommandPanel() {

  }
}
