# IME
OOD Assignment 4
Updated: 6/10/22 

Model:
The model package contains a Lambda package, a Picture package, a Pixel package, and a ImageUtil
class.

Within the Picture package, it contains a IPicture Interface, a PPMPicture class, an abstract 
RGBPicture class, and a Picture Model class. 
The interface IPicture contains the methods that every picture object should have:
- getWidth() which returns an integer that is the width of this picture
- getHeight() which returns an integer that is the height of this picture
- getPixel(int r, int c) which returns an IPixel which is the Pixel object at a given row and 
column value
- setPixel(int r, int c, IPixel pixel) which sets the Pixel object at a given row and column value
the given Pixel object
- greyscale(String component) which greyscales the picture according to a given component 
specifying its type
- flip(String direction) which flips the picture in a given String direction
- brighten(int increment) which brightens the picture by a given increment
- toFile(String filename) which converts the picture into its respective file type

The RGBPicture abstract class implements the interface IPicture and represents a picture that uses 
the RGB pixel. This class contains a protected RGBPixel[][] pixels variable which represents the 
pixels of the picture and a protected RGBPicture altercation variable representing a copy of the
picture. The constructor takes in an int width, and int height and initializes the pixels to a new 
2D array of type RGBPixel. This class contains a applyLambda(RGBPixelLambda lambda) method which 
applies the given lambda to all pixels of this picture and stores it in its altercation. It 
implemented the getWidth(), getHeight(), getPixel(), flip() methods which should be present in any 
type of RGBPicture object. Additionally, this class contains an abstract void resetAltercation() 
whose purpose is to initialize the altercation variable and override setPixel() so that it is an 
abstract method. 

The PPMPicture represents a Picture that is a PPM file type. This class extends the RGBPicture 
abstract class and contains a private final String token which represents the type of PPM file this 
picture is and a private static final int maxValue which represents the maxValue a individual Pixel 
component can be. The constructor takes in a String token, int width, int height, and calls 
super(width, height). This class contains getter methods getToken(), getMaxValue() and implemented
resetAltercation(), setPixel(), greyscale(), brighten(), and toFile(). 

THe PictureModel class represents a model for the Picture class. This class essentially acts like 
a picture collection that saves and retrieves pictures for a controller. It has a
private Map<String, IPicture> pictures. There are two constructors for this class, one takes in 
no parameters and the other takes in a Map<String, IPicture> pictures which is a map of pictures 
to house all future pictures. This class has a putPicture(String name, IPicture picture) method 
that puts a given picture value to a new key name in pictures and a getPicture(String name) method 
that returns the picture value of a given key name.

The Pixel package contains a IPixel Interface, an abstract RGBPixel class, and a RGBPixelImpl class. 
The IPixel interface represents a pixel which encapsulates all simulates between pixels 
(RGB, RGBA, HVI, Hex, etc.). It contains the methods: 
- getValue() which returns the maximum int value of the three components of this pixel
- getIntensity() which returns an average int value of the three components of this pixel
- getLuma() which returns the luma int value of this pixel

The abstract RGBPixel class implements IPixel and was made to branch similar code between a pixel 
with RGB values, one that also has an alpha component, and others that may feature other defining 
details. It contains a private int r, private int g, private int b for the red, green, blue 
component of a pixel respectively. It also contains a private static final int max and private 
static final int min to represent the max and min values each component of the pixel can be. 
The constructor takes in an int r, int g, int b and calls clamp(int color) which clamps color 
values to their respective max and min possible values. This class contains the methods: 
- getR() which returns the red int value of this pixel
- setR(int r) which sets the red value of this pixel
- getG() which returns the green value of this pixel
- setG(int g) which sets the green value of this pixel
- getB() which returns the blue value of this pixel
- setB(int b) which sets the blue value of this pixel
- setRGB(int r, int g, int b) which sets given RGB values to their respective variables. It will 
clamp inputs to its min and max values. 
This class also implemented the getValue(), getIntensity(), getLuma(), and toString() method. 

The RGBPixelImpl class extends RGBPixel and represents a pixel with its RGB values. The constructor 
takes in int r, int g, int b and calls super(r, g, b). This class override equals() and hashCode(). 

The ImageUtil class contains utility methods to read a PPM image from file and simply write 
its contents to a picture. It contains the methods:
- readPPM(String filename) which reads an image file in the PPM format and return a representative 
Picture object.
- writePPM(PPMPicture picture, String filename) which writes out the PPM Picture into a String. 
- main(String[] args) which is a demo main method. 

Inside the Lambda package, there are two interfaces called IPixelLambda and a RGBPixelLambda. The
IPixelLambda interface is for lambdas mutating IPixels. The RGBPixelLambda interface is for lambdas
mutating RGBPixels and contains the method run(RGBPixel p).

