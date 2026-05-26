package com.example

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun syllabusInitializesSuccessfully() {
    val subjects = com.example.data.GateSyllabus.subjects
    assert(subjects.isNotEmpty())
    println("Successfully loaded ${subjects.size} subjects!")
    for (subject in subjects) {
      println("Subject: ${subject.name} has ${subject.topics.size} topics")
      for (topic in subject.topics) {
        println("  Topic: ${topic.name} has ${topic.subtopics.size} subtopics")
        for (subtopic in topic.subtopics) {
          assert(subtopic.id.isNotEmpty())
        }
      }
    }
  }
}
