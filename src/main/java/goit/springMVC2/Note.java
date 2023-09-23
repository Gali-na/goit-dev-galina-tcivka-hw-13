package goit.springMVC2;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
public class Note {
    long id;
    String title;
    String content;
}
