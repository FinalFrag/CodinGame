class Relation {

    private int indexA;
    private int indexB;
    private boolean greaterThan;

    public Relation(int indexA, int indexB, boolean greaterThan) {
        this.indexA = indexA;
        this.indexB = indexB;
        this.greaterThan = greaterThan;
    }

    public int getIndexA() {
        return indexA;
    }

    public int getIndexB() {
        return indexB;
    }

    public boolean isGreaterThan() {
        return greaterThan;
    }
}
