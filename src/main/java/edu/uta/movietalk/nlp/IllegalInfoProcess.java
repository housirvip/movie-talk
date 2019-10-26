package edu.uta.movietalk.nlp;

import edu.uta.movietalk.client.PDClient;
import edu.uta.movietalk.entity.Abuse;
import edu.uta.movietalk.entity.DirtyWord;
import edu.uta.movietalk.mapper.DirtyWordMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hxy
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class IllegalInfoProcess {

    private final DirtyWordMapper dirtyWordMapper;

    private final PDClient pdClient;

    @Value("${api.pd.key}")
    String apiKey;

    public Boolean isDirty(String content) {

        try {

            Map<String, String> map = new HashMap<>();
            map.put("api_key", apiKey);
            map.put("text", content);
            Abuse abuse= pdClient.postEmotion(apiKey, content);
            if(abuse.getAbusive() > 0.7) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }


        DirtyWord dirtyWord = new DirtyWord();
        dirtyWord.setLanguage("en");
        List<String> dirtyWordList = dirtyWordMapper.selectWordBySelective(dirtyWord);

        for (String word : dirtyWordList) {
            if(content.contains(word)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
