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
        assertEquals("data/stars/three-star.csv", csvReader.returnFilePath());

        List<Star> testList = new ArrayList<>();
        Star testStar1 = new Star("1", "Star One", 1.0, 0.0, 0.0);
        testList.add(testStar1);
        Star testStar2 = new Star("2", "Star Two", 2.0, 0.0, 0.0);
        testList.add(testStar2);
        Star testStar3 = new Star("3", "Star Three", 3.0, 0.0, 0.0);
        testList.add(testStar3);

        // test x values
        assertEquals(testList.get(0).returnX(), csvReader.returnStarList().get(0).returnX());
        assertEquals(testList.get(1).returnX(), csvReader.returnStarList().get(1).returnX());
        assertEquals(testList.get(2).returnX(), csvReader.returnStarList().get(2).returnX());

        // test starID
        assertEquals(testList.get(0).returnStarID(), csvReader.returnStarList().get(0).returnStarID());
        assertEquals(testList.get(1).returnStarID(), csvReader.returnStarList().get(1).returnStarID());
        assertEquals(testList.get(2).returnStarID(), csvReader.returnStarList().get(2).returnStarID());

        // test name
        assertEquals(testList.get(0).returnStarName(), csvReader.returnStarList().get(0).returnStarName());
        assertEquals(testList.get(1).returnStarName(), csvReader.returnStarList().get(1).returnStarName());
        assertEquals(testList.get(2).returnStarName(), csvReader.returnStarList().get(2).returnStarName());

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
        assertEquals("1", createdStar.returnStarID());
        assertEquals("Star One", createdStar.returnStarName());
        assertEquals(1.0, createdStar.returnX(), 0.0001);
        assertEquals(0.0, createdStar.returnY(), 0.0001);
        assertEquals(0.0, createdStar.returnZ(), 0.0001);

    }
}
