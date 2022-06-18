package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import model.picture.IPicture;
import model.picture.PictureImpl;
import model.pixel.IPixel;
import model.pixel.PixelImpl;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Write an image to a given file.
   *
   * @param filename the path of the file.
   * @throws IOException if it encounters an error accessing/reading the file
   */
  public static void writeFile(IPicture picture, String filename) throws IOException {
    // figure out by substring of last three letter s for file name
    // if == ppm copy and past toFile
    if (filename == null) {
      throw new IOException("Null filename.");
    }

    String fileType = filename.substring(filename.length() - 3);

    if (fileType.equals("ppm") || fileType.equals("pbm")) {
      PrintWriter os = new PrintWriter(filename);

      // header
      os.println("P3");
      os.println(picture.getWidth() + " " + picture.getHeight());
      os.println(255);

      for (int r = 0; r < picture.getHeight(); r++) {
        for (int c = 0; c < picture.getWidth(); c++) {
          IPixel pixel = picture.getPixel(r, c);
          os.println(pixel.getR());
          os.println(pixel.getG());
          os.println(pixel.getB());
        }
      }

      os.close();
    } else {
      BufferedImage output = new BufferedImage(picture.getWidth(),
              picture.getHeight(), BufferedImage.TYPE_INT_RGB);
      for (int r = 0; r < picture.getHeight(); r++) {
        for (int c = 0; c < picture.getWidth(); c++) {
          int red = picture.getPixel(r, c).getR();
          int green = picture.getPixel(r, c).getG();
          int blue = picture.getPixel(r, c).getB();

          int color = new Color(red, green, blue).getRGB();
          output.setRGB(c, r, color);
        }
      }
      ImageIO.write(output, fileType, new File(filename));
    }
  }

  /**
   * Reads an image file from the file formats ppm, bmp, jpg and png.
   *
   * @param filename the file name to be read from
   * @throws IOException if it encounters an error reading/accessing from the file
   */
  public static PictureImpl readFile(String filename) throws IOException {
    if (filename == null) {
      throw new IOException("Null filename.");
    }

    PictureImpl picture;
    String fileType = filename.substring(filename.length() - 3);

    // checking whether file is equal to ppm or pbm
    if (fileType.equals("ppm") || fileType.equals("pbm")) {
      Scanner sc;

      try {
        sc = new Scanner(new FileInputStream(filename));
      } catch (FileNotFoundException e) {
        throw new IOException("File " + filename + " not found.");
      }

      StringBuilder builder = new StringBuilder();
      // read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }

      // now set up the scanner to read from the string we just built
      sc = new Scanner(builder.toString());
      String token = sc.next();
      if (!token.equals("P3")) {
        throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
      }
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxValue = sc.nextInt();
      picture = new PictureImpl(width, height);

      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          int red = sc.nextInt();
          int green = sc.nextInt();
          int blue = sc.nextInt();
          picture.setPixel(r, c, new PixelImpl(red, green, blue));
        }
      }
    } else {

      BufferedImage currentFile = ImageIO.read(new File(filename));
      int height = currentFile.getHeight();
      int width = currentFile.getWidth();
      picture = new PictureImpl(width, height);

      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          Color color = new Color(currentFile.getRGB(c, r));
          int red = color.getRed();
          int green = color.getGreen();
          int blue = color.getBlue();
          picture.setPixel(r, c, new PixelImpl(red, green, blue));
        }
      }
    }

    return picture;
  }
}

