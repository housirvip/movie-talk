package edu.uta.movietalk.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import edu.uta.movietalk.constant.Constant;
import edu.uta.movietalk.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * @author housirvip
 */
@Data
public class PageDto implements Serializable {

    private Integer pageNum;

    private Integer pageSize;

    private String param;

    @JsonIgnore
    private Map<String, Object> paramAsMap = Maps.newHashMap();

    public PageDto putUid(Integer uid) {

        paramAsMap.put(Constant.UID, uid);

        return this;
    }

    public PageDto putDisable() {

        paramAsMap.put(Constant.ENABLE, false);

        return this;
    }

    public PageDto putParam() {

        Optional.ofNullable(JsonUtils.convertToMap(param, String.class, Object.class))
                .ifPresent(map -> paramAsMap.putAll(map));

        return this;
    }
}
