package com.bstc.keeper;

/**
 * Created by Brian on 7/21/2015.
 * "Thing" refers to one item, such as a person, or event.
 */
public class Thing {
    protected String _name;
    protected int _iconResourceId;
    protected String _description;
    protected String _tags;

    public Thing(String name, int iconResourceId, String description, String tags){
        this._name = name;
        this._iconResourceId = iconResourceId;
        this._description = description;
        this._tags = tags;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_iconResourceId(int _iconResourceId) {
        this._iconResourceId = _iconResourceId;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public void set_tags(String _tags) {
        this._tags = _tags;
    }

    public String get_name() {
        return _name;
    }

    public int get_iconResourceId() {
        return _iconResourceId;
    }

    public String get_description() {
        return _description;
    }

    public String get_tags() {
        return _tags;
    }

    //getter methods
    /*
    public String getName(){return name;}
    public int getIconResourceId(){return iconResourceId;}
    public String getDescription(){return description;}
    public String[] getTags() {return tags;}
    */
}
