package ru.knastnt.springjmsibmmq.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.knastnt.springjmsibmmq.service.PhoneRequestService;

import java.rmi.ServerException;

@RestController
@RequestMapping(path = "/phones")
public class PhoneRequestController {

    @Autowired
    private PhoneRequestService phoneRequestService;

    @GetMapping
    public @ResponseBody void getAllPhones() {

        phoneRequestService.findAll();
    }

    @GetMapping(path = "/{id}")
    public void getPhoneById(@PathVariable Long id) {

        phoneRequestService.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPhone(@RequestBody String newPhone) throws ServerException {

        phoneRequestService.addNewInfo(newPhone);
        return ResponseEntity.noContent().build();
    }
}
