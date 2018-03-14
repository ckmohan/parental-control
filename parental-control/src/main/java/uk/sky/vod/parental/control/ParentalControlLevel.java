package uk.sky.vod.parental.control;

import static java.util.EnumSet.allOf;

public enum ParentalControlLevel {

    U(0, "U"), PG(7, "PG"),
    TWELVE(12, "12"), FIFTEEN(15, "15"),
    EIGHTEEN(18, "18");

    private final int minAge;
    private final String code;

    ParentalControlLevel(final int minAge, final String code) {
        this.code = code;
        this.minAge = minAge;
    }

    public static ParentalControlLevel fromCode(final String code) {
        return allOf(ParentalControlLevel.class).stream().filter(parentalControlLevel -> parentalControlLevel.getCode()
                .equals(code)).findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid code : " + code));
    }

    public int getMinAge() {
        return minAge;
    }

    public String getCode() {
        return code;
    }
}
