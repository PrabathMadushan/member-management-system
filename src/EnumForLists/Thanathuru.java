package EnumForLists;

public enum Thanathuru {

    POLIS_KOSTHAPAL(1),
    PLOLIS_SARAYAN(2),
    UPA_POLIS_PARIKSHAKA(3),
    POLIS_PARIKSHAKA(4),
    PRADANA_POLIS_PARIKSHAKA(5),
    SAHAKARA_POLIS_ADIKARI(6),
    JESHTA_POLIS_ADIKARI(7),
    NIYOJYA_POLIS_PATHI(8);

    private final int nameAsKey;

    private Thanathuru(int key) {
        this.nameAsKey = key;
    }

    public int toKey() {
        return this.nameAsKey;
    }
}
