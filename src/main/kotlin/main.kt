fun main() {
    /*val service = NotesService()
    val note1 = Note(1, "Заметка 1", "Заметка про вашего мальчика")
    service.add(note1)
    val note2 = Note(1, "Заметка 1 редактированная", "Это про Простоквашино")
    service.edit(note2)
    val note3 = Note(2, "Заметка 2", "Вигвам - жилище индейцев")
    service.add(note3)
    //service.delete(1)
    val array = arrayOf(1, 2, 3, 4)
    val resultFound = service.get(array)
    val resultFound2 = service.getById(2)

    val commService = CommentsService()
    commService.add(Comment(1, 2, "Вигвамы - индейцам!", false))
    commService.add(Comment(2, 2, "Вигвамы - не только для индейцев!", false))
    val arrayComments = arrayOf(commService.getComments(2))
    commService.edit(Comment(2, 2, "Не только вигвамы - не только для индейцев!", false))
    commService.delete(2)
    val arrayComments2 = arrayOf(commService.getComments(2))*/
}

data class Note(
    val id: Int,
    val title: String,
    val text: String
)

data class FoundNotes(
    val count: Int,
    val arrayNotes: Array<Note>
)

data class Comment(
    val id: Int,
    val noteId: Int,
    val message: String,
    val deleted: Boolean
)

interface CrudService<E> {
    fun add(item: E): Int
    fun delete(id: Int): Boolean
    fun edit(item: E): Boolean
}

class NotesService: CrudService<Note> {
    private var notes = mutableListOf<Note>()

    override fun add(item: Note): Int {
        notes.add(item)
        return item.id
    }

    override fun delete(id: Int): Boolean {
        for ((index, noteInList) in notes.withIndex()) {
            if (id == noteInList.id) {
                notes.removeAt(index)
                return true
            }
        }
        return false
    }

    override fun edit(item: Note): Boolean {
        for ((index, noteInList) in notes.withIndex()) {
            if (item.id == noteInList.id) {
                notes[index] = item
                return true
            }
        }
        return false
    }

    fun get(arrayNoteIds: Array<Int>): FoundNotes {
        var array = emptyArray<Note>()
        var count = 0
        for ((index, noteInList) in notes.withIndex()) {
            for ((indexOfArray, idInArray) in arrayNoteIds.withIndex()) {
                if (noteInList.id == idInArray) {
                    array += noteInList
                    count ++
                }
            }
        }
        return FoundNotes(count, array)
    }

    fun getById(NoteId: Int): FoundNotes {
        var array = emptyArray<Note>()
        var count = 0
        for ((index, noteInList) in notes.withIndex()) {
                if (noteInList.id == NoteId) {
                    array += noteInList
                    count ++
                }
        }
        return FoundNotes(count, array)
    }
}

class CommentsService: CrudService<Comment> {
    private var comments = mutableListOf<Comment>()
    override fun add(item: Comment): Int {
        comments.add(item)
        return item.id
    }

    override fun delete(id: Int): Boolean {
        var deleted = false
        for ((index, commentInList) in comments.withIndex()) {
            if (commentInList.noteId == id && commentInList.deleted == false) {
                comments[index] = commentInList.copy(deleted = true)
                deleted = true
            }
        }
        return deleted
    }

    override fun edit(item: Comment): Boolean {
        for ((index, commentInList) in comments.withIndex()) {
            if ((item.id == commentInList.id) && (commentInList.deleted == false)) {
                comments[index] = item
                return true
            }
        }
        return false
    }

    fun restoreComment(id: Int): Boolean {
        for ((index, commentInList) in comments.withIndex()) {
            if ((id == commentInList.id) && (commentInList.deleted == true)) {
                comments[index] = commentInList.copy(deleted = false)
                return true
            }
        }
        return false
    }

    fun getComments(noteId: Int): Array<Comment> {
        var array = emptyArray<Comment>()
        for ((index, commentInList) in comments.withIndex()) {
            if ((commentInList.noteId == noteId) && (commentInList.deleted == false)) {
                    array += commentInList
            }
        }
        return array
    }

}