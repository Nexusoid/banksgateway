package fr.alternalis.banksgateway.iservices;

import fr.alternalis.banksgateway.xml.request.TransactionRequestXml;

public interface IGatewayService {

    Integer makeBanksCall(TransactionRequestXml request);

}
