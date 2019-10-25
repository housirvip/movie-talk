package edu.uta.movietalk.nlp;

import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author hxy
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StanfordNlp {

    /**
     * segment
     */
    public List<String> segment(String paragraph) {

        List<String> segmentList = new ArrayList<>();

        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Annotation document = new Annotation(paragraph);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {

                String lemma = token.get(LemmaAnnotation.class);
                segmentList.add(lemma);
            }
        }
        return segmentList;
    }
}
