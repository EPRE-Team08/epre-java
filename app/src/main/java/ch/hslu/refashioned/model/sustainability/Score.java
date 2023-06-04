package ch.hslu.refashioned.model.sustainability;

public class Score {
    public static final int MAX_SCORE = 100;
    public static final int MIN_SCORE = 0;
    private final int overall;
    private final int emissions;
    private final int transparency;
    private final int waterAndChemicals;
    private final int materials;
    private final int workersRights;
    private final int waste;

    public Score(int overall, int emissions, int transparency, int waterAndChemicals, int materials, int workersRights, int waste) {
        this.overall = overall;
        this.emissions = emissions;
        this.transparency = transparency;
        this.waterAndChemicals = waterAndChemicals;
        this.materials = materials;
        this.workersRights = workersRights;
        this.waste = waste;
    }

    public int getOverall() {
        return overall;
    }

    public int getEmissions() {
        return emissions;
    }

    public int getTransparency() {
        return transparency;
    }

    public int getWaterAndChemicals() {
        return waterAndChemicals;
    }

    public int getMaterials() {
        return materials;
    }

    public int getWorkersRights() {
        return workersRights;
    }

    public int getWaste() {
        return waste;
    }
}
