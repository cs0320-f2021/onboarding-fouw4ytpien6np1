package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
  private String filePath;
  private Path path;
  private ArrayList<Star> starList = new ArrayList<>();
  private BufferedReader br;


  /**
   * @param filePath - the path of the CSV file the user wants to read.
   * @throws IOException - Error printed in case of bad file read.
   */
  public CSVReader(String filePath) throws IOException {
    this.filePath = filePath;
    try {
      this.path = Paths.get(filePath);
      this.br = Files.newBufferedReader(path);

      String line = br.readLine();

      while (line != null) {
        try {
          String[] starAttributes = (line.split(","));
          Star star = createStar(Arrays.asList(starAttributes));
          starList.add(star);
          line = br.readLine();
        } catch (Exception e) {
          line = br.readLine();
        }
      }
      System.out
          .println("Read " + starList.size() + " stars from " + this.filePath);
    } catch (IOException ieo) {
      System.out.println("File not found from Path. Try Again.");
    }
  }

  /**
   * Creates a Star from inputted data. Process used in CSV processing.
   *
   * @param data - a List of Strings
   * @return new Star
   */
  public static Star createStar(List<String> data) {
    String starID = data.get(0);
    String name = data.get(1);
    Double x = Double.parseDouble(data.get(2));
    Double y = Double.parseDouble(data.get(3));
    Double z = Double.parseDouble(data.get(4));

    return new Star(starID, name, x, y, z);
  }

  /**
   * Returns the list of stars read in from the CSVFile.
   *
   * @return objectList
   */
  public ArrayList<Star> returnStarList() {
    return this.starList;
  }

  /**
   * Returns associated filePath of Reader.
   *
   * @return filePath
   */
  public String returnFilePath() {
    return this.filePath;
  }
}
