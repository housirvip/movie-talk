package edu.uta.movietalk.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author hxy
 */
@Getter
@Setter
@ToString
public class AdminRecord {

    private Integer userTotal;

    private Integer reviewTotal;

    private Integer replyTotal;

    private Integer reportTotal;

    private Integer reportUnsolveTotal;

    private List<WebDataRecord> webDataRecordList;
}
