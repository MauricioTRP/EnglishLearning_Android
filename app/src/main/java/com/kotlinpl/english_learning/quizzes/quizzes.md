# Quizzes

A core component of the application.

Each user will have a unique ID, name, and email.

Each quiz will have a unique ID, title, and description.

Each question will have a unique ID, text, and a list of answer options.

Each answer option will have a unique ID, text, and a flag indicating whether it is the correct answer.


```mermaid
---
title: Quizz App
---
erDiagram
    USER
    QUESTION ||--o{ QUESTION_OPTION : "haves many"
    QUIZ ||--o{ QUIZ_QUESTION : "haves many"
    QUESTION ||--o{ QUIZ_QUESTION : "haves many"
    QUESTION ||--o{ STUDENT_ANSWER : "have many"
    USER ||--o{ STUDENT_ANSWER : "answers many"
    QUESTION ||--o{ QUESTION_TAG : "has many tags through"
    TAG ||--o{ QUESTION_TAG : "has many questions through"

    TAG {
        string tagId
        string tagName
    }
    QUESTION_TAG {
        string tagId
        string questionId
    }
    USER {
        string userId
        string name
        string email
        string password
    }
    QUIZ {
        string quizId
        string title
        string desctiption
    }
    QUESTION {
        string questionId
        string text
    }
    QUIZ_QUESTION {
        string quizId
        string questionId
    }
    QUESTION_OPTION {
        string optionId
        string text
        boolean isCorrect
        string questionId
        string feedback
    }
    STUDENT_ANSWER {
        string studentId
        string questionId
        string optionId
    }
```