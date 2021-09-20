# README
To build use:
`mvn package`

**To run use:**
`./run`

**Project Name:**
`Onboarding`

**Project Description:**

To run use:
`./run`

This will create a REPL, where they are several commands.

The first two commands come from the Onboarding Lab. They are "add num1 num2" and "subtract num1 num2", where
num1 and num2 are numbers (Double or Integer). The output is always a Double of the addition or subtraction of 
the two numbers that follow the command. 

The second major command "stars filepath" will load the specified stars csv file from the filepath using a CSVReader 
object. If the user inputs this, then the user has access to TWO commands only, which are associated with the 
csv file specified by the filepath. They are:

1. "naive_neighbors <k> <x> <y> <z>", where
    - k is the nonnegative, integral number of nearest neighbors to find
    - x, y, z represents the position from which to find the nearest neighbors.
    
2. "naive_neighbors <k> <“name”>" where 
   - name is the name of a star, which must be given in quotes.

If an input to the REPL does not match the specified formats, then the default output is to return the first
word that the user inputs, except in the case of loading a stars csv file. If a user does not input a new csv file to
read or input either of the 2 commands listed above, then an error will be produced simply as "ERROR:" in line
with the convention in the testing files. The REPL process, however, will continue, and the user has access to the three
commands ("stars", and naive_neighbors for both the x y z and "name" inputs).

**Design Choices:**
1. CSVReader Class
    * I have implemented the CSVReader as an Object that whose instantiation method requires the filePath of the 
      desired csv file. This class could implement a broader Reader Interface that would allow for different
      implementations for different file types. For example a TXTReader Class could implement such a Reader interface,
      as the CSVReader Class would.
    * The actual CSVReader uses a buffered reader to process the inputted csv file line by line. Since the headers are 
      assumed for this project, I have decided to not process them while reading the file, as instantiating the 
      CSVReader Object adds Stars to a List<Star> field within the CSVReader Object. A future design decision
      might be to create a CSVReader Class, but then have a subclass that specifically reads in Stars.
    * The CSVReader has a method called CreateStar that parses line data to create a Star Object.
    
2. Star Class
    * The Star class serves to store the information read in from the CSVReader, so that our naive_knn algorithm can 
      process the Star data effectively.
    * A Star has 6 attributes - StarID, name, x, y, z, and euclideanDistance (initiated to null). 
    * I have added a method called setEuclideanDistance to set the euclideanDistance to the mainStar calculated 
      in naive_knn.
      
3. naive_knn Class implements knn
    * The class has 8 fields, some of which are initially assigned values and updated as the algorithm is run. They 
      are: mainStar (star to calculate euclideanDistance from), starsFromCSV (List of Stars read in from CSVReader), 
      kStars (List to store final calculated Stars closes to mainStar), k (number of nearest neighbors), 
      intsLessThanK (used in case of ties), intsGreaterThanK (used in case of ties), rand (used to randomly
      selected stars with tied EuclideanDistance to mainStar), starsToRandomlyChooseFrom (List to store tied
      stars).
    * The naive_knn Object is initiated with a mainStar, List<Star> from CSVReader and a k value. If k is < 0, an 
      IllegalArgumentException is thrown, which corresponds to an "ERROR:" message in the main REPL. In the case of the
      first command, "naive_neighbors <k> <x> <y> <z>", a stand-in mainStar is created from which the 
      euclideanDistance is calculated from with the corresponding x, y, and z values. If the other command is used,
      "naive_neighbors <k> <“name”>", then that Star is found and assigned to mainStar.
    * The main method in the naive_knn class is knnAlgorithm. It uses three helper methods to set the kStars closest 
      to mainStar, which are setDistances() (sets the euclideanDistance from each star to mainStar), 
      sortDistances() (sorts the list of stars by euclideanDistance), getTopKEntries() (checks for ties and 
      randomly chooses stars until there are a total of K stars). 

4. Main
    * The main design decision here was to create a separate REPL stream if the User loads a star CSV File. 
      The only commands a user has access to once the stars command is initially run is to load a new CSV File
      with the stars command or to run naive_knn with a distance (x, y, z) or by star name.

**Errors/Bugs:**

**Tests**

**How to run tests:**

* When building the package for this project, you can run mvn package, which will run all the tests, both unit 
  and system tests automatically. Alternatively, you can run mvn test to compile sources and run unit tests.
  
**To run use:**
`./run`

**To start the server use:**
`./run --gui [--port=<port>]`