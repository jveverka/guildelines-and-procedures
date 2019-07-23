package itx.examples.springboot.simplerest.rest;

import itx.examples.springboot.simplerest.dto.SystemInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "/data")
public class DataServiceRest {

    private static final String NAME_VERSION = "simple-rest:1.0.0";

    private final String instanceId;
    private final AtomicLong requestCounter;

    public DataServiceRest() {
        this.instanceId = UUID.randomUUID().toString();
        this.requestCounter = new AtomicLong(0);
    }

    @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_VALUE )
    public SystemInfo getSystemInfo() {
        return new SystemInfo(NAME_VERSION, System.currentTimeMillis(), instanceId, requestCounter.incrementAndGet());
    }

}
