package fkk.enable.selector;

import fkk.enable.config.HelloConfig;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HelloImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                //"fkk.enable.config.HelloConfig"
                HelloConfig.class.getName()
        };
    }
}
