package ru.systems.demo.message;

public class MessageJson {

    private RequestType requestType;
    private ObjectEntity objectEntity;
    private Long id;
    private Object body;

    public RequestType getType() {
        return requestType;
    }

    public void setType(RequestType requestType) {
        this.requestType = requestType;
    }

    public ObjectEntity getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(ObjectEntity objectEntity) {
        this.objectEntity = objectEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MessageJson{" +
                "requestType=" + requestType +
                ", objectEntity=" + objectEntity +
                ", id=" + id +
                ", body=" + body +
                '}';
    }
}
