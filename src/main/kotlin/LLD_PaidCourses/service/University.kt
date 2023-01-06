package LLD_PaidCourses.service

import LLD_PaidCourses.model.Course
import LLD_PaidCourses.model.Student
import LLD_PaidCourses.repository.Repository

class University(private val repository: Repository<Student>) {

    fun getPaidCoursesWithTheNumbersOfSubscribedStudents(coursesCount: Int): Map<Course, Int> {
        val students = repository.get();

        //create studentCourseMap of paid courses.
        val studentCourseMap = students.map { student ->
            val paidCourses = student.subscribedCourses.filter {
                it.isPaid
            }
            Pair(student, paidCourses)
        }.toMap()

        //create coursestudentMap for paid courses.
        val courseMap = mutableMapOf<Course, Int>()
        studentCourseMap.map { (student, courses) ->
            courses.map { course ->
                if (!courseMap.containsKey(course)) {
                    courseMap[course] = 1
                } else {
                    courseMap[course] = courseMap[course]!! + 1
                }
            }
        }

        return courseMap
            .toList()
            .sortedByDescending { it.second }
            .take(coursesCount)
            .toMap()

    }


}
