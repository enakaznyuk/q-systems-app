package ru.knastnt.springjmsibmmq.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knastnt.springjmsibmmq.entity.*;
import ru.knastnt.springjmsibmmq.mq.Sender;
import ru.knastnt.springjmsibmmq.entity.MessageJson;

@Service
public class WorkerRequestService {

    private final Gson gson = new GsonBuilder().create();

    @Autowired
    private Sender sender;

    public void addNewWorker(String worker) {

        MessageJson messageJson = new MessageJson();
        messageJson.setObjectEntity(ObjectEntity.WORKER);
        messageJson.setType(RequestType.POST);
        messageJson.setBody(gson.fromJson(worker, Worker.class));
        sender.sendMessage(gson.toJson(messageJson));
    }

    public void findById(Long id) {

        MessageJson messageJson = new MessageJson();
        messageJson.setObjectEntity(ObjectEntity.WORKER);
        messageJson.setType(RequestType.GET);
        messageJson.setId(id);
        sender.sendMessage(gson.toJson(messageJson));
    }

    public void findAll() {

        MessageJson messageJson = new MessageJson();
        messageJson.setObjectEntity(ObjectEntity.WORKER);
        messageJson.setType(RequestType.GET);
        sender.sendMessage(gson.toJson(messageJson));
    }

    public void linkPhonesToWorker(Long id, String phoneDtoList) {

        MessageJson messageJson = new MessageJson();
        messageJson.setObjectEntity(ObjectEntity.WORKER);
        messageJson.setType(RequestType.PATCH);
        messageJson.setId(id);
        messageJson.setBody(gson.fromJson(phoneDtoList, Object.class));
        sender.sendMessage(gson.toJson(messageJson));
    }
}