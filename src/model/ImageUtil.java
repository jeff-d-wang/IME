package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import model.Picture.IPicture;
import model.Picture.PPMPicture;
import model.Picture.RGBPicture;
import model.Pixel.RGBPixel;
import model.Pixel.RGBPixelImpl;


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

  public static void writePPM(PPMPicture picture, String filename) throws IOException {
    PrintWriter out = new PrintWriter(filename);

    out.println(picture.getToken());
    out.println(picture.getWidth() + " " + picture.getHeight());
    out.println(picture.getMaxValue());

    for (int i = 0; i < picture.getWidth(); i++) {
      for (int j = 0; j < picture.getHeight(); j++) {
        RGBPixel pixel = picture.getPixel(i, j);
        int r = pixel.getR();
        int g = pixel.getG();
        int b = pixel.getB();
        out.println(r + " " + g + " " + b);
      }
    }
  }

  //demo main
  public static void main(String[] args) throws IOException {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "src/pictures/Koala.ppm";
    }

    IPicture koala = ImageUtil.readPPM(filename);

    System.out.println(koala.getWidth() + ", " + koala.getHeight());
  }
}

