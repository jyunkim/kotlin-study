package chapter2

import java.util.*

// 코틀린에서는 함수나 변수가 클래스 안에 있을 필요가 없음능
// 기본이 public final

// 웬만하면 val를 사용하고 필요한 경우에만 var로 변경
// 초기화 할 경우 타입을 지정하지 않아도 됨
const val x = 1 // public static final

// 반환 타입을 지정하지 않으면 Unit(void)
// 불록이 본문인 함수
fun a() {
    // 지역 변수는 초기화하지 않아도 되지만 그러기 위해선 타입을 명시
    val x: Int
    val y = 1
}

// 식이 본문인 함수 -> return을 써주지 않아도 됨
fun b(): Int = x

// 코틀린에서 디폴트 접근 제한자는 public
// 자바의 디폴트 접근 제한자(같은 패키지) = 코틀린의 internal(같은 모듈)
class C {
    // 프로퍼티는 반드시 초기화 해주어야 함
    private val x = 1
    private val y: Int = 2
    private val name = "jihyun"
    private val list = listOf<Int>(1, 2, 3)

    // 문자열 템플릿
    override fun toString(): String {
        return "hello, ${if (name.length > 0) name else "noName"} ${list[0]}"
    }
}

// data class(data 생략 가능)
// getter, setter, toString, equals, hashCode 생성
data class Person(
    val name: String, // read only - getter만 생성
    var isMarried: Boolean
)

class Rectangle(val height: Int, val width: Int) {
    // 프로퍼티 값을 저장할 필요가 없을 때
    val isSquare: Boolean
        get() = height == width // getter
}

fun checkColor(color: Color): String =
    when (color) {
        Color.RED -> "Richard"
        Color.YELLOW -> "of"
        Color.BLUE -> "york"
    }


fun main() {
    val ins = C()
    println(ins.toString())

    val person = Person("jihyun", false)
    person.isMarried = true // 프로퍼티 이름을 직접 사용하면 내부적으로 getter, setter 호출

    val rectangle = Rectangle(1, 2)
    println(rectangle.isSquare)

    checkColor(Color.BLUE)

    val listA: List<Int> = listOf(1, 2, 3)

    when {
        listA.isEmpty() -> println("Empty")
        listA.contains(1) -> println("yes")
        else -> println("no")
    }

    for (e in listA) {
        print("$e ")
//        print(e.toString() + " ")
    }
    println()

    // 0~2
    for (i in 0..2) {
        print(listA[i].toString() + " ")
    }
    println()

    for (i in 0..2 step 2) {
        print(listA[i].toString() + " ")
    }
    println()

    for (i in 2 downTo 0) {
        print(listA[i].toString() + " ")
    }
    println()

    val map = TreeMap<String, Int>()
    map["b"] = 2
    map["a"] = 1
    for ((key, value) in map) {
        println("key = ${key} value = ${value}")
    }

    val list = listOf(1, 2, 3)
    for ((index, element) in list.withIndex()) {
        println("index = ${index} element = ${element}")
    }

    val range = 1..10
    println(1 in range)
}
