package island.pojo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Island {

    UUID uuid;

    ArrayList<Cell> cells = new ArrayList<>();

    public Island() {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Island)) return false;
        Island island = (Island) o;
        return getUuid().equals(island.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return "Island{" +
                "uuid=" + uuid +
                ", cells=" + cells +
                '}';
    }
}
