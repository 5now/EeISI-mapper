package it.infocert.eigor.api.configuration;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurableSupportTest {

    @Mock Configurable configurable;

    @Test
    public void shouldRaiseConfigurationExceptionWhenConfiguredTwice() throws ConfigurationException {

        // given
        ConfigurableSupport sut = new ConfigurableSupport(configurable);

        // when
        sut.configure();
        ConfigurationException ce = null;
        try {
            sut.configure();
            fail();
        } catch (ConfigurationException e) {
            ce = e;
        }

        // then
        assertThat(ce.getMessage(), Matchers.containsString("has been already configured."));

    }

    @Test(expected = IllegalStateException.class)
    public void shouldRaiseAnErrorIfNotConfigured() {

        // given
        ConfigurableSupport sut = new ConfigurableSupport(configurable);

        // then
        sut.checkConfigurationOccurred();

    }

    @Ignore
    public void shouldNotRaiseAnErrorIfConfigured() throws ConfigurationException {

        // given
        ConfigurableSupport sut = new ConfigurableSupport(configurable);

        // then
        sut.configure();
        sut.checkConfigurationOccurred();

    }

}