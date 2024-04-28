package ru.systems.demo.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.systems.demo.dto.PhoneDto;
import ru.systems.demo.dto.WorkerDto;
import org.springframework.beans.factory.annotation.Autowired;
import ru.systems.demo.message.MessageJson;
import ru.systems.demo.service.WorkerService;

@Component
public class WorkerComponent {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private ModelMapper modelMapper;

    public void getMessage(MessageJson messageJson) {

        switch (messageJson.getType()) {
            case POST:
                WorkerDto workerDto = modelMapper.map(messageJson.getBody(), WorkerDto.class);
                addWorker(workerDto);
                break;
            case GET:
                if (messageJson.getId() != null)
                    getWorkerById(messageJson.getId());
                else
                    getAllWorkers();
                break;
            case PATCH:
                PhoneDto[] phoneDtos =  modelMapper.map(messageJson.getBody(), PhoneDto[].class);
                linkPhonesToWorker(messageJson.getId(), phoneDtos);
                break;
        }
    }

    public void getAllWorkers() {

        workerService.findAll();
    }

    public void getWorkerById(Long id) {

        workerService.findById(id);
    }

    public void addWorker(WorkerDto newWorker) {

        workerService.addNewWorker(newWorker);
    }

    public void linkPhonesToWorker(Long id, @NotNull @NotEmpty @Valid PhoneDto[] phoneDtoList) {

        workerService.linkPhonesToWorker(id, phoneDtoList);
    }
}
