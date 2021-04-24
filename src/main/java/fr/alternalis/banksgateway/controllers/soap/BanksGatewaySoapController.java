package fr.alternalis.banksgateway.controllers.soap;

import fr.alternalis.banksgateway.iservices.IGatewayService;
import fr.alternalis.banksgateway.xml.request.TransactionRequestXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Endpoint
public class BanksGatewaySoapController {

    private static final String NAMESPACE_URI = "http://www.alternalis.fr/banksgateway/controllers/soap";

    private IGatewayService gatewayService;

    @Autowired
    public BanksGatewaySoapController(IGatewayService gatewayService){
        this.gatewayService = gatewayService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "creditForUserRequest")
    @ResponsePayload
    public GetCreditResponse makeCredit(@RequestPayload CreditForUserRequest request){
        GetCreditResponse response = new GetCreditResponse();
        TransactionRequestXml xmlRequest;
        Double valueDouble;
        try{
            valueDouble = Double.parseDouble(request.getValue());
        } catch(NumberFormatException e){
            response.setResponse("Indicated value incorrect.");
            return response;
        }
        if(valueDouble != 0){
            xmlRequest = new TransactionRequestXml(request.getUsername(), valueDouble.toString());
            Integer responseInt = gatewayService.makeBanksCall(xmlRequest);
            if(responseInt == 0){
                response.setResponse("Successfully credited user.");
            }
            if(responseInt == 1){
                response.setResponse("The bank was unable to find the user.");
            }
            if(responseInt == 2){
                response.setResponse("Value was not accepted by the bank.");
            }
        } else {
            response.setResponse("Can't make a credit for a value of 0.");
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "debitForUserRequest")
    @ResponsePayload
    public GetDebitResponse makeDebit(@RequestPayload DebitForUserRequest request){
        GetDebitResponse response = new GetDebitResponse();
        Double valueDouble;
        TransactionRequestXml xmlRequest;
        try{
            valueDouble = Double.parseDouble(request.getValue());
            valueDouble = valueDouble * -1;
            valueDouble = BigDecimal.valueOf(valueDouble).setScale(2, RoundingMode.HALF_UP).doubleValue();
        } catch (NumberFormatException e){
            response.setResponse("Indicated value incorrect.");
            return response;
        }
        if(valueDouble != 0){
            xmlRequest = new TransactionRequestXml(request.getUsername(), valueDouble.toString());
            Integer responseInt = gatewayService.makeBanksCall(xmlRequest);
            if(responseInt == 0){
                response.setResponse("Successfully credited user.");
            }
            if(responseInt == 1){
                response.setResponse("The bank was unable to find the user.");
            }
            if(responseInt == 2){
                response.setResponse("Value was not accepted by the bank.");
            }
        } else {
            response.setResponse("Can't make a debit for a value of 0.");
        }
        return response;
    }
}
