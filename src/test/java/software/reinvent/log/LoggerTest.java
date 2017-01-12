package software.reinvent.log;


import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
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

    private Logger log = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    private Appender<ILoggingEvent> mockAppender;
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @Before
    public void setUp(Appender<ILoggingEvent> appender, ArgumentCaptor<LoggingEvent> captor) throws Exception {
        mockAppender = appender;
        this.captorLoggingEvent = captor;

        log.addAppender(mockAppender);
    }

    @After
    public void tearDown() throws Exception {
        log.detachAppender(mockAppender);
    }


    @Test
    public void testInjectedLogger() throws Exception {
        injectedLogger.info("Just a {}.", "test");

        verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
        final LoggingEvent loggingEvent = captorLoggingEvent.getValue();
        assertThat(loggingEvent.getFormattedMessage()).isEqualTo("Just a test.");
    }
}