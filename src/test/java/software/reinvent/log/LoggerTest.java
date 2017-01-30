package software.reinvent.log;


import com.google.inject.Inject;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created on 11.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
@RunWith(JukitoRunner.class)
@UseModules(LoggerModule.class)
public class LoggerTest {

    @InjectLogger
    private org.slf4j.Logger injectedLogger;

    private Logger rootLogger;

    @Inject
    private Appender mockedAppender;


    private ArgumentCaptor<Appender> argumentCaptor = ArgumentCaptor.forClass(Appender.class);

    @Before
    public void setUp() throws Exception {
        rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.addAppender(mockedAppender);
    }

    @After
    public void tearDown() throws Exception {
        rootLogger.detachAppender(mockedAppender);
    }


    @Test
    public void testInjectedLogger() throws Exception {
        injectedLogger.info("Just a {}.", "test");

        verify(mockedAppender).doAppend(argumentCaptor.capture());
        assertThat(((LoggingEvent) argumentCaptor.getAllValues().get(0)).getFormattedMessage()).isEqualTo("Just a test.");

    }
}
