package com.example.test553.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test553.database.AppDatabase
import com.example.test553.database.DbMapper
import com.example.test553.database.Repository
import com.example.test553.domain.model.ColorModel
import com.example.test553.domain.model.ContactModel
import com.example.test553.domain.model.NoteModel
import com.example.test553.routing.MyNotesRouter
import com.example.test553.routing.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : ViewModel() {
    val notesNotInTrash: LiveData<List<NoteModel>> by lazy {
        repository.getAllNotesNotInTrash()
    }
    val contactsNotInTrash: LiveData<List<ContactModel>> by lazy {
        repository.getAllContactsNotInTrash()
    }

    private var _noteEntry = MutableLiveData(NoteModel())
    private var _contactEntry = MutableLiveData(ContactModel())
    val noteEntry: LiveData<NoteModel> = _noteEntry
    val contactEntry: LiveData<ContactModel> = _contactEntry
    val colors: LiveData<List<ColorModel>> by lazy {
        repository.getAllColors()
    }


    val notesInTrash by lazy { repository.getAllNotesInTrash() }
    val contactsInTrash by lazy { repository.getAllContactsInTrash() }
    private var _selectedNotes = MutableLiveData<List<NoteModel>>(listOf())
    private var _selectedContacts = MutableLiveData<List<ContactModel>>(listOf())

    val selectedNotes: LiveData<List<NoteModel>> = _selectedNotes
    val selectedContacts: LiveData<List<ContactModel>> = _selectedContacts

    private val repository: Repository

    init {
        val db = AppDatabase.getInstance(application)
        repository = Repository(db.noteDao(), db.colorDao(), DbMapper(),db.contactDao(),)
    }

    fun onCreateNewNoteClick() {
        _noteEntry.value = NoteModel()
        MyNotesRouter.navigateTo(Screen.SaveNote)
    }

    fun onNoteClick(note: NoteModel) {
        _noteEntry.value = note
        MyNotesRouter.navigateTo(Screen.SaveNote)
    }

    fun onNoteCheckedChange(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertNote(note)
        }
    }

    fun onNoteSelected(note: NoteModel) {
        _selectedNotes.value = _selectedNotes.value!!.toMutableList().apply {
            if (contains(note)) {
                remove(note)
            } else {
                add(note)
            }
        }
    }

    fun restoreNotes(notes: List<NoteModel>) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.restoreNotesFromTrash(notes.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedNotes.value = listOf()
            }
        }
    }

    fun permanentlyDeleteNotes(notes: List<NoteModel>) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.deleteNotes(notes.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedNotes.value = listOf()
            }
        }
    }

    fun onNoteEntryChange(note: NoteModel) {
        _noteEntry.value = note
    }

    fun saveNote(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertNote(note)

            withContext(Dispatchers.Main) {
                MyNotesRouter.navigateTo(Screen.Notes)

                _noteEntry.value = NoteModel()
            }
        }
    }
    fun moveNoteToTrash(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.moveNoteToTrash(note.id)

            withContext(Dispatchers.Main) {
                MyNotesRouter.navigateTo(Screen.Notes)
            }
        }
    }



    fun onCreateNewContactClick() {
        _contactEntry.value = ContactModel()
        MyNotesRouter.navigateTo(Screen.SaveContact)
    }

    fun onContactClick(contact: ContactModel) {
        _contactEntry.value = contact
        MyNotesRouter.navigateTo(Screen.SaveContact)
    }

    fun onContactCheckedChange(contact: ContactModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertContact(contact)
        }
    }

    fun onContactSelected(contact: ContactModel) {
        _selectedContacts.value = _selectedContacts.value!!.toMutableList().apply {
            if (contains(contact)) {
                remove(contact)
            } else {
                add(contact)
            }
        }
    }

    fun restoreContact(contacts: List<ContactModel>) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.restoreContactsFromTrash(contacts.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedContacts.value = listOf()
            }
        }
    }

    fun permanentlyDeleteContact(contacts: List<ContactModel>) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.deleteContacts(contacts.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedContacts.value = listOf()
            }
        }
    }

    fun onContactEntryChange(contact: ContactModel) {
        _contactEntry.value = contact
    }

    fun saveContact(contact: ContactModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertContact(contact)

            withContext(Dispatchers.Main) {
                MyNotesRouter.navigateTo(Screen.Contacts)

                _contactEntry.value = ContactModel()
            }
        }
    }

    fun moveContactToTrash(contact: ContactModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.moveContactToTrash(contact.id)

            withContext(Dispatchers.Main) {
                MyNotesRouter.navigateTo(Screen.Contacts)
            }
        }
    }
}