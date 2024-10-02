package ch.heigvd.amt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Probe {

    @Id
    private String url;

}
