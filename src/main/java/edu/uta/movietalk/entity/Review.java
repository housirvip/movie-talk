package edu.uta.movietalk.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hxy
 */
@Getter
@Setter
@ToString
public class Review implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer mid;

    private String title;

    private Integer vote;

    private Date createTime;

    private Date updateTime;

    private String content;

    private static final long serialVersionUID = 1L;

    public void setCreateTime(Date createTime) {
        this.createTime = (Date)createTime.clone();
    }

    public Date getCreateTime() {
        return (Date)createTime.clone();
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = (Date)updateTime.clone();
    }

    public Date getUpdateTime() {
        return (Date)updateTime.clone();
    }
}