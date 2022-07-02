# IME
## OOD Assignment 6 
### Update 6/25/22

## Model Package: 
In our model package, PictureImpl now contains two new methods: 
- downscale(double x, double y) which downscales an image by either width or height
- partialImageManipulation(IPicture pic, String command) which partially manipulates an image 
based on a mask image.

### Controller: 
The controller now contains IFeatures Interface, FeatureImpl, and a PictureJFrameController. 
The IFeatures Interface contains:
- load(String filename, String name) which is the load feature function for the controller.
- save(IPicture picture, String filename) which is the save feature function for the controller.
- file(String filename) which is the file feature function for the controller.
- component(String component, IPicture picture, String name) which is the component feature 
function for the controller.
- flip(String direction, IPicture picture, String name) which is the flip feature function for the
controller.
- brighten(int increment, IPicture picture, String name) which is the Brighten feature function 
for the controller. 
- blur(IPicture picture, String name) which is the Blur feature function for the controller.
- sharpen(IPicture picture, String name) which is the Sharpen feature function for the controller.
- greyscale(IPicture picture, String name) which is the Greyscale feature function for the 
controller.
- sepia(IPicture picture, String name) which is the Sepia feature function for the controller.
- partialImage(IPicture picture, IPicture mask, String command, String name) which is the
  partialImage feature function for the controller.

The FeaturesImpl abstract class implements the IFeature interface.

### View: 
The view has a new PictureJFrameView class and a HistogramPanel class which allows the user to see 
the histogram with rgb colors.


### IMEJAR: 
This new main method allows the user to open the program with a jar file frm terminal. 

## OOD Assignment 5
### Update: 6/17/22 
Side note: All changes made are noted in this README version for assignment 5.

## Model Package: 
In our model, it now contains a filter package, a picture package, a pixel package, a transformation
package, the ImageUtil class, and the IPixelLambda class. 

### ImageUtil class: 
In the ImageUtil class, what was modified was the read and write methods can now read and write 
to different image types such as png, bmp and jpg. It continues to support reading and writing 
from file types of ppm. Originally, we thought that alpha values would be used and thus causing a
need for different types of pixels and pictures, but we realized that this was not the case in our
situation. So now, instead of the IPicture class handling its conversion to a file format, we let
the ImageUtil class manage such. It can now handle conversion of once ppm file pictures to now other
supported formats.

### Picture Package: 
The picture package was modified, and it now contains a IPicture interface, a IPictureModel 
interface, a PictureImpl class and a PictureModel class. 

IPicture now represent an interface picture class with rgb values. This was decided as such because
now we know that we should only be working with RGB values and thus, all pictures share those
qualities.
In the IPicture interface, the new methods are: 
- blur() which blurs a given image
- sharpen() which sharpens a given image
- greyscale() which applies a greyscale transformation on an image
- sepia() which applies a sepia transformation on an image.

The previous greyscale(String component) method which greyscaled the picture according to a given 
component was renamed to component(String component) because a greyscale transformation 
is different from a component transformation such as a red greyscale modification. This causes for
some minor alteration with naming convention across the code like in the controller (Runnable) but
it isn't something to worry about.

The toFile(String filename) method from earlier was removed because the write method in the 
ImageUtil class can support this functionality. The reason being was mentioned before but ultimately
a IPicture should represent all pictures possible and the ImageUtil class should focus on converting
a picture to file formats.

The IPictureModel interface are representative of a collection of Pictures that are constantly 
being added and retrieved by the controller. Each of those pictures handle functionality. This
interface now gets implemented by the PictureModel class to add flexibility. This class now has the 
methods: putPicture(String name, IPicture picture) and getPicture(String name) which puts a given 
picture value to a new key name in pictures and returns the picture value of a given key name, 
respectively. 

The previous abstract RGBPicture class was removed because the original implementation idea was to 
create a new class for every type of picture (png, jpg, ppm, bmp) and override the toFile() method 
for each class. However, after our realization that we would only need to be working with RGB values
and nothing more/less (like HVI or alpha values), we could bridge the gap between an implemention of
a picture to the interface closer in a more intelligent way. Now, the writeFile() method in 
ImageUtils supports this functionality and hence, we removed the abstract RGBPicture class and 
just had PPMPicture implement IPicture. 

The previous PPMPPicture class was renamed to PictureImpl and continues to implement IPicture. 
Since the abstract RGBPicture class was removed, setPixel() is no longer an abstract method. 
So PictureImpl is now what a default picture with RGB values and functions is. The flaw with the 
last design was that we were thinking too far and deep into what it meant to be a picture and 
assumed that pixels could also be measured in values other than rgb. Thus, operations that relied 
on RGB values needed to be in different subclasses, creating layers of unnecessary abstractions. 
If we were to somehow face HEX values in the future, we could just convert them into RGB values 
or just add more values by extending the PictureImpl class. The overall structure of IPicture and
its child classes makes much more sense and is easier to work with.

