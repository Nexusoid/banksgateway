//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.2 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2021.04.24 à 06:53:02 PM CEST 
//


package fr.alternalis.banksgateway.controllers.soap;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.alternalis.banksgateway.controllers.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.alternalis.banksgateway.controllers.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DebitForUserRequest }
     * 
     */
    public DebitForUserRequest createDebitForUserRequest() {
        return new DebitForUserRequest();
    }

    /**
     * Create an instance of {@link CreditForUserRequest }
     * 
     */
    public CreditForUserRequest createCreditForUserRequest() {
        return new CreditForUserRequest();
    }

    /**
     * Create an instance of {@link GetDebitResponse }
     * 
     */
    public GetDebitResponse createGetDebitResponse() {
        return new GetDebitResponse();
    }

    /**
     * Create an instance of {@link GetCreditResponse }
     * 
     */
    public GetCreditResponse createGetCreditResponse() {
        return new GetCreditResponse();
    }

}
