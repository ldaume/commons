package software.reinvent.log;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 11.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectLogger {
}