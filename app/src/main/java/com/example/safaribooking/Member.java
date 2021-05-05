package com.example.safaribooking;

import android.widget.Button;

public class Member {
    private long tktKeyValue;
    private String park;
    private String customerType;
    private int fullTicket;
    private int halfTicket;
    private String date;
    private double fulltktAmount;
    private double halftktAmount;
    private double totalAmount;



    public Member(long tktKeyValue,String park, String customerType, int fullTicket, int halfTicket, String date, double fulltktAmount, double halftktAmount, double totalAmount) {
        this.tktKeyValue = tktKeyValue;
        this.park = park;
        this.customerType = customerType;
        this.fullTicket = fullTicket;
        this.halfTicket = halfTicket;
        this.date = date;
        this.fulltktAmount = fulltktAmount;
        this.halftktAmount = halftktAmount;
        this.totalAmount = totalAmount;
    }

    public Member(){

    }
    public long getTktKeyValue() {
        return tktKeyValue;
    }

    public String getPark() {
        return park;
    }

    public String getCustomerType() {
        return customerType;
    }

    public int getFullTicket() {
        return fullTicket;
    }

    public int getHalfTicket() {
        return halfTicket;
    }

    public String getDate() {
        return date;
    }

    public double getFulltktAmount() {
        return fulltktAmount;
    }

    public double getHalftktAmount() {
        return halftktAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

}
