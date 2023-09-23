package goit.springMVC2.controllers;


import goit.springMVC2.ArgumentException;
import goit.springMVC2.DAO.DAONoteService;
import goit.springMVC2.DAO.NoteService;
import goit.springMVC2.Note;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {

   final DAONoteService daoNoteService;
   @GetMapping()
    public ModelAndView getGreeting() {
        ModelAndView result = new ModelAndView("note");
        return result;
    }
    @GetMapping("/add")
    public  ModelAndView  getForm() {
        ModelAndView result = new ModelAndView("add");
        return result;
    }
    @PostMapping("/add")
    public  ModelAndView addNote(@RequestBody Note note){
        ModelAndView result = new ModelAndView("add");
        try { daoNoteService.add(note);
       } catch (ArgumentException e) {
          result.addObject("warning",e.getMessage());
           return result;
       }
        result.addObject("warning","new note created");
        return result;
    }
    @PostMapping("/delete")
    public  void deleteNote(@RequestBody Note note,
                            HttpServletResponse resp) throws ArgumentException, IOException {
        daoNoteService.deleteById(note.getId());
        resp.sendRedirect("/note/list");
    }
    @GetMapping("/list")
    public  ModelAndView getAllNote() throws ArgumentException {
        ModelAndView result = new ModelAndView("list");
        result.addObject("list",daoNoteService.listAll());
        return result;
    }

    @GetMapping("/edit")
    public  ModelAndView  getFormEdit(@RequestParam(name = "id") long id) throws ArgumentException {
        ModelAndView result = new ModelAndView("edit");
        result.addObject("note",daoNoteService.getById(id) );
        return result;
    }
    @PostMapping("/edit")
    public  void seveEditedNote(@RequestBody Note note,
                            HttpServletResponse resp) throws ArgumentException, IOException {
        daoNoteService.update(note);
        resp.sendRedirect("/note/list");
    }
}
