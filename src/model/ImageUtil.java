package model;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import model.Pixel.RGBPixel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Picture readPicture(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
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

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Picture picture = new Picture(width, height);
    picture.setMaxValue(maxValue);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        picture.setPixel(i, j, new RGBPixel(r, g, b));
      }
    }

    return picture;
  }

  //demo main
  public static void main(String[] args) throws IOException {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "src/Koala.ppm";
    }

    Picture koala = ImageUtil.readPicture(filename);

    System.out.println(koala.getWidth() + ", " + koala.getHeight());
  }
}

