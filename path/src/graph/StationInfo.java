package graph;

public class StationInfo implements IStationInfo {
    private String name;
    private int xPos;
    private int yPos;

    public StationInfo(String name, int xPos, int yPos) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getxPos() {
        return xPos;
    }

    @Override
    public int getyPos() {
        return yPos;
    }
    public String toString() {
        return name + " (" + xPos + ", " + yPos + ")";
    }
}