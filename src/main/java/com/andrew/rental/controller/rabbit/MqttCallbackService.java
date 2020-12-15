package com.andrew.rental.controller.rabbit;

import com.andrew.rental.dto.MqttMessageType;
import com.andrew.rental.model.User;
import com.andrew.rental.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.var;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class MqttCallbackService implements MqttCallback {
    @Autowired
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        if (s.equalsIgnoreCase("r/user")) {
            var typeRef = new TypeReference<HashMap<String, Object>>() {};
            HashMap<String, Object> hashMap = mapper.
                    readValue(mqttMessage.getPayload(), typeRef);
            MqttMessageType message_type = MqttMessageType.valueOf(hashMap.
                    get("message_type").toString());
            switch (message_type) {
                case ADD:
                    handleAddRequest(hashMap.get("payload"));
                    break;
                case DELETE:
                    handleDeleteRequest(hashMap.get("payload"));
                    break;
                default:
                    throw new IllegalArgumentException("Message type not supported");
            }
        }
    }

    private void handleDeleteRequest(Object payload) throws NotFoundException {
        String id = mapper.convertValue(payload, String.class);
        userService.deleteUserById(UUID.fromString(id));
    }

    private void handleAddRequest(Object payload) {
        User user = mapper.convertValue(payload, User.class);
        userService.addUser(user);
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
