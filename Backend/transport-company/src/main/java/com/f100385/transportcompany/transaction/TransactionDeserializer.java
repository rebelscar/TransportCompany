package com.f100385.transportcompany.transaction;

import com.f100385.transportcompany.client.Client;
import com.f100385.transportcompany.client.ClientService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransactionDeserializer extends JsonDeserializer<Transaction> {

    @Autowired
    private ClientService clientService;

    /**
     * Deserializes an object from JSON
     * to a Transaction object.
     * Used for RESTful services.
     *
     * @param jsonParser The JSON Parser.
     * @param deserializationContext The deserialization context.
     * @return The deserialized Transaction.
     */
    @Override
    public Transaction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);
//        if (node.isNull() || node.isEmpty())
//            throw new

        Transaction transaction = new Transaction();

        //get the clientId from the json and set it to the transaction object
        int clientId = node.get("client").asInt();
        Client client = clientService.get(clientId);
        transaction.setClient(client);

        double moneyToPay = node.get("moneyToPay").asDouble();
        transaction.setMoneyToPay(moneyToPay);

        return transaction;
    }

}