The PictureModel class implements the IPictureModel interface. Nothing has been modified in this 
class.

### Pixel Package: 
The pixel package contains a IPixel interface and a PixelImpl class. The abstract RGBPixel class was
removed because the original implementation idea was that there would be more than one type of 
pixels that would need to be supported such as RGB pixel and a ARGB pixel. However, since there 
is only one type of pixel, the abstract RGBPixel class was removed and its methods were placed 
inside the IPixel interface. So the IPixel interface now contains the methods:
- getR() which returns the red int value of this pixel
- getG() which returns the green value of this pixel
- getB() which returns the blue value of this pixel
- getValue() which returns the maximum int value of the three components of this pixel
- getIntensity() which returns an average int value of the three components of this pixel
- getLuma() which returns the luma int value of this pixel 
- toMatrix() which returns the 3x1 matrix equivalent to this pixel's rgb values.

The following methods were removed because these methods essentially make the private fields of the 
PixelImpl class public. 
- setR(int r) which sets the red value of this pixel
- setG(int g) which sets the green value of this pixel
- setB(int b) which sets the blue value of this pixel 
- setRGB(int r, int g, int b) which sets given RGB values to their respective variables. It will
  clamp inputs to its min and max values.

The same here goes with RGBPixel. We were thinking too far ahead into what it meant to represent a 
pixel. IPixel was there to represent an eventual coming of Alpha values but that was not needed and
a pixel with rgb values was sufficient in our case. Thus, now we're able to reduce confusion around 
what interface/abstract type should this variable be etc. with just IPicture and IPixel since now 
all the subclasses will have RGB values. 

The previous RGBPixelImpl class was renamed to PixelImpl class and the PixelImpl class implements 
the IPixel interface. 

### Filter package: 
The filter package contains a IFilter interface and a FilterImpl class. The IFilter interface
represents a filter for a picture. It functions by applying a filter onto a kernel
of the same size, achieving an ultimate value for the pixel at the center of the kernel, then
assigns it its final RGB values. It contains the method apply(IPicture picture, double[][] filter)
which applies the given filter onto a given picture. The overall design of this filter package
allows for future filter methods to be made.

The FilterImpl class implements the IFilter interface. 

### Transformation package:
The transformation package contains a ITransformation interface and a TransformationImpl class.
The ITransformation interface represents a transformation for a picture. It functions by 
multiplying a matrix to a 1x3 matrix of rgb values for a pixel, achieving an ultimate value for 
the pixel, then assigns it its final RGB values. It contains the method 
`apply(IPicture picture, double[][] matrix)` which applies the given filter onto a given picture.
The overall design of this transformation package allows for future transformation methods to be
made.

The TransformationImpl class implements the ITransformation interface. 

### Controller:
The constructor continues to take in a PictureModel model, PictureView view, 
Readable readable and puts into the variable runnables by calling the Runnable class for the 
load function, save function, greyscale function, flip function, and brighten function. 
This PictureControllerImpl class implemented `run()`.

The PictureControllerImpl class now contains new Runnable classes for the blur function, sharpen 
function, greyscale function, sepia function, and a file function.

The `run` function also has another version of it `run(Readable readable)` which can accept a
different Readable object. This allows for the support of the `-file` command by feeding in the 
following text file into it. After the file has been successfully read and compiled, the program
is then able to continue accepting user-input commands.

### Lambda:
We deleted the package and just consolidated everything into one interface class called
IPixelLambda since there's no need to differentiate between RGB pixels and other pixel types (since
how we apply lambdas operate differently). Now IPixelLambda serves as the sole interface that is
able to run a defined lambda onto a picture.

### View: Nothing has been modified. 

### Main Method (IME): Nothing has been modified. 

## res/ Folder:

### SmallImage Folder: 
Pictures were moved out of the src/ folder and into the more appropriate res/ folder.
It now contains these new files: 
In the result package where we hold results of our program: 
- blur.ppm 
- sepia.ppm
- sharpen.ppm
- smallImageBMPto.bmp 
- smallImageBMPto.jpg
- smallImageBMPto.png
- smallImageJPGto.bmp
- smallImageJPGto.jpg
- smallImageJPGto.png
- smallImagePNGto.bmp 
- smallImagePNGto.jpg
- smallImagePNGto.png
- smallImagePPMto.bmp
- smallImagePPMto.jpg
- smallImagePPMto.jpg
- smallImagePPMto.png

In the smallImage package: 
- smallImage.bmp
- smallImage.jpeg
- smallImage.png 
- smallImage-blur.ppm 
- smallImage-greyscale.ppm 
- smallImage-half-filter.ppm
- smallImage-half-transform.ppm
- smallImage-one-filter.ppm
- smallImage-sepia.ppm
- smallImage-sharpen.ppm
- smallImage-zero-filter.ppm

