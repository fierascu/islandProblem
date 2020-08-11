package org.example;

import java.util.Objects;

import static org.example.Frame.CHAR_FOR_ISLAND;

public class Cell {
    char value;

    int x;

    int y;

    boolean isLand;

    public Cell() {
    }

    public Cell(int x, int y, char charValue) {
        this.x = x;
        this.y = y;
        value = charValue;
        isLand = value == CHAR_FOR_ISLAND;
    }

    public Cell(int x, int y, String value) {
        this(x, y, value.charAt(0));
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
