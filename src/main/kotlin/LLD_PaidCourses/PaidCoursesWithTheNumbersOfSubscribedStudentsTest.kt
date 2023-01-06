package LLD_PaidCourses

import LLD_PaidCourses.model.Student
import LLD_PaidCourses.repository.RepoImpl
import LLD_PaidCourses.service.University

//typealias Id = Int

object Test {
    @JvmStatic
    fun main(array: Array<String>) {
        val university = University(RepoImpl<Student>())
        println(university.getPaidCoursesWithTheNumbersOfSubscribedStudents(1))
    }
}

