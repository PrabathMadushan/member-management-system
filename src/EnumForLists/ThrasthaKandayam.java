package EnumForLists;

public enum ThrasthaKandayam {

    LTT(1), 
    JVP(2),
    PRACHANDA_CRIYA(3);
   
    private final int nameAsKey;

    private ThrasthaKandayam(int key) {
        this.nameAsKey = key;
    }

    public int toKey() {
        return this.nameAsKey;
    }
}
