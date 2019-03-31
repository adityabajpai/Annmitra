package com.example.asus.annmitra2.Models;

/**
 * Created by asus on 9/22/2018.
 */

public class Contacts
{
    private String name, officerRank, mobileno,area;

    public Contacts(String name, String officerRank, String mobileno,String area)
    {
        this.name = name;
        this.officerRank = officerRank;
        this.mobileno = mobileno;
        this.area=area;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOfficerRank()
    {
        return officerRank;
    }

    public void setOfficerRank(String officerRank)
    {
        this.officerRank = officerRank;
    }

    public String getMobileno()
    {
        return mobileno;
    }

    public void setMobileno(String mobileno)
    {
        this.mobileno = mobileno;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
