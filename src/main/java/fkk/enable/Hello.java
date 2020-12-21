package fkk.enable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hello {
    private static final Logger logger=LoggerFactory.getLogger(Hello.class);
    public Hello(){
        logger.info("------Hello no params constructor init");
    }

}
