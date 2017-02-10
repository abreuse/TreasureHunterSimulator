package element;

public class Adventurer{

    private Square square;

    private String path;

    private String orientation;

    public Adventurer(Square square, String path, String orientation) {
        this.square = square;
        this.path = path;
        this.orientation = orientation;
    }

    public int getPositionX()
    {
        return this.square.getX();
    }

    public int getPositionY(){
        return this.square.getY();
    }

    @Override
    public String toString() {
        return "Adventurer{" +
                "square=" + square +
                ", path='" + path + '\'' +
                ", orientation='" + orientation + '\'' +
                '}';
    }
}
