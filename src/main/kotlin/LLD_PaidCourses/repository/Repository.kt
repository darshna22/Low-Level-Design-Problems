package LLD_PaidCourses.repository

interface Repository<T> {
    fun get(): Iterable<T>
}