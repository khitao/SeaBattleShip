
import java.util.Objects;

public class Ship {
    private int size;

    private String position;


    public Ship(int size) {
        this.size = size;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public String isPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return size == ship.size && position.equals(ship.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size,/* coordinate,*/ position);
    }
}
