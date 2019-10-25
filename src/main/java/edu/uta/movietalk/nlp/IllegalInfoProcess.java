package edu.uta.movietalk.nlp;

import edu.uta.movietalk.entity.DirtyWord;
import edu.uta.movietalk.mapper.DirtyWordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hxy
 */
@Service
@RequiredArgsConstructor
public class IllegalInfoProcess {

    private final DirtyWordMapper dirtyWordMapper;
    private final StanfordNlp stanfordNlp;

    public Boolean isDirty(String content) {

        List<String> wordList = stanfordNlp.segment(content);

        DirtyWord dirtyWord = new DirtyWord();
        dirtyWord.setLanguage("en");
        List<String> dirtyWordList = dirtyWordMapper.selectWordBySelective(dirtyWord);

        for (String word : wordList) {
            if(dirtyWordList.contains(word)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
