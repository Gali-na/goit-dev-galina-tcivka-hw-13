package goit.springMVC2;

import goit.springMVC2.DAO.NoteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NoteServiceTest {
    private static NoteService noteService;

    @BeforeAll
    private static void getClient() throws ArgumentException {
        noteService = new NoteService();
        populatingList();
    }

    @Test
    void listAll_AddOneNote_GetRightResult() throws ArgumentException {
        int countNotBeforeAdding = noteService.listAll().size();
        Note note = new Note();
        note.setTitle("gym");
        note.setContent("I'm going to the gym");
        noteService.add(note);
        int countNoteAfterAdding = noteService.listAll().size();
        assertEquals(countNotBeforeAdding + 1, countNoteAfterAdding);
    }

    @Test
    void add_NoteNull_ThrowException() {
        assertThrows(ArgumentException.class, () -> noteService.add(null));
    }

    @Test
    void add_NoteTitleEmpty_ThrowException() {
        Note note = new Note();
        note.setTitle("");
        assertThrows(ArgumentException.class, () -> noteService.add(note));
    }

    @Test
    void add_NoteValid_PositiveResult() throws ArgumentException {
        int countNotBeforeAdding = noteService.listAll().size();
        Note note = new Note();
        note.setTitle("housework");
        note.setContent("I'm doing homework now");
        noteService.add(note);
        int countNotAfterAdding = noteService.listAll().size();
        assertEquals(countNotBeforeAdding + 1, countNotAfterAdding);
    }

    @Test
    void add_NoteValid_CheckProperties() throws ArgumentException {
        int countNotBeforeAdding = noteService.listAll().size();
        Note note = new Note();
        note.setTitle("car wash");
        note.setContent("taking cars to the car wash");
        Note newNote = noteService.add(note);
        assertEquals("car wash", newNote.getTitle());
        assertEquals("taking cars to the car wash", newNote.getContent());
    }

    @Test
    void deleteById_IdNotExist_ThrowException() {
        assertThrows(ArgumentException.class, () -> noteService.deleteById(0));
    }

    @Test
    void deleteById_IdExist_PositiveResult() throws ArgumentException {
        Note note = new Note();
        note.setTitle("English language");
        note.setContent("learn English");
        Note newNote = noteService.add(note);
        int countNoteAfterAdding = noteService.listAll().size();
        noteService.deleteById(newNote.getId());
        int countNoteAfterDelete = noteService.listAll().size();
        assertEquals(countNoteAfterAdding - 1, countNoteAfterDelete);

    }

    @Test
    void update_NoteExist_PositiveResult() throws ArgumentException {
         Note note = new Note();
        note.setTitle("rest");
        note.setContent("invite friends");
        Note newNote = noteService.add(note);
        newNote.setTitle("holiday");
        noteService.update(newNote);
        //String title =noteService.getById(newNote.getId()).getTitle();
        assertEquals(noteService.getById(newNote.getId()).getTitle(), "holiday");
    }

    @Test
    void update_NoteNotExist_ThrowException() {
        Note note = new Note();
        note.setId(0);
        note.setTitle("rest");
        note.setContent("invite friends");
        assertThrows(ArgumentException.class, () -> noteService.update(note));
    }

    @Test
    void getById_IdNotExist_ThrowException() {
        assertThrows(ArgumentException.class, () -> noteService.getById(0));
    }

    @Test
    void getById_IdExist_PositiveResult() throws ArgumentException {
        Note note = new Note();
        note.setTitle("dog");
        note.setContent("walk with the dog");
        Note newNote = noteService.add(note);
        assertEquals(noteService.getById(newNote.getId()).getTitle(), "dog");
        assertEquals(noteService.getById(newNote.getId()).getContent(), "walk with the dog");
    }
    private static void populatingList() throws ArgumentException {
        Note note = new Note();
        note.setTitle("gym");
        note.setContent("I'm going to the gym");
        noteService.add(note);
        note = new Note();
        note.setTitle("housework");
        note.setContent("I'm doing homework now");
        noteService.add(note);
    }
}