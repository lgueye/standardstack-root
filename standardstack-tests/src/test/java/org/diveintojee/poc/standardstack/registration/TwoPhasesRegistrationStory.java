package org.diveintojee.poc.standardstack.registration;

import com.google.common.collect.Lists;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;

import java.util.List;

/**
 * @author louis.gueye@gmail.com
 */
public class TwoPhasesRegistrationStory extends JUnitStories {

    @Override
    public Configuration configuration() {
        Configuration configuration = new MostUsefulConfiguration();
        configuration.storyReporterBuilder() // Configure report builder
                .withFormats(Format.HTML_TEMPLATE, Format.ANSI_CONSOLE) // Configure
                // desired
                // output
                // formats
                .withFailureTrace(true) //
                .withMultiThreading(true);
        configuration.storyControls().doSkipScenariosAfterFailure(false).doResetStateBeforeStory(true);
        return configuration;
    }

    @Override
    protected List<String> storyPaths() {
        return Lists.newArrayList("two_phases_registration.story");
    }
}
