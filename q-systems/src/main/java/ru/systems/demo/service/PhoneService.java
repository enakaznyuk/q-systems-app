package ru.systems.demo.service;

import org.modelmapper.ModelMapper;
import ru.systems.demo.dto.PhoneDto;
import ru.systems.demo.entity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.systems.demo.repository.PhoneRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void addNewInfo(PhoneDto phoneDto) {

        Phone phoneAfterDto = modelMapper.map(phoneDto, Phone.class);
        phoneRepository.save(phoneAfterDto);
    }

    public PhoneDto findById(Long id) {

        Optional<Phone> phone = phoneRepository.findById(id);
        return phone.map(value -> modelMapper.map(value, PhoneDto.class))
                .orElse(null);
    }

    public List<PhoneDto> findAll() {

        Iterable<Phone> phoneIterable = phoneRepository.findAll();
        List<Phone> phones = StreamSupport.stream(phoneIterable.spliterator(), false).toList();

        return phones.stream()
                .map(v -> modelMapper.map(v, PhoneDto.class))
                .toList();
    }
}
