package ch.heigvd.amt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Probe {

    @Id
    private String url;

    public Probe(String url) {
        this.url = url;
    }

    public Probe() {
        //Do nothing
    }

    public String getUrl() {
        return url;
    }
}
