package Element;

public class Adventurer{

    private String name;

    private String path;

    private String orientation;

    private Square square;

    private int treasuresFound;

    public Adventurer(String name, String path, String orientation, Square square) {
        this.name = name;
        this.path = path;
        this.orientation = orientation;
        this.square = square;
        this.treasuresFound = 0;
    }

    public Adventurer() {

    }

    public int getPositionX()
    {
        return this.square.getX();
    }

    public int getPositionY(){
        return this.square.getY();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public int getTreasuresFound() {
        return treasuresFound;
    }

    public void setTreasuresFound(int treasuresFound) {
        this.treasuresFound = treasuresFound;
    }

    @Override
    public String toString() {
        return "Adventurer " + name +
                ", x=" + square.getX() + ", y=" + square.getY() +
                ", treasures found=" + treasuresFound;
    }
}
