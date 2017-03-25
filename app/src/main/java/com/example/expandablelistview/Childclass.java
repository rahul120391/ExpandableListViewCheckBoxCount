package com.example.expandablelistview;

/**
 * Created by rahul on 1/18/2015.
 */
public class Childclass
{
    private String name;
    private String state;
    private int count;
    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Childclass(String name, String state,int count) {
        this.name = name;
        this.state = state;
        this.count=count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
