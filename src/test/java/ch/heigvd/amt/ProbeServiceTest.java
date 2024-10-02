package ch.heigvd.amt;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject; //Supporté par Quarkus
import org.junit.jupiter.api.Assertions;

@QuarkusTest
public class ProbeServiceTest {

    @Inject
    ProbeService probeService;

    @Test
    void testCreationProbe() {
        var probe = new Probe("http://example.com");
        probeService.createProbeIfNotExists(probe);

        var probe2 = new Probe("http://example.com");
        probeService.createProbeIfNotExists(probe2);

        Assertions.assertEquals(1, probeService.listProbes().size());
    }

    @Test
    void testComputeStatus() {
        var probe = new Probe("https://httpstat.us/200");
        probeService.createProbeIfNotExists(probe);

        var status = probeService.computeStatus(probe);

        Assertions.assertTrue(status.isUp());

    }

}
