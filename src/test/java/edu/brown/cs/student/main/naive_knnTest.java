package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class naive_knnTest {

    @Test
    public void testNaive_knn() throws IOException {
        CSVReader csvReader = new CSVReader("data/stars/three-star.csv");
        ArrayList<Star> stars = csvReader.starList;
        Star mainStar = new Star("test", "test", 0.0, 0.0, 0.0);

        // test k = 0 functionality
        naive_knn knn = new naive_knn(mainStar, stars, 0);
        assertEquals(0, knn.returnKStars().size());

        // test k = 1
        naive_knn knn2 = new naive_knn(mainStar, stars, 1);
        knn2.knnAlgorithm();
        ArrayList<Star> outputK1 = knn2.returnKStars();
        assertEquals(1, outputK1.size());
        Star knn2Star1 = outputK1.get(0);
        assertEquals("Star One", knn2Star1.name);

        // test k = 2 and order of stars
        naive_knn knn3 = new naive_knn(mainStar, stars, 2);
        knn3.knnAlgorithm();
        ArrayList<Star> outputK2 = knn3.returnKStars();
        Star knn3Star1 = outputK2.get(0);
        assertEquals("1", knn3Star1.starID );
        Star knn3Star2 = outputK2.get(1);
        assertEquals("2", knn3Star2.starID);

        // test k = 3 and order
        naive_knn knn4 = new naive_knn(mainStar, stars, 3);
        knn4.knnAlgorithm();
        ArrayList<Star> outputK3 = knn4.returnKStars();
        Star knn4Star1 = outputK3.get(0);
        assertEquals("1", knn4Star1.starID);
        Star knn4Star2 = outputK3.get(1);
        assertEquals("2", knn4Star2.starID);
        Star knn4Star3 = outputK3.get(2);
        assertEquals("3", knn4Star3.starID);

        // test larger file with 10 Stars
        CSVReader csvReader2 = new CSVReader("data/stars/ten-star.csv");
        ArrayList<Star> stars2 = csvReader2.starList;
        Star mainStar2 = new Star("test", "test", 0.0, 0.0, 0.0);

        // test k = 1 on 10 star dataset
        naive_knn knn5 = new naive_knn(mainStar2, stars2, 1);
        knn5.knnAlgorithm();
        Star knn5star1 = knn5.returnKStars().get(0);
        assertEquals("Sol", knn5star1.name);

        // test k = 2
        naive_knn knn6 = new naive_knn(mainStar2, stars2, 2);
        knn6.knnAlgorithm();
        Star knn6Star1 = knn6.returnKStars().get(0);
        assertEquals("Sol", knn6Star1.name);
        Star knn6Star2 = knn6.returnKStars().get(1);
        assertEquals("Proxima Centauri", knn6Star2.name);
    }

    @Test
    public void testSetDistances() throws IOException {
        CSVReader csvReader = new CSVReader("data/stars/three-star.csv");
        ArrayList<Star> stars = csvReader.starList;
        Star mainStar = new Star("test", "test", 0.0, 0.0, 0.0);

        naive_knn knn = new naive_knn(mainStar, stars, 3);
        knn.setDistances();
        ArrayList<Star> starsFromCSV = knn.returnStarsFromCSV();
        Star firstStar = starsFromCSV.get(0);
        assertEquals(1, firstStar.euclideanDistance, 0.0001);
        Star secondStar = starsFromCSV.get(1);
        assertEquals(2, secondStar.euclideanDistance, 0.0001);
        Star thirdStar = starsFromCSV.get(2);
        assertEquals(3, thirdStar.euclideanDistance, 0.0001);

    }

    @Test
    public void testSortDistances() throws IOException {
        CSVReader csvReader = new CSVReader("data/stars/three-star.csv");
        ArrayList<Star> stars = csvReader.starList;
        Star mainStar = new Star("test", "test", 0.0, 0.0, 0.0);
        naive_knn knn = new naive_knn(mainStar, stars, 3);
        knn.setDistances();
        knn.sortDistances();
        Star firstStar = knn.returnStarsFromCSV().get(0);
        assertEquals(1, firstStar.euclideanDistance, 0.0001);
        Star secondStar = knn.returnStarsFromCSV().get(1);
        assertEquals(2, secondStar.euclideanDistance, 0.0001);
        Star thirdStar = knn.returnStarsFromCSV().get(2);
        assertEquals(3, thirdStar.euclideanDistance, 0.0001);
    }

    @Test
    public void testGetTopKEntries() throws IOException {
        CSVReader csvReader = new CSVReader("data/stars/three-star.csv");
        ArrayList<Star> stars = csvReader.starList;
        Star mainStar = new Star("test", "test", 0.0, 0.0, 0.0);
        naive_knn knn = new naive_knn(mainStar, stars, 3);
        knn.setDistances();
        knn.sortDistances();
        knn.getTopKEntries();
        ArrayList<Star> kStars = knn.returnKStars();
        Star firstStar = kStars.get(0);
        assertEquals("Star One", firstStar.name);
        Star secondStar = kStars.get(1);
        assertEquals("Star Two", secondStar.name);
        Star thirdStar = kStars.get(2);
        assertEquals("Star Three", thirdStar.name);

        // Test large stardata.csv file for functionality purposes.
        CSVReader csvReader2 = new CSVReader("data/stars/stardata.csv");
        ArrayList<Star> stars2 = csvReader2.starList;
        naive_knn knn2 = new naive_knn(mainStar, stars2, 10);
        knn2.knnAlgorithm();
        // uncomment below to test output
        //  knn2.displayResults();
        Star firstStar2 = knn2.returnKStars().get(0);
        assertEquals("0", firstStar2.starID);
        Star secondStar2 = knn2.returnKStars().get(1);
        assertEquals("70667", secondStar2.starID);


        // make convoluted example to test ties:
        ArrayList<Star> starsTie = new ArrayList<>();
        for (int i = 0; i < 4; i ++) {
            Star star1 = new Star("testStar", "test", 1.0, 0.0, 0.0);
            starsTie.add(star1);
        }
        Star mainStar2 = new Star("Main Star", "Main Star", 0.0, 0.0, 0.0);
        naive_knn knn3 = new naive_knn(mainStar2, starsTie, 3);
        knn3.knnAlgorithm();
        assertEquals(3, knn3.returnKStars().size());


    }

    @Test
    public void testknnAlgorithmn() {
        // knnAlorithmn consists of setDistances(), sortDistances(),
        // and getTopKEntries(), which I have tested above, thus I omit tests for
        // this function specifically.
    }

    @Test
    public void testDistanceBetweenPoints() throws IOException {
        CSVReader csvReader = new CSVReader("data/stars/three-star.csv");
        ArrayList<Star> stars = csvReader.starList;
        Star mainStar = new Star("test", "test", 0.0, 0.0, 0.0);
        naive_knn knn = new naive_knn(mainStar, stars, 3);
        Double dist1 = knn.distanceBetweenPoints(1.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        assertEquals(1.0, dist1, 0.0001);
        Double dist2 = knn.distanceBetweenPoints(5.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        assertEquals(5.0, dist2, 0.0001);
    }
}
