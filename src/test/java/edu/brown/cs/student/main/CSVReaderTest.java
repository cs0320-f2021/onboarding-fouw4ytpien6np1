package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderTest {

    @Test
    public void testCSVReader() throws IOException {
        CSVReader csvReader = new CSVReader("data/stars/three-star.csv");

        // test proper CSVReader construction:
        assertEquals("data/stars/three-star.csv", csvReader.filePath);

        List<Star> testList = new ArrayList<>();
        Star testStar1 = new Star("1", "Star One", 1.0, 0.0, 0.0);
        testList.add(testStar1);
        Star testStar2 = new Star("2", "Star Two", 2.0, 0.0, 0.0);
        testList.add(testStar2);
        Star testStar3 = new Star("3", "Star Three", 3.0, 0.0, 0.0);
        testList.add(testStar3);

        // test x values
        assertEquals(testList.get(0).x, csvReader.starList.get(0).x);
        assertEquals(testList.get(1).x, csvReader.starList.get(1).x);
        assertEquals(testList.get(2).x, csvReader.starList.get(2).x);

        // test starID
        assertEquals(testList.get(0).starID, csvReader.starList.get(0).starID);
        assertEquals(testList.get(1).starID, csvReader.starList.get(1).starID);
        assertEquals(testList.get(2).starID, csvReader.starList.get(2).starID);

        // test name
        assertEquals(testList.get(0).name, csvReader.starList.get(0).name);
        assertEquals(testList.get(1).name, csvReader.starList.get(1).name);
        assertEquals(testList.get(2).name, csvReader.starList.get(2).name);

    }

    @Test
    public void testCreateStar() throws IOException {
        List<String> starData = new ArrayList<>();
        starData.add("1");
        starData.add("Star One");
        starData.add("1");
        starData.add("0");
        starData.add("0");
        Star createdStar = CSVReader.createStar(starData);

        // test that Star was created correctly
        assertEquals("1", createdStar.starID);
        assertEquals("Star One", createdStar.name);
        assertEquals(1.0, createdStar.x, 0.0001);
        assertEquals(0.0, createdStar.y, 0.0001);
        assertEquals(0.0, createdStar.z, 0.0001);

    }
}
