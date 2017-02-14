package Element;

public class Square extends Element{

    private Adventurer adventurer;

    private int treasures;

    private boolean isMountain;

    public Square(int x, int y, int treasures, Adventurer adventurer, boolean isMountain) {
        super(x, y);
        this.treasures = treasures;
        this.adventurer = adventurer;
        this.isMountain = isMountain;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }

    public int getTreasures() {
        return treasures;
    }

    public void setTreasures(int treasures) {
        this.treasures = treasures;
    }

    public void setMountain(boolean mountain) {
        isMountain = mountain;
    }


    public boolean isMountain() {
        return isMountain;
    }

    @Override
    public String toString() {
        return super.toString() +
                "; Square{" +
                "adventurer=" + adventurer +
                "treasures=" + treasures +
                ", isMountain=" + isMountain +
                '}';
    }
}