Controller:
The controller has a PictureController Interface and a PictureControllerImpl class. The 
PictureController Interface represents a controller for the Picture class. It contains the method
run() which runs the IME program by accepting user input and parsing it. The PictureControllerImpl
class implements the PictureController Interface and has:
- private PictureTextView view, which represents the view that we can visually see 
- private final Readable readable which represents the Readable object for inputs
- private Map<String, IPicture> pictures which represents a hashmap of pictures with their names 
- private Map<String, Runnable> runnables which represents a hashmap of runnable class for various 
functions
- private String[] script which represents the user input of commands such as "image name"
- private String[] command which represents the break down of script such as "horizontal" or "flip"
The constructor takes in a PictureModel model, PictureView view, Readable readable and puts into 
the variable runnables by calling the Runnable class for the load function, save function, 
greyscale function, flip function, and brighten function. This PictureControllerIMpl class 
implemented run(). 

View:
The view package contains a PictureView Interface and a PictureTextView class. The PictureView 
Interface represents operations that should be offered by a view for the IME program. It contains 
the method renderMessage(String message) which renders a specific message to the provided data 
destination. The PictureTextView class implements the PictureView Interface and contains a private 
Appendable. The PictureTextView class contains two constructors, one takes in no parameters and 
calls this(System.out) while the other takes in an Appendable object that it will view as 
its destination. The PictureTextView class implements the renderMessage() method. 

Pictures:
The pictures package contains: 
- a smallImage package which contains 
    - smallImage.ppm 
    - smallImage-blue-greyscale.ppm 
    - smallImage-brighten-by-10.ppm 
    - smallImage-brighten-by-30.ppm 
    - smallImage-darken-by-10.ppm
    - smallImage-green-greyscale.ppm 
    - smallImage-horizontal.ppm 
    - smallImage-horizontal-vertical.ppm 
    - smallImage-intensity-greyscale.ppm 
    - smallImage-luma-greyscale.ppm 
    - smallImage-red-greyscale.ppm 
    - smallImage-value-greyscale.ppm 
    - smallImage-vertical.ppm
    - smallImage-vertical-horizontal.ppm 
- Koala.ppm 
- koala-blue-greyscale.png 
- koala-brighter-by-50.png
- koala-green-greyscale.png
- koala-horizontal.png 
- koala-horizontal-vertical.png 
- koala-intensity-greyscale.png 
- koala-luma-greyscale.png 
- koala-red-greyscale.png 
- koala-value-greyscale.png 
- koala-vertical.png 
- koala-vertical-horizontal.png 
The images in smallImage package is my own photograph created using GIMP, and I am 
authorizing it to be used in this project. The smallImage.ppm is the original image. 
Pictures with names such as smallImage-blue-greyscale means that the original image was 
greyscaled by blue. Pictures with names such as smallImage-horizontal-vertical.ppm means that the 
original image was flipped horizontally and then vertically. The rest of the images in the 
pictures package were given as starter code for this assignment. 

Script of Commands that program will accept:
To load smallImage.ppm and call it 'smallImage' enter: 
load src/pictures/smallImage/smallImage.ppm smallImage

To brighten smallImage by 10 enter: 
brighten 10 smallImage smallImage-brighter-by-10

To darken smallImage by 10 enter:
brighten -10 smallImage smallImage-darken-by-10

To flip smallImage vertically enter: 
vertical-flip smallImage smallImage-vertical

To flip smallImage horizontally enter:
horizontal-flip smallImage smallImage-horizontal

To flip the vertically flipped smallImage horizontally enter: 
horizontal-flip smallImage-vertical smallImage-vertical-horizontal

To flip the horizontally flipped smallImage vertically enter: 
vertical-flip smallImage-horizontal smallImage-horizontal-vertical

To create a greyscale using only the value component, as an image smallImage-greyscale enter: 
value-component smallImage smallImage-value-greyscale

To create a greyscale using only the intensity component, as an image smallImage-greyscale enter: 
intensity-component smallImage smallImage-intensity-greyscale

To create a greyscale using only the luma component, as an image smallImage-greyscale enter: 
luma-component smallImage smallImage-luma-greyscale

To create a greyscale using only the red component, as an image smallImage-greyscale enter: 
red-component smallImage smallImage-red-greyscale

To create a greyscale using only the green component, as an image smallImage-greyscale enter: 
green-component smallImage smallImage-green-greyscale

To create a greyscale using only the blue component, as an image smallImage-greyscale enter: 
blue-component smallImage smallImage-blue-greyscale

To save smallImage-brighter-by-10 enter: 
save src/pictures/smallImage/smallImage-brighter-by-10.ppm smallImage-brighter-by-10

To save smallImage-red-greyscale enter: 
save src/pictures/smallImage/smallImage-red-grayscale.ppm smallImage-red-greyscale

To overwrite smallImage with another file enter: 
load src/pictures/smallImage/smallImage-red-grayscale.ppm smallImage