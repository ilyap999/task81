import org.junit.Test

import org.junit.Assert.*

class CommentsServiceTest {

    @Test
    fun add() {
        val commService = CommentsService()
        val result = commService.add(Comment(1, 2, "Вигвамы - индейцам!", false))
        assertEquals(1, result)
    }

    @Test
    fun delete() {
        val commService = CommentsService()
        commService.add(Comment(2, 2, "Не только вигвамы - не только для индейцев!", false))
        val result = commService.delete(2)
        assertTrue(result)
    }

    @Test
    fun edit() {
        val commService = CommentsService()
        commService.add(Comment(1, 2, "Вигвамы - индейцам!", false))
        commService.add(Comment(2, 2, "Вигвамы - не только для индейцев!", false))
        val result = commService.edit(Comment(2, 2, "Не только вигвамы - не только для индейцев!", false))
        assertTrue(result)
    }

    @Test
    fun restoreComment() {
        val commService = CommentsService()
        commService.add(Comment(1, 2, "Вигвамы - индейцам!", false))
        commService.add(Comment(2, 2, "Вигвамы - не только для индейцев!", true))
        val result = commService.restoreComment(2)
        assertTrue(result)
    }

    @Test
    fun getComments() {
        val commService = CommentsService()
        commService.add(Comment(1, 2, "Вигвамы - индейцам!", false))
        commService.add(Comment(2, 2, "Вигвамы - не только для индейцев!", true))
        val result = commService.getComments(2).size
        assertEquals(1, result)
    }
}