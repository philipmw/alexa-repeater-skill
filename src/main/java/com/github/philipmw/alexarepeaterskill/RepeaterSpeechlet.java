package com.github.philipmw.alexarepeaterskill;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;

public class RepeaterSpeechlet implements Speechlet {
    private final Reprompt reprompt = new Reprompt();

    public RepeaterSpeechlet() {
        super();
        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText("Do you want me to repeat anything more?  Give me a word, date, time, duration, number, city, state, or name.");
        reprompt.setOutputSpeech(repromptSpeech);
    }

    public SpeechletResponse onLaunch(LaunchRequest request, Session session) {
        PlainTextOutputSpeech out = new PlainTextOutputSpeech();
        out.setText("Hello! Hello! Give me a word, date, time, duration, number, city, state, or name.");
        return SpeechletResponse.newAskResponse(out, reprompt);
    }

    public SpeechletResponse onIntent(IntentRequest request, Session session) {
        Integer utterancesCount = (Integer)session.getAttribute("utterancesCount");
        session.setAttribute("utterancesCount", ++utterancesCount);

        Intent intent = request.getIntent();
        if ("GetScoreIntent".equals(intent.getName())) {
            PlainTextOutputSpeech out = new PlainTextOutputSpeech();
            out.setText(String.format("Your score is %d.", utterancesCount));
            return SpeechletResponse.newAskResponse(out, reprompt);
        }
        else if ("DateIntent".equals(intent.getName())) {
            return processInput(intent.getSlot("date").getValue());
        }
        else if ("TimeIntent".equals(intent.getName())) {
            return processInput(intent.getSlot("time").getValue());
        }
        else if ("DurationIntent".equals(intent.getName())) {
            return processInput(intent.getSlot("duration").getValue());
        }
        else if ("NumberIntent".equals(intent.getName())) {
            return processInput(intent.getSlot("number").getValue());
        }
        else if ("CityIntent".equals(intent.getName())) {
            return processInput(intent.getSlot("city").getValue());
        }
        else if ("StateIntent".equals(intent.getName())) {
            return processInput(intent.getSlot("state").getValue());
        }
        else if ("FirstNameIntent".equals(intent.getName())) {
            return processInput(intent.getSlot("name").getValue());
        }
        else if ("WordIntent".equals(intent.getName())) {
            return processInput(intent.getSlot("word").getValue());
        }
        else if ("AMAZON.HelpIntent".equals(intent.getName())) {
            PlainTextOutputSpeech out = new PlainTextOutputSpeech();
            out.setText("Give me a word, date, time, duration, number, city, state, or name.");
            return SpeechletResponse.newAskResponse(out, reprompt);
        }
        else if ("AMAZON.StopIntent".equals(intent.getName()) || "AMAZON.CancelIntent".equals(intent.getName())) {
            PlainTextOutputSpeech out = new PlainTextOutputSpeech();
            out.setText(String.format("Your score is %d.  Goodbye. Goodbye.", utterancesCount));
            return SpeechletResponse.newTellResponse(out);
        }
        else {
            PlainTextOutputSpeech out = new PlainTextOutputSpeech();
            out.setText("I didn't recognize what you said. Give me a word, date, time, duration, number, city, state, or name.");
            return SpeechletResponse.newAskResponse(out, reprompt);
        }
    }

    private SpeechletResponse processInput(String input) {
        PlainTextOutputSpeech out = new PlainTextOutputSpeech();
        out.setText(input);
        return SpeechletResponse.newAskResponse(out, reprompt);
    }

    public void onSessionStarted(SessionStartedRequest request, Session session) {
        session.setAttribute("utterancesCount", 0);

    }
    public void onSessionEnded(SessionEndedRequest request, Session session) {

    }
}