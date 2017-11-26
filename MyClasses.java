package com.example.david.listview;

/**
 * Created by David on 25.11.2017.
 */

public class MyClasses {
    public String kod;
    public String name;
    public String teacher;
    public String type;

    public MyClasses(String kod, String name, String teacher, String type)
    {
        this.kod=kod;
        this.name=name;
        this.teacher=teacher;
        this.type=type;
    }

    public String getKod()
    {
        return kod;
    }

    public void setKod(String kod)
    {
        this.kod=kod;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public void setTeacher(String teacher)
    {
        this.teacher=teacher;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type=type;
    }
}
