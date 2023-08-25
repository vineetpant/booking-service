package com.oli.booking.models.enums;

public enum Currency {
    EURO, USD, INR;

    public static float getEuroConversionRate(Currency currency) {
        switch (currency) {
            case EURO:
                return 1;
            case INR:
                return 90;
            case USD:
                return (float) 1.1;
            default:
                return 1;
        }
    }

    public static float getINRConversionRate(Currency currency) {
        switch (currency) {
            case EURO:
                return (float) .011;
            case INR:
                return 1;
            case USD:
                return (float) 0.012;
            default:
                return 1;
        }
    }

    public static float getUSDConversionRate(Currency currency) {
        switch (currency) {
            case EURO:
                return (float) .92;
            case INR:
                return 82;
            case USD:
                return 1;
            default:
                return 1;
        }
    }
}
