package fr.amu.iut.exercice17;

import javafx.beans.property.*;

public class Exercice {
    private final int a;
    private final int b;
    private final IntegerProperty solution = new SimpleIntegerProperty();

    public Exercice() {
        this.a = (int)(Math.random() * 10);
        this.b = (int)(Math.random() * 10);
        this.solution.set(a + b);
    }

    public String getEnonce() {
        return a + " + " + b + " = ?";
    }

    public IntegerProperty solutionProperty() {
        return solution;
    }

    public int getSolution() {
        return solution.get();
    }
}
