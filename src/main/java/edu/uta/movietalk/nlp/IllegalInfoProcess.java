package edu.uta.movietalk.nlp;

import edu.uta.movietalk.client.PDClient;
import edu.uta.movietalk.client.TSClient;
import edu.uta.movietalk.entity.*;
import edu.uta.movietalk.mapper.DirtyWordMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hxy
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class IllegalInfoProcess {

    private final DirtyWordMapper dirtyWordMapper;

    private final PDClient pdClient;

    private final TSClient tsClient;

    private final StanfordNlp stanfordNlp;

    @Value("${api.pd.key}")
    String apiKey;

    @Value("${api.ts.key}")
    String tsKey;

    public Boolean isDirty(String content) {
        DirtyWord dirtyWord = new DirtyWord();
        dirtyWord.setLanguage("en");
//        List<String> dirtyWordList = dirtyWordMapper.selectWordBySelective(dirtyWord);

//        List<String> contentWord = stanfordNlp.segment(content);
//        for (String word : contentWord) {
//            for(String dirty: dirtyWordList) {
//                if(word.equals(dirty)) {
//                    return Boolean.TRUE;
//                }
//            }
//        }
//        try {
//
//            Map<String, String> map = new HashMap<>();
//            map.put("api_key", apiKey);
//            map.put("text", content.toLowerCase());
//            ParallelDotAbuse parallelDotAbuse = pdClient.postEmotion(apiKey, content);
//            if(parallelDotAbuse.getAbusive() > 0.8) {
//                return Boolean.TRUE;
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }

        try {
            TisanceParse tisanceParse = new TisanceParse();
            tisanceParse.setContent(content);
            tisanceParse.setLanguage("en");
            TisanceParseSetting tisanceParseSetting = new TisanceParseSetting();
            tisanceParseSetting.setAbuse(Boolean.TRUE);
            tisanceParse.setSettings(tisanceParseSetting);
            TisanceResponse tisanceResponse = tsClient.parse(tsKey, tisanceParse);
            if(tisanceResponse.getAbuse() !=null && !tisanceResponse.getAbuse().isEmpty()) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Boolean.FALSE;
    }
}
