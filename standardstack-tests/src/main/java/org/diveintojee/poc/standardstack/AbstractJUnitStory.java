package org.diveintojee.poc.standardstack;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Responsible to:
 * - create a jbehave execution context for the scope of the story
 * - load the story based on the class name using an underscore camel case strategy
 * - create a steps factory managed by spring
 *
 * @author louis.gueye@gmail.com
 */
public class AbstractJUnitStory extends JUnitStory {

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
        configuration.storyControls()
                .doSkipScenariosAfterFailure(false)
                .doResetStateBeforeStory(true);
        configuration.useStoryPathResolver(new UnderscoredCamelCaseResolver());
        return configuration;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), new AnnotationConfigApplicationContext(StepsConfig.class));
    }
}
