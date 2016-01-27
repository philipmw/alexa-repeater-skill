package com.github.philipmw.alexarepeaterskill;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public class RepeaterSpeechletRequestHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;

    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.echo-sdk-ams.app.67962976-02b9-4f5a-bc6c-6eb33ba16030");
    }

    public RepeaterSpeechletRequestHandler() {
        super(new RepeaterSpeechlet(), supportedApplicationIds);
    }

    public RepeaterSpeechletRequestHandler(Speechlet speechlet,
                                           Set<String> supportedApplicationIds) {
        super(speechlet, supportedApplicationIds);
    }
}