package adventOfCode2021;

public class Line {
    private final int XStart;
    private final int YStart;
    private final int XEnd;
    private final int YEnd;

    public Line(int XStart, int YStart, int XEnd, int YEnd) {
        this.XStart = XStart;
        this.YStart = YStart;
        this.XEnd = XEnd;
        this.YEnd = YEnd;
    }

    public int getXStart() {
        return XStart;
    }

    public int getYStart() {
        return YStart;
    }

    public int getXEnd() {
        return XEnd;
    }

    public int getYEnd() {
        return YEnd;
    }

    public String toString() {
        return XStart + " | " + YStart + " | " + XEnd + " | "
                + YEnd + "\n";
    }
}
