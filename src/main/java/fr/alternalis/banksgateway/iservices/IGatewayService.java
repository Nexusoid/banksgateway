package fr.alternalis.banksgateway.iservices;

import fr.alternalis.banksgateway.xml.request.TransactionRequestXml;

/**
 * Interface for the Gateway service.
 */
public interface IGatewayService {

    /**
     * Function that make a request to the bank.
     * @param request is the request sent to the bank.
     * @return An Integer that indicate how answered the bank.
     */
    Integer makeBanksCall(TransactionRequestXml request);

}
