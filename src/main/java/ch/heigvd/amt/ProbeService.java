package ch.heigvd.amt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@ApplicationScoped //Indique que la classe soit injectable dans le test
public class ProbeService {

    //Pour faire de la persistance !
    @Inject
    EntityManager em; //Lui, il a une API qui récupère les entités et les sauvegarde dans la base de données !

    List<Probe> listProbes() {
        return em.createQuery("SELECT p FROM Probe p", Probe.class).getResultList();
    }

    //Implémenter la persistance dans ce service
    @Transactional
    Probe createProbeIfNotExists(@Valid Probe probe){

        var persistedProbe = em.find(Probe.class, probe.getUrl());

        if(persistedProbe != null){
            return persistedProbe;
        }

        em.persist(probe);
        return probe;
    }

    Status getLastStatus(String url){
        return null;
    }

    List<Status> getStatusList(String url, int count){
        return null;
    }

    Status executeProbe(Probe probe){
        return null;
    }


    @Transactional
    Status computeStatus(Probe probe){
        var url = URI.create(probe.getUrl());
        var timeout = 1000;
        var start = Instant.now();

        boolean isUp = true;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.toURL().openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                isUp = false;
            }
        } catch (IOException exception) {
            isUp = false;
        }

        var end = Instant.now();

        var duration = Duration.between(start, end);
        var status = new Status(probe, isUp, start, duration);

        em.persist(status);

        return status;

    }
}
