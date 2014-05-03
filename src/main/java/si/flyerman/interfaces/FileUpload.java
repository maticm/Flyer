package si.flyerman.interfaces;

import java.io.File;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.MTOM;
import org.jvnet.staxex.StreamingDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
@MTOM
public class FileUpload {
    static final Logger log = LoggerFactory.getLogger(FileUpload.class);
    private FileUpload(){}
    // TODO: everything.
    public void fileUpload(String name, @XmlMimeType("application/octet-stream") DataHandler data){
        try {
            StreamingDataHandler sdh = (StreamingDataHandler)data;
            File file = File.createTempFile(name, "");
            sdh.moveTo(file);
            sdh.close();
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
