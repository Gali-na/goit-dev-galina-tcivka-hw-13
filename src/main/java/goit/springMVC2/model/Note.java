package goit.springMVC2.model;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
public class Note {
    private long id;
    private String title;
    private String content;
}
