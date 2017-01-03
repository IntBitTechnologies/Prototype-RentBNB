package com.intbit.rentbnb.base;

/**
 * @author Sakthi
 */

public enum RentBnbEnums {
    Offers_View_Grid("Grid", 0),
    Offers_View_List("List", 1);

    private String stringValue;
    private int intValue;

    private RentBnbEnums(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public int toInt() {
        return intValue;
    }

}

