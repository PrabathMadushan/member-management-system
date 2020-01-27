package EnumForLists;

public enum WiseshaEkaka {

    STFS(1),
    PRABU_ARAKSHAKA_ANSHAYA(2),
    POLIS_ANU_KANDA(3);

    private final int nameAsKey;

    private WiseshaEkaka(int key) {
        this.nameAsKey = key;
    }

    public int toKey() {
        return this.nameAsKey;
    }
}
