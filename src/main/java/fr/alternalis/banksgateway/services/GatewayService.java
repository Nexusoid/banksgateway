package fr.alternalis.banksgateway.services;

import fr.alternalis.banksgateway.iservices.IGatewayService;
import fr.alternalis.banksgateway.xml.request.TransactionRequestXml;
import fr.alternalis.banksgateway.xml.response.ResponseXml;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GatewayService implements IGatewayService {

    public static final String BANKS_SERVER = "http://localhost:8080/rest/xml/user/transaction";

    @Override
    public Integer makeBanksCall(TransactionRequestXml request) {
        RestTemplate template = new RestTemplate();

        ResponseXml response = template.postForObject(BANKS_SERVER, request, ResponseXml.class);
        return response.getValue();
    }

}
