package com.sample.interfaces;

import com.sample.objects.Note;

public interface NotesTable {

    void add(Note note);

    void edit(Note note, String oldValue);

    void delete (Note note);

}
