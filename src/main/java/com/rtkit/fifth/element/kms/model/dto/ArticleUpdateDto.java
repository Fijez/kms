package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {

    @NotBlank
    private Long id;
    private String title;
    private String topic;
    private String content;
    private String creator;
    private List<Long> tags;
    private String roleAccess;
    private List<Long> users;
    private Long namespaceId;

}
