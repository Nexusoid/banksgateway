package fr.alternalis.banksgateway.controllers.soap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

/**
 * Unit test made for the SOAP endpoint, this test is able to test the whole gateway because it even call GatewayService.
 * The bank need to be online in order to run the unit test of the gateway.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class BanksGatewaySoapControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testWSDL() throws Exception {
        this.webClient.get().uri("/soap/banksgateway.wsdl")
                .exchange().expectStatus().isOk();
    }

    @Test
    public void testCredit() throws Exception {
        this.webClient.post().uri("/soap/banksgateway")
                .accept(MediaType.TEXT_XML).contentType(MediaType.TEXT_XML).body(BodyInserters.fromValue(creditRequest))
                .exchange().expectStatus().isOk();
    }

    @Test
    public void testDebit() throws Exception {
        this.webClient.post().uri("/soap/banksgateway")
                .accept(MediaType.TEXT_XML).contentType(MediaType.TEXT_XML).body(BodyInserters.fromValue(debitRequest))
                .exchange().expectStatus().isOk();
    }

    private static final String creditRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
            "                  xmlns:gs=\"http://www.alternalis.fr/banksgateway/controllers/soap\">\n" +
            "    <soapenv:Header/>\n" +
            "    <soapenv:Body>\n" +
            "        <gs:creditForUserRequest>\n" +
            "            <gs:username>Tristan</gs:username>\n" +
            "            <gs:value>10</gs:value>\n" +
            "        </gs:creditForUserRequest>\n" +
            "    </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    private static final String debitRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
            "                  xmlns:gs=\"http://www.alternalis.fr/banksgateway/controllers/soap\">\n" +
            "    <soapenv:Header/>\n" +
            "    <soapenv:Body>\n" +
            "        <gs:debitForUserRequest>\n" +
            "            <gs:username>Tristan</gs:username>\n" +
            "            <gs:value>10</gs:value>\n" +
            "        </gs:debitForUserRequest>\n" +
            "    </soapenv:Body>\n" +
            "</soapenv:Envelope>";
}