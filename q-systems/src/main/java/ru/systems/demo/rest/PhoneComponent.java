package ru.systems.demo.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.systems.demo.dto.PhoneDto;
import ru.systems.demo.message.MessageJson;
import ru.systems.demo.service.PhoneService;

@Component
public class PhoneComponent {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private ModelMapper modelMapper;

    public void getMessage(MessageJson messageJson) {
        switch (messageJson.getType()) {
            case POST:
                PhoneDto phoneDto = modelMapper.map(messageJson.getBody(), PhoneDto.class);
                addPhone(phoneDto);
                break;
            case GET:
                if (messageJson.getId() != null)
                    getPhoneById(messageJson.getId());
                else
                    getAllPhones();
                break;
        }
    }

    public void getAllPhones() {

        phoneService.findAll();
    }

    public void getPhoneById(Long id) {

        phoneService.findById(id);
    }

    public void addPhone(PhoneDto newPhone) {

        phoneService.addNewInfo(newPhone);
    }
}
