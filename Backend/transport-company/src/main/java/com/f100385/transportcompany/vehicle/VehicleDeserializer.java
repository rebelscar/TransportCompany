package com.f100385.transportcompany.vehicle;

import com.f100385.transportcompany.vehicle.vehicle_detail.VehicleDetail;
import com.f100385.transportcompany.vehicle.vehicle_detail.VehicleDetailService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class VehicleDeserializer extends JsonDeserializer<Vehicle> {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleDetailService vehicleDetailService;

    @Override
    public Vehicle deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode vehicleDetailNode = node.get("vehicleDetail");

        String detailBrand = vehicleDetailNode.get("brand").textValue();
        String detailModel = vehicleDetailNode.get("model").textValue();

        JsonNode manufacturedDateNode = vehicleDetailNode.get("manufactured");
        Date detailDate = objectMapper.readValue(manufacturedDateNode.toString(), Date.class);

        VehicleDetail vehicleDetail = new VehicleDetail(detailDate, detailBrand, detailModel);
        vehicleDetail = vehicleDetailService.add(vehicleDetail);
        Vehicle vehicle = new Vehicle(vehicleDetail);

        return vehicle;
    }
}

