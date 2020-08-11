package org.example;

import java.util.Objects;

import static org.example.Constants.CHAR_FOR_LAND;

public class Cell {
    private char value;

    private int x;

    private int y;

    private boolean isLand;

    private boolean isVisited;

    public Cell() {
    }

    public Cell(int x, int y, char charValue) {
        this.x = x;
        this.y = y;
        value = charValue;
        isLand = value == CHAR_FOR_LAND;
    }

    public Cell(int x, int y, String value) {
        this(x, y, value.charAt(0));
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited() {
        isVisited = true;
    }

    public void unsetVisited() {
        isVisited = false;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLand() {
        return isLand;
    }

    public void setLand(boolean land) {
        isLand = land;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value=" + value +
                ", x=" + x +
                ", y=" + y +
                ", isLand=" + isLand +
                ", isVisited=" + isVisited +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return getX() == cell.getX() &&
                getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

}
