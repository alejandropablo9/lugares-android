package mx.com.tejate.testservice.models.value;

public class Anchor {

    private Geolocation geolocation;

    public Anchor(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    @Override
    public String toString() {
        return geolocation.toString();
    }
}
