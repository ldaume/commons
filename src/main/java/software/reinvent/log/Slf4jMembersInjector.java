package software.reinvent.log;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.MembersInjector;

/**
 * Created on 11.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class Slf4jMembersInjector<T>  implements MembersInjector<T> {
    private final Field field;
    private final Logger logger;

    Slf4jMembersInjector(Field aField) {
        field = aField;
        logger = LoggerFactory.getLogger(field.getDeclaringClass());
        field.setAccessible(true);
    }

    public void injectMembers(T anArg0) {
        try {
            field.set(anArg0, logger);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}