package goit.springMVC2.DAO;

import goit.springMVC2.ArgumentException;
import goit.springMVC2.Note;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public interface DAONoteService {

    List<Note> listAll();
    Note add(Note note) throws ArgumentException;


    void deleteById(long id) throws ArgumentException ;

    void update(Note note) throws ArgumentException;


    Note getById(long id) throws ArgumentException ;


}


