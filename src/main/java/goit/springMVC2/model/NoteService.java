package goit.springMVC2.model;

import goit.springMVC2.exception.NoteInformationException;
import goit.springMVC2.exception.NoteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    //private static final Logger logger = LoggerFactory.getLogger(NoteService.class);
    private final Map<Long, Note> notes = new HashMap<>();


    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }


    public Note add(Note note) throws NoteInformationException {
        if(note==null) {
            throw new NoteInformationException("note can't be null");
        }
        if ( (note.getTitle() == null || note.getTitle().equals(""))){
            throw new NoteInformationException("title of note is incorrect");
        }

        long currentNoteId= new Random().nextLong(1L, Long.MAX_VALUE);
        if (notes.size() == 0) {
            note.setId(currentNoteId);
        }
        else{
            while(notes.containsKey(currentNoteId)) {
                currentNoteId = new Random().nextLong(1L, Long.MAX_VALUE);
            }
            note.setId(currentNoteId);
        }

      notes.put(currentNoteId,note);
        return note;
    }


    public void deleteById(long id) throws NoteNotFoundException {
        if (!notes.containsKey(id)) {
            throw new NoteNotFoundException(id);
        }
        notes.remove(id);
    }


    public Note update(Note note) throws NoteNotFoundException, NoteInformationException {
        if(!notes.containsKey(note.getId())){
            throw new NoteNotFoundException(note.getId());
        }
        if(note.getTitle()==null || note.getTitle().equals("")){
            throw new NoteInformationException("title of note is incorrect");
        }
        Note noteExist = notes.get(note.getId());
        noteExist.setTitle(note.getTitle());
        noteExist.setContent(note.getContent());
        notes.put(note.getId(), noteExist);
        return noteExist;



    }

    public Note getById(long id) throws NoteNotFoundException {
        if (notes.containsKey(id)) {
            return notes.get(id);
        }
        throw new NoteNotFoundException(id);
    }
}
