package com.f100385.transportcompany.other.config;

import com.f100385.transportcompany.transaction.Transaction;
import com.f100385.transportcompany.transaction.TransactionSerializer;
import com.f100385.transportcompany.vehicle.Vehicle;
import com.f100385.transportcompany.vehicle.VehicleDeserializer;
import com.f100385.transportcompany.vehicle.transport_info.TransportInfo;
import com.f100385.transportcompany.vehicle.transport_info.TransportInfoDeserializer;
import com.f100385.transportcompany.vehicle.transport_info.TransportInfoSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Module dynamoDemoEntityDeserializer() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Vehicle.class, new VehicleDeserializer());
        module.addDeserializer(TransportInfo.class, new TransportInfoDeserializer());
        module.addSerializer(Transaction.class, new TransactionSerializer());
        module.addSerializer(TransportInfo.class, new TransportInfoSerializer());

        return module;
    }
}
