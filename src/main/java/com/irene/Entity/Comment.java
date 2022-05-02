package com.irene.Entity;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Long user_id;
    private Integer sid;
    private Integer likes;
    private String content;

}
