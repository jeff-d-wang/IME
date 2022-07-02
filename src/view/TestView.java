package view;

import java.awt.FlowLayout;
import java.awt.Button;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Test class for the view.
 */
public class TestView extends JFrame {
  /**
   * Basic constructor for test view class.
   */
  public TestView() {
    super();

    setSize(800, 800);
    JPanel mainPanel = new JPanel();
    // mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.setLayout(new FlowLayout());

    mainPanel.add(new Button());
    this.add(mainPanel);

    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    new TestView();
  }
}
