package hello.springmvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MappingController {
//test2
    @GetMapping(value = "/mapping/{userid}/{itemnumber}", headers = "mode=debug", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingPath(@PathVariable("userid") String userid_param,
                              @PathVariable("itemnumber") String itemnumber_param)
    {
        log.error("userid = {}, itemnumber = {}", userid_param, itemnumber_param);

        return  "ok";

    }

}
