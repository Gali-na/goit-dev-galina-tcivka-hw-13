package goit.springMVC2.DAO;

import goit.springMVC2.ArgumentException;
import goit.springMVC2.Note;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoteService implements DAONoteService {
    private  Map<Long, Note> storedge;
    public NoteService() {

        storedge = new HashMap<>();
    }

    public List<Note> listAll() {
        return storedge.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
    }
   public Note add(Note note) throws ArgumentException {
        if (note == null) {
            throw new ArgumentException("Note is incorrect");
        }
        if (note.getTitle() == null || note.getTitle().equals("")) {
            throw new ArgumentException("title of note is incorrect");
        }
        Random rnd = new Random(1L);
        long currentNoteId= rnd.nextLong();
        if (storedge.size() == 0) {
            note.setId(currentNoteId);
        }
        else{
            while(storedge.containsKey(currentNoteId)){
                currentNoteId = rnd.nextLong();
            }
            note.setId(currentNoteId);
        }

        storedge.put(currentNoteId,note);
        return note;
    }

  public   void deleteById(long id) throws ArgumentException {
         if(storedge.remove(id)==null) {
             throw new ArgumentException("Id is not exist");
         }
    }

   public void update(Note note) throws ArgumentException {
        checkNote(note);
        if(!storedge.containsKey(note.getId())){
            throw new ArgumentException("Note is not exist");
        }
        storedge.put(note.getId(),note);
    }


  public   Note getById(long id) throws ArgumentException {
        if (storedge.containsKey(id)) {
            return  storedge.get(id);
        }
        throw new ArgumentException("id is not exist");
    }

    private void checkNote(Note note) throws ArgumentException {
        if (note == null) {
            throw new ArgumentException("Note is incorrect");
        }
        if (note.getId() == 0) {
            throw new ArgumentException("Note is incorrect");
        }
    }
}