### ImageProcessing.jar:
This is the jar file that can be executed through terminal. It can also run the example-script.txt
and test-script.txt.

### Example Script Txt: 
This file (example-script.txt) is a file that can be passed in as an input command when running the
jar file.

### Test Script Txt:
This file (test-script.txt) is a file that can be passed in as an input command when running the
jar file. This was made to test the `-file` script command and also be included in the
example-script.txt as an available and working command.

### IMEClassDiagram.png:
This picture details the class structure of this program in a diagram.

## smallImage Usage
The images in smallImage package is my `own` photograph created using GIMP, and I (RuiMing Li) am
authorizing it to be used in this project. The smallImage.ppm is the original image.
Pictures with names such as smallImage-blue-greyscale means that the original image was
greyscaled by blue. Pictures with names such as smallImage-horizontal-vertical.ppm means that the
original image was flipped horizontally and then vertically. The images in the result package
are images that were saved based on scripted commands such as the ones below.

## OOD Assignment 4
### Updated: 6/10/22

Model:
The model package contains a Lambda package, a Picture package, a Pixel package, and a ImageUtil
class.

Within the Picture package, it contains a IPicture Interface, a PPMPicture class, an abstract
RGBPicture class, and a PictureModel class.
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
details. This design choice was made by us to allow for the possibility of Pixels that are measured 
in other formats like Hex and HVI. The abstract class specifies RGB because png files include an 
alpha component but still share a great amount of code with just a regular RGB Pixel. It contains 
a private int r, private int g, private int b for the red, green, blue component of a pixel 
respectively. It also contains a private static final int max and private static final int min to
represent the max and min values each component of the pixel can be. The constructor takes in an 
int r, int g, int b and calls clamp(int color) which clamps color values to their respective max 
and min possible values. This class contains the methods:

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
mutating RGBPixels and contains the method run(RGBPixel p). This is done solely to reduce redundant 
code via application of a given lambda to all pixels of a Picture.

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
- private String[] command which represents the breakdown of script such as "horizontal" or "flip"
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

Pictures.smallImage:
The pictures package contains:  
- result package 
  - blueGreyscale.ppm
  - brighten-by-10.ppm
  - default.ppm
  - greenGreyscale.ppm
  - horizontal.ppm
  - horizontal-vertical.ppm
  - intensityGreyscale.ppm
  - lumaGreyscale.ppm
  - notPPM.jpeg
  - redGreyscale.ppm
  - valueGreyscale.ppm
  - vertical.ppm
  - vertical-horizontal.ppm
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
- smallImageP6.pbm

# smallImage Usage
The images in smallImage package is my `own` photograph created using GIMP, and I (RuiMing Li) am
authorizing it to be used in this project. The smallImage.ppm is the original image.
Pictures with names such as smallImage-blue-greyscale means that the original image was
greyscaled by blue. Pictures with names such as smallImage-horizontal-vertical.ppm means that the
original image was flipped horizontally and then vertically. The images in the result package 
are images that were saved based on scripted commands such as the ones below. 

How to run the program:
-To load a file, enter "load " + where it is in the directory + the name of the PPM
file
-To brighten, enter "brighten " + the number you want to brighten by, either brighten by 10 or
brighten by -10 (same thing as darken as 10) + the name of the PPM file + the name of the file you
want to save it as

- To flip, enter "horizontal-flip " or "vertical-flip " depending on which way you would like to
  flip + the name of the PPM file + the name of the file you want to save it as
- To greyscale, enter "red-grayscale " or "green grayscale " or "blue grayscale " or
  "value-grayscale " or "intensity-grayscale " or "luma grayscale " depending on which way you would
  like to grayscale + the name of the PPM file + the name of the file you want to save it as
- To save, enter "save " + where it is in the directory + the name you would like to save it as
- To overwrite, enter "load " + where it is in the directory + the name of the PPM
  file you would like to overwrite

Example Script of Commands that program will accept:
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

Example script for brightening and flipping smallImage:
load src/pictures/smallImage/smallImage.ppm smallImage
brighten 10 smallImage smallImage-brighter-by-10
save src/pictures/smallImage/smallImage-brighter-by-10.ppm smallImage-brighter-by-10
vertical-flip smallImage smallImage-vertical
vertical-flip smallImage-horizontal smallImage-horizontal-vertical
save src/pictures/smallImage/smallImage-horizontal-vertical.ppm smallImage-horizontal-vertical

Example script for blue grayscale:
load src/pictures/smallImage/smallImage.ppm smallImage
blue-component smallImage smallImage-blue-greyscale
save src/pictures/smallImage/smallImage-blue-grayscale.ppm smallImage-blue-greyscale

*** To see example PPM pictures that were saved navigate to the res/smallImage/result folder.