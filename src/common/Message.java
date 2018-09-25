package common;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Valentin
 */
@XmlRootElement
public class Message {
    public Message() {
        type = "";
        payload = "";
    }
    public Message(String type_, String payload_) {
        type = type_;
        payload = payload_;
    }


    public String serialize() {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, sw);
        } catch (JAXBException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }
    
    public static Message parse(String s) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader sr = new StringReader(s);
            
            return (Message) jaxbUnmarshaller.unmarshal(sr);
        } catch (JAXBException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Message("badmsg", "");
    }

    @XmlElement
    public String type;
    @XmlElement
    public String payload;
}
