package edu.brown.cs.student.main;

import java.util.List;

public interface knn {
    double epsilon = 0.00001d;

    void knnAlgorithm();

    void sortDistances();

    void getTopKEntries();

    void displayResults();
}
