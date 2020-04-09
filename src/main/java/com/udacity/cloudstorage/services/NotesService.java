package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.NoteMapper;
import com.udacity.cloudstorage.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Shubham Sharma
 * @date 8/4/20
 */
@Service
public class NotesService {
    @Autowired
    private NoteMapper notesMapper;

    public List<Notes> getAllNotes(int userid) throws Exception {
        List<Notes> notes = notesMapper.findByUserId(userid);
        return java.util.Optional.ofNullable(notes).orElse(Collections.emptyList());
    }

    public Notes getNoteById(int id) {
        return notesMapper.findById(id);
    }

    public void addNote(Notes note, int userid) {
        notesMapper.insertNote(note, userid);
    }

    public void updateNote(Notes note) {
        notesMapper.updateNote(note);
    }

    public void deleteNote(int id) {
        notesMapper.deleteNote(id);
    }
}
