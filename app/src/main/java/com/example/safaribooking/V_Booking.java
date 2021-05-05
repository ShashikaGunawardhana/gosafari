package com.example.safaribooking;

public class V_Booking {
    
    String Seat4,Seat6,Seat8;
    String date,place;
    Double fullamount;
    int Tot_vehicle;
    public V_Booking() {
    }

    public V_Booking(String seat4, String seat6, String seat8, String date, String place, int Tot_vehicle , double fullamount ) {
        Seat4 = seat4;
        Seat6 = seat6;
        Seat8 = seat8;
        this.date = date;
        this.place = place;
        this.Tot_vehicle=Tot_vehicle;
        this.fullamount=fullamount;
    }

    public int getTot_vehicle() {
        return Tot_vehicle;
    }

    public void setTot_vehicle(int tot_vehicle) {
        Tot_vehicle = tot_vehicle;
    }

    public Double getFullamount() {
        return fullamount;
    }

    public void setFullamount(Double fullamount) {
        this.fullamount = fullamount;
    }

    public String getSeat4() {
        return Seat4;
    }

    public void setSeat4(String seat4) {
        Seat4 = seat4;
    }

    public String getSeat6() {
        return Seat6;
    }

    public void setSeat6(String seat6) {
        Seat6 = seat6;
    }

    public String getSeat8() {
        return Seat8;
    }

    public void setSeat8(String seat8) {
        Seat8 = seat8;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
