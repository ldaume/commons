package software.reinvent.log;


import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import org.slf4j.Logger;

import java.lang.reflect.Field;

/**
 * Created on 11.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class Slf4jTypeListener implements TypeListener {

    public <I> void hear(TypeLiteral<I> aTypeLiteral, TypeEncounter<I> aTypeEncounter) {

        for (Field field : aTypeLiteral.getRawType().getDeclaredFields()) {
            if (field.getType() == Logger.class
                    && field.isAnnotationPresent(InjectLogger.class)) {
                aTypeEncounter.register(new Slf4jMembersInjector<>(field));
            }
        }
    }
}