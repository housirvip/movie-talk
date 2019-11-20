package edu.uta.movietalk.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hxy
 */
@Getter
@Setter
@ToString
public class TisanceParseSetting {

    public TisanceParseSetting() {
        this.abuse = Boolean.FALSE;
        this.deterministic = Boolean.FALSE;
        this.document_sentiment = Boolean.FALSE;
        this.entities = Boolean.FALSE;
        this.fetch_definitions = Boolean.FALSE;
        this.parses = Boolean.FALSE;
        this.sentiment = Boolean.FALSE;
        this.snippets = Boolean.FALSE;
        this.topics = Boolean.FALSE;
        this.words = Boolean.FALSE;
    }

    private boolean abuse;

    private boolean document_sentiment;

    private boolean words;

    private boolean fetch_definitions;

    private boolean parses;

    private boolean sentiment;

    private boolean entities;

    private boolean topics;

    private boolean deterministic;

    private boolean snippets;
}
