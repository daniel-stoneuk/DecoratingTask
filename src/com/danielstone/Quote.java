package com.danielstone;


public class Quote {

    public static int UNDERLAY_FIRST_STEP = 0;
    public static int UNDERLAY_MONARCH = 1;
    public static int UNDERLAY_ROYAL = 2;

    private int customerReference = -1;
    private int roomWidthCM = 0;
    private int roomLengthCM = 0;
    private int underlayType = -1;

    public Quote(int customerReference, int roomWidthCM, int roomLengthCM, int underlayType) {
        this.customerReference = customerReference;
        this.roomWidthCM = roomWidthCM;
        this.roomLengthCM = roomLengthCM;
        this.underlayType = underlayType;
    }

    public Quote() {

    }

    public int getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(int customerReference) {
        this.customerReference = customerReference;
    }

    public int getRoomWidthCM() {
        return roomWidthCM;
    }

    public void setRoomWidthCM(int roomWidthCM) {
        this.roomWidthCM = roomWidthCM;
    }

    public int getRoomLengthCM() {
        return roomLengthCM;
    }

    public void setRoomLengthCM(int roomLengthCM) {
        this.roomLengthCM = roomLengthCM;
    }

    public int getUnderlayType() {
        return underlayType;
    }

    public void setUnderlayType(int underlayType) {
        this.underlayType = underlayType;
    }
}
