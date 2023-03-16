package com.f100385.transportcompany.transaction;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransactionSerializer extends JsonSerializer<Transaction> {

    @Override
    public void serialize(Transaction transaction, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", transaction.getId());
        jsonGenerator.writeNumberField("moneyToPay", transaction.getMoneyToPay());
        jsonGenerator.writeNumberField("client", transaction.getClient().getId());
        // include other fields as necessary
        jsonGenerator.writeEndObject();
    }

}
