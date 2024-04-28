package ru.knastnt.springjmsibmmq.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.knastnt.springjmsibmmq.service.WorkerRequestService;

import java.rmi.ServerException;

@RestController
@RequestMapping(path = "/workers")
public class WorkerRequestController {

    @Autowired
    private WorkerRequestService workerRequestService;

    @GetMapping
    public void getAllWorkers() {

        workerRequestService.findAll();
    }

    @GetMapping(path = "/{id}")
    public void getWorkerById(@PathVariable Long id) {

        workerRequestService.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addWorker(@RequestBody String newWorker) throws ServerException {

        workerRequestService.addNewWorker(newWorker);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}/phones")
    public ResponseEntity<Void> linkPhonesToWorker(@PathVariable Long id, @RequestBody @NotNull @NotEmpty @Valid String phoneDtoList) throws ServerException {

        workerRequestService.linkPhonesToWorker(id, phoneDtoList);
        return ResponseEntity.noContent().build();
    }
}
