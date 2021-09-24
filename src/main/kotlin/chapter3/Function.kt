package chapter3

import kotlin.text.StringBuilder

// 디폴트 파라미터
fun default(prefix: String, postfix: String = ".") {
    println(prefix + postfix)
}

// 확장 함수 -> 수신 객체 멤버에 this 없이 접근 가능, 수신 객체의 메서드나 프로퍼티에 바로 접근 가능
fun String.lastChar(): Char = get(length - 1) // this.get(this.length - 1)

// 확장 프로퍼티
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) = setCharAt(length - 1, value)

// 가변 인자 함수 - iterable 형태로 넘어옴
fun sum(vararg nums: Int): Int {
    return nums.sum()
}

fun spread(args: Array<Int>) {
    // 스프레드 연산자 - 가변 인자로 변환
    val list = listOf(*args, 4)
    println(list)
}

class User(
    val id: Int,
    val name: String,
    val address: String
)

// 로컬 함수 -> 외부 함수의 모든 파라미터와 변수 접근 가능
fun saveUser(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: no $fieldName")
        }
    }
    validate(user.name, "name")
    validate(user.address, "address")
}

// 로컬 함수 + 확장 함수
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user $id: no $fieldName")
        }
    }
    validate(name, "name")
    validate(address, "address")
}

fun saveUserV2(user: User) {
    user.validateBeforeSave()
}

fun main() {
    default("asdf")

    val str = "asdf"
    println(str.lastChar())

    val sb = StringBuilder("asdf")
    sb.lastChar = 'g'
    println(sb)

    println(sum(1, 2, 3))

    // intArray: int[]
    // Array<Int>: Integer[]
    val arr = intArrayOf(1, 2, 3)
    println(sum(*arr))
    spread(arrayOf(1, 2, 3))
}
