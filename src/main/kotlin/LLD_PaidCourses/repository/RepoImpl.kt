package LLD_PaidCourses.repository

import LLD_PaidCourses.model.Course
import LLD_PaidCourses.model.Student

class RepoImpl<T> : Repository<Student> {
    override fun get(): Iterable<Student> {
        return listOf(
            Student(
                id = 1,
                name = "MK",
                subscribedCourses = listOf(
                    Course(id = 1, name = "Maths", isPaid = true),
                    Course(id = 2, name = "Arts", isPaid = true)
                )
            ),
            Student(
                id = 1,
                name = "DK",
                subscribedCourses = listOf(
                    Course(id = 1, name = "Maths", isPaid = false),
                    Course(id = 2, name = "Arts", isPaid = true)
                )
            )
        )

    }

}
