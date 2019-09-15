package edu.uta.movietalk.dto;

import lombok.Data;

/**
 * @author housirvip
 */
@Data
public class DingDto {

    private String msgtype;
    private Text text;
    private Link link;
    private Markdown markdown;
    private At at;

    @Data
    private class Text {
        private String content;
    }

    @Data
    private class Link {
        private String title;
        private String text;
        private String messageUrl;
        private String picUrl;
    }

    @Data
    private class Markdown {
        private String title;
        private String text;
    }

    @Data
    private class At {
        private String[] atMobiles;
        private Boolean isAtAll;
    }

    public DingDto putText(String content) {

        this.setMsgtype("text");
        this.text = new Text();
        this.text.setContent(content);

        return this;
    }

    public DingDto putMarkdown(String title, String content) {

        this.setMsgtype("markdown");
        this.markdown = new Markdown();
        this.markdown.setTitle(title);
        this.markdown.setText(content);

        return this;
    }

    public DingDto pubAt(Boolean atAll, String... phones) {

        this.at = new At();
        this.at.setIsAtAll(atAll);
        this.at.setAtMobiles(phones);

        return this;
    }
}
