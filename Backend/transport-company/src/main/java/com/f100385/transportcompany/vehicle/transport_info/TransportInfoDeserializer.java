package com.f100385.transportcompany.vehicle.transport_info;

import com.f100385.transportcompany.client.Client;
import com.f100385.transportcompany.transaction.Transaction;
import com.f100385.transportcompany.vehicle.Vehicle;
import com.f100385.transportcompany.vehicle.VehicleService;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransportInfoDeserializer extends JsonDeserializer<TransportInfo> {

    @Autowired
    private VehicleService vehicleService;

    @Override
    public TransportInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);

        TransportInfo transportInfo = new TransportInfo();

        //get the vehicleId from the json and set it to the transaction object
        int vehicleId = node.get("vehicle").asInt();
        Vehicle vehicle = vehicleService.get(vehicleId);
        transportInfo.setVehicle(vehicle);

        transportInfo.setDestination(node.get("destination").asText());
        transportInfo.setCargo(node.get("cargo").asText());
        transportInfo.setCost(node.get("cost").asDouble());

        return transportInfo;
    }
}
