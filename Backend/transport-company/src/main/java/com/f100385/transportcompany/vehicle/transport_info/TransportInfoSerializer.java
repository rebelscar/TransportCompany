package com.f100385.transportcompany.vehicle.transport_info;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransportInfoSerializer extends JsonSerializer<TransportInfo> {

    @Override
    public void serialize(TransportInfo info, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", info.getId());
        jsonGenerator.writeStringField("destination", info.getDestination());
        jsonGenerator.writeStringField("cargo", info.getCargo());
        jsonGenerator.writeNumberField("cost", info.getCost());
        jsonGenerator.writeNumberField("vehicle", info.getVehicle().getId());

        // include other fields as necessary
        jsonGenerator.writeEndObject();
    }

}