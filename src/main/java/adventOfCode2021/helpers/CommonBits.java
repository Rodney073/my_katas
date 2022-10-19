package adventOfCode2021.helpers;

public class CommonBits {
    private String mostCommon;
    private String lestCommon;

    public CommonBits(String mostCommon, String lestCommon) {
        this.mostCommon = mostCommon;
        this.lestCommon = lestCommon;
    }

    public String getMostCommon() {
        return mostCommon;
    }

    public void setMostCommon(String mostCommon) {
        this.mostCommon = mostCommon;
    }

    public String getLeastCommon() {
        return lestCommon;
    }

    public void setLestCommon(String lestCommon) {
        this.lestCommon = lestCommon;
    }
}
