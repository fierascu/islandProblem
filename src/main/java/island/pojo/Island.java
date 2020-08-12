package island.pojo;

import java.util.ArrayList;
import java.util.Objects;

public class Island {

    static int counter;

    int id;

    ArrayList<Cell> cells = new ArrayList<>();

    public Island() {
        id = counter;
        counter++;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
    }

    public boolean hasCell(Cell cell) {
        return !cells.isEmpty() && cells.stream().findAny().get().equals(cell);
    }


    public boolean hasCell(int x, int y) {
        return !cells.isEmpty() &&
                cells.stream().filter(cell -> cell.getX() == x && cell.getY() == y).findFirst().isPresent();
    }

    public boolean isCellVisited(Cell cell) {
        return !cells.isEmpty() && cells.stream().findAny().get().equals(cell);
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "Island{" +
                "id=" + id +
                ", cells=" + cells +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Island)) return false;
        Island island = (Island) o;
        return getId() == island.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
