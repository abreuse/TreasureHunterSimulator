package element;

public class Square extends Element{

    private int treasures;

    private boolean isOccupied;

    private boolean isMountain;

    public Square(int x, int y, int treasures, boolean isOccupied, boolean isMountain) {
        super(x, y);
        this.treasures = treasures;
        this.isOccupied = isOccupied;
        this.isMountain = isMountain;
    }

    public int getTreasures() {
        return treasures;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isMountain() {
        return isMountain;
    }

    @Override
    public String toString() {
        return super.toString() +
                "; Square{" +
                "treasures=" + treasures +
                ", isOccupied=" + isOccupied +
                ", isMountain=" + isMountain +
                '}';
    }
}
