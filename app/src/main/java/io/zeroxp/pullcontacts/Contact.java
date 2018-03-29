package io.zeroxp.pullcontacts;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by parthbhavsar on 2018-03-29.
 */

public class Contact implements Parcelable {
    private String name;
    private String number;


    public Contact()
    {
        name = "";
        number = "";
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    //Parcelables

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {

        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };


    private Contact(Parcel in){

        this.name = in.readString();
        this.number = in.readString();


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(number);

    }
}
