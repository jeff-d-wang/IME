package model;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import model.picture.IPicture;
import model.picture.PPMPicture;
import model.pixel.RGBPixelImpl;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and return a representative Picture object.
   *
   * @param filename the path of the file.
   * @throws IOException if it encounters an error accessing/reading the file
   */
  public static PPMPicture readPPM(String filename) throws IOException {
    if (filename == null) {
      throw new IOException("Null filename.");
    }

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
    PPMPicture picture = new PPMPicture(token, width, height);

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();
        picture.setPixel(r, c, new RGBPixelImpl(red, green, blue));
      }
    }

    return picture;
  }

  /**
   * Write an image to a given file in the PPM format.
   *
   * @param filename the path of the file.
   * @throws IOException if it encounters an error accessing/reading the file
   */
  public static void writeFile(IPicture picture, String filename) throws IOException {
    picture.toFile(filename);
    //    PrintWriter out = new PrintWriter(filename);
    //
    //    out.println(picture.getToken());
    //    out.println(picture.getWidth() + " " + picture.getHeight());
    //    out.println(picture.getMaxValue());
    //
    //    for (int i = 0; i < picture.getWidth(); i++) {
    //      for (int j = 0; j < picture.getHeight(); j++) {
    //        RGBPixel pixel = picture.getPixel(i, j);
    //        int r = pixel.getR();
    //        int g = pixel.getG();
    //        int b = pixel.getB();
    //        out.println(r + " " + g + " " + b);
    //      }
    //    }
  }
}

