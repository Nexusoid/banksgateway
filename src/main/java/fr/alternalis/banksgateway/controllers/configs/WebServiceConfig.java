package fr.alternalis.banksgateway.controllers.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration made for the SOAP web service.
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soap/*");
    }

    @Bean(name = "banksgateway")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema banksGatewaySchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BanksGatewayPort");
        wsdl11Definition.setLocationUri("/banksgateway");
        wsdl11Definition.setTargetNamespace("http://www.alternalis.fr/banksgateway/controllers/soap");
        wsdl11Definition.setSchema(banksGatewaySchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema banksGatewaySchema() {
        return new SimpleXsdSchema(new ClassPathResource("/soap/banksgateway.xsd"));
    }

}
