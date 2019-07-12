package itx.examples.springboot.simplerest.rest;

import itx.examples.springboot.simplerest.dto.SystemInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/data")
public class DataServiceRest {

    @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_VALUE )
    public SystemInfo getSystemInfo() {
        return new SystemInfo("spring-demo", "1.0.0", System.currentTimeMillis());
    }

}
