package fkk.enable.annotation;

import fkk.enable.selector.HelloImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloImportSelector.class)
public @interface EnableHello {
}
