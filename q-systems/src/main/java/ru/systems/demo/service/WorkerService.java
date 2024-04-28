package ru.systems.demo.service;

import org.modelmapper.ModelMapper;
import ru.systems.demo.dto.PhoneDto;
import ru.systems.demo.dto.WorkerDto;
import ru.systems.demo.entity.Phone;
import ru.systems.demo.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.systems.demo.exception.InvalidException;
import ru.systems.demo.repository.PhoneRepository;
import ru.systems.demo.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Long addNewWorker(WorkerDto worker) {

        Worker workerAfterDto = modelMapper.map(worker, Worker.class);
        workerRepository.save(workerAfterDto);
        return workerAfterDto.getId();
    }

    public WorkerDto findById(Long id) {

        Optional<Worker> worker = workerRepository.findById(id);
        return worker.map(value -> modelMapper.map(value, WorkerDto.class))
                .orElse(null);
    }

    public List<WorkerDto> findAll() {

        Iterable<Worker> workerIterable = workerRepository.findAll();
        List<Worker> workers = StreamSupport.stream(workerIterable.spliterator(), false).toList();
        return workers.stream()
                .map(v -> modelMapper.map(v, WorkerDto.class))
                .toList();
    }

    public void linkPhonesToWorker(Long id, PhoneDto[] phoneDtoList) {

        Optional<Worker> worker = workerRepository.findById(id);
        if (worker.isEmpty())
            throw new InvalidException("Error in creating the User resource. Try Again.");

        Set<Phone> newPhones = worker.get().getPhones();

        for (PhoneDto dto : phoneDtoList) {
            Optional<Phone> phoneFromDb = phoneRepository.findById(dto.getId());
            if (phoneFromDb.isEmpty()) {
                throw new InvalidException("Error in creating the User resource. Try Again.");
            }
            newPhones.add(phoneFromDb.get());
        }
        worker.get().setPhones(newPhones);
        workerRepository.save(worker.get());
    }
}

