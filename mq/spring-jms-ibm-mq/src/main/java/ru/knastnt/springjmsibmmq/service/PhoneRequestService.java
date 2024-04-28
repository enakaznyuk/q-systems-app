package ru.knastnt.springjmsibmmq.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knastnt.springjmsibmmq.entity.*;
import ru.knastnt.springjmsibmmq.mq.Sender;

@Service
public class PhoneRequestService {

    private final Gson gson = new GsonBuilder().create();

    @Autowired
    private Sender sender;

    public void addNewInfo(String phoneDto) {

        MessageJson messageJson = new MessageJson();
        messageJson.setObjectEntity(ObjectEntity.PHONE);
        messageJson.setType(RequestType.POST);
        messageJson.setBody(gson.fromJson(phoneDto, Phone.class));
        sender.sendMessage(gson.toJson(messageJson));
    }

    public void findById(Long id) {

        MessageJson messageJson = new MessageJson();
        messageJson.setObjectEntity(ObjectEntity.PHONE);
        messageJson.setType(RequestType.GET);
        messageJson.setId(id);
        sender.sendMessage(gson.toJson(messageJson));
    }

    public void findAll() {

        MessageJson messageJson = new MessageJson();
        messageJson.setObjectEntity(ObjectEntity.PHONE);
        messageJson.setType(RequestType.GET);
        sender.sendMessage(gson.toJson(messageJson));
    }
}
