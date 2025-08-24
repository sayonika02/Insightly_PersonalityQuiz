package com.sd.personalityquiz.data

import com.sd.personalityquiz.model.Question

object QuizRepository {
    fun getQuestions(): List<Question> {
        return listOf(
            Question(
                text = "Someone asks you to try an adventurous activity (skydiving, rafting, etc). You:",
                options = listOf("Get excited and convince others to join too", "Hesitate, but might say yes if encouraged", "Politely decline, that stuff is not for me!"),
                scores = listOf(3, 2, 1),
                category = "Fun"
            ),
            Question(
                text = "During a fight with a close friend, your first instinct is to:",
                options = listOf("Hold your ground silently until they finish", "Stay calm and try to understand their perspective", "Argue back with equal intensity"),
                scores = listOf(2, 1, 3),
                category = "Personality"
            ),
            Question(
                text = "When working on a group project, you usually:",
                options = listOf("Share ideas and actively contribute to discussions", "Do your part quietly and on time", "Take the lead and organize everyone"),
                scores = listOf(2, 1, 3),
                category = "Logic"
            ),
            Question(
                text = "When meeting someone new, you usually:",
                options = listOf("Wait for them to approach you first", "Greet politely but keep it short", "Start with humor or enthusiasm to break the ice"),
                scores = listOf(1, 2, 3),
                category = "Personality"
            ),
            Question(
                text = "You're stranded on a desert island. What's going through your head?",
                options = listOf("I should make a plan! shelter, water, food, survival first", "Finally some peace and quiet! I’ll enjoy this", "I’m lonely… I’ll explore the island to pass time"),
                scores = listOf(1, 1, 3),
                category = "Logic"
            )
        ).shuffled()
    }
}
