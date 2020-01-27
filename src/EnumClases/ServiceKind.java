package EnumClases;

public enum ServiceKind {

    UPASEWA("උප සේවා"), 
    NITHYASEWA("නිත්‍ය සේවා");
    private final String nameAsString;

    private ServiceKind(String name) {
        this.nameAsString = name;
    }

    @Override
    public String toString() {
        return this.nameAsString;
    }
}
