package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;

import model.picture.IPicture;
import model.pixel.IPixel;

/**
 * This is the HistogramPanel class. This class allows the user to see the histogram with
 * rgb colors.
 */
public class HistogramPanel extends JPanel {

  private IPicture picture;
  private final int height;
  private final static int barWidth;
  private final int offset;

  static {
    barWidth = 2;
  }

  /**
   * Constructor for histogram panel.
   *
   * @param width   the width of the histogram.
   * @param height  the height of the histogram.
   * @param picture the picture to get the rgb values of.
   * @throws IllegalArgumentException if picture is null.
   */
  public HistogramPanel(int width, int height, IPicture picture) throws IllegalArgumentException {
    if (picture == null) {
      throw new IllegalArgumentException("Given null picture");
    }
    this.height = height;
    this.picture = picture;
    this.offset = (width - barWidth * 256) / 2;
    this.setPreferredSize(new Dimension(width, height));
  }

  private int getMax(int[] a) {
    int max = a[0];
    for (int i = 1; i < a.length; i++) {
      if (a[i] > max) {
        max = a[i];
      }
    }
    return max;
  }

  /**
   * This method tells the panel how to draw the bar graph.
   * @param page the <code>Graphics</code> object to protect
   */
  public void paintComponent(Graphics page) {
    super.paintComponent(page);

    System.out.println("paint component");

    int[] countR = new int[256];
    int[] countG = new int[256];
    int[] countB = new int[256];

    for (int r = 0; r < picture.getHeight(); r++) {
      for (int c = 0; c < picture.getWidth(); c++) {
        IPixel pixel = picture.getPixel(r, c);
        countR[pixel.getR()]++;
        countG[pixel.getG()]++;
        countB[pixel.getB()]++;
      }
    }

    double scale = (double) (height) / Math.max(Math.max(getMax(countR),
            getMax(countG)), getMax(countB));
    System.out.println(height);

    for (int i = 0; i < countR.length; i++) {
      int barHeight = (int) (countR[i] * scale);
      page.setColor(new Color(255, 0, 0, 25));
      page.fillRect(offset + i * barWidth, height - barHeight, barWidth, barHeight);

      barHeight = (int) (countG[i] * scale);
      page.setColor(new Color(0, 255, 0, 25));
      page.fillRect(offset + i * barWidth, height - barHeight, barWidth, barHeight);

      barHeight = (int) (countB[i] * scale);
      page.setColor(new Color(0, 0, 255, 25));
      page.fillRect(offset + i * barWidth, height - barHeight, barWidth, barHeight);

      barHeight = (int) (((countR[i] + countG[i] + countB[i]) / 3) * scale);
      page.setColor(new Color(0, 0, 0, 25));
      page.fillRect(offset + i * barWidth, height - barHeight, barWidth, barHeight);
    }
  }
}
