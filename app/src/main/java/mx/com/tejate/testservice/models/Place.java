package mx.com.tejate.testservice.models;

import mx.com.tejate.testservice.models.value.Anchor;
import mx.com.tejate.testservice.models.value.Icon;
import mx.com.tejate.testservice.models.value.Title;

public class Place {

    private Anchor anchor;
    private Title text;
    private int id;
    private Icon icon;


    public Place(Anchor anchor, Title text, int id, Icon icon) {
        this.anchor = anchor;
        this.text = text;
        this.id = id;
        this.icon = icon;
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public Title getText() {
        return text;
    }

    public void setText(Title text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getCoordenadas() {
        return anchor.toString();
    }

    public String getTitle() {
        return  text.toString();
    }

    public String getUrl() {
        return icon.getUrl();
    }
}
