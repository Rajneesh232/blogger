package com.blog.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private long id;
    @NotEmpty
    @Size(min=2,message="Title should be at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min=4,message="Description should be at least 4 characters")
    private String Description;

    @NotEmpty
    @Size(min=4,message="content should be at least 4 characters")
    private String content;
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
