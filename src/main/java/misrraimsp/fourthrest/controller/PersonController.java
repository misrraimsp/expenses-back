package misrraimsp.fourthrest.controller;

import lombok.RequiredArgsConstructor;
import misrraimsp.fourthrest.service.PersonServer;
import misrraimsp.fourthrest.util.dto.PersonDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonServer personServer;

    @GetMapping("/people")
    public List<PersonDTO> allPeople() {
        return personServer.findAll();
    }
}
