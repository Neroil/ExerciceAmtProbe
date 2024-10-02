package ch.heigvd.amt;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.Instant;

//On doit ajouter des annotations pour que l'ORM reconnaisse les entités, utilisation de la réflexion !

//Le status est une mesure de l'état d'une sonde à un instant donné
@Entity
//@Table(name = "Status")
public class Status {
    @Id
    private Long id;

    @ManyToOne
    private Probe probe;

    private boolean isUp;
    private Instant timestamp;
    private Duration duration;
}
