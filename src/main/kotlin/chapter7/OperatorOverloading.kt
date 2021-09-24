package chapter7

import java.lang.IndexOutOfBoundsException

// 관례(Convention): 특정 기능과 미리 정해진 이름의 함수를 연결해주는 기법

data class Point(val x: Int, val y: Int) {
    // 비교 연산자 오버로딩
    // Any의 equals에 operator가 붙어있으므로 필요 x
    // 확장 함수는 구현되어 있는 함수보다 우선순위가 낮기 때문에 구현 불가
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Point) return false
        return x == other.x && y == other.y
    }
}

// 산술 연산자 오버로딩
// 확장 함수르 구현하는 것이 일반적
// operator - 관례를 따르는 함수임을 명시
// 미리 정해진 연산자의 이름으로만 오버로딩 가능
operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

// 단항 연산자 오버로딩
operator fun Point.unaryMinus(): Point {
    return Point(-x, -y)
}

class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    // 비교 연산자 오버로딩
    override fun compareTo(other: Person): Int {
        // 인자로 받은 프로퍼티 순서로 값을 비교
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }

    // 구조 분해 선언
    // data class에는 구현되어 있음
    operator fun component1() = firstName
    operator fun component2() = lastName
}

// 컬렉션 관례
// get, set -> []
operator fun Point.get(index: Int): Int {
    return when(index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException()
    }
}

// contains -> in
// rangeTo -> ..
// iterator -> for in


// 위임 프로퍼티


fun main() {
    var p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2)

    val p = Point(10, 20)
    println(-p)

    println(p == p1)
    println(p1 != p2)

    p1 += p2 // p1 = p1 + p2
    // 또는 plusAssign 함수 구현
    println(p1)

    val ps1 = Person("ji", "kim")
    val ps2 = Person("hy", "kim")
    println(ps1 < ps2)

    println(p1[1])

    val (n1, n2) = ps1
    println("$n2 $n1")

    val map = mapOf(1 to 1, 2 to 2)
}
