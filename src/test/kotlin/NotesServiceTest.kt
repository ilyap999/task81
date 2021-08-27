import org.junit.Test

import org.junit.Assert.*

class NotesServiceTest {

    @Test
    fun add() {
        val service = NotesService()
        val note1 = Note(1, "Заметка 1", "Заметка про вашего мальчика")
        val result = service.add(note1)
        assertEquals(1, result)
    }

    @Test
    fun delete() {
        val service = NotesService()
        val note1 = Note(1, "Заметка 1", "Заметка про вашего мальчика")
        service.add(note1)
        val result = service.delete(1)
        assertTrue(result)
    }

    @Test
    fun edit() {
        val service = NotesService()
        val note1 = Note(1, "Заметка 1", "Заметка про вашего мальчика")
        service.add(note1)
        val note2 = Note(1, "Заметка 1 редактированная", "Это про Простоквашино")
        val result = service.edit(note2)
        assertTrue(result)
    }

    @Test
    fun get() {
        val service = NotesService()
        val note1 = Note(1, "Заметка 1", "Заметка про вашего мальчика")
        service.add(note1)
        val note3 = Note(2, "Заметка 2", "Вигвам - жилище индейцев")
        service.add(note3)
        val array = arrayOf(1, 2)
        val resultFound = service.get(array)
        val result = resultFound.count
        assertEquals(2, result)
    }

    @Test
    fun getById() {
        val service = NotesService()
        val note1 = Note(1, "Заметка 1", "Заметка про вашего мальчика")
        service.add(note1)
        val note3 = Note(2, "Заметка 2", "Вигвам - жилище индейцев")
        service.add(note3)
        val resultFound = service.getById(2)
        val result = resultFound.count
        assertEquals(1, result)
    }
}