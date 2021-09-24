package chapter6

// 코틀린에서는 nullable한 타입과 그렇지 않은 타입을 구분
// nullable하지 않은 타입에 null이 들어가면 런타임에 발생하는 NullPointerException이 아닌 컴파일 에러 발생

val x: String = "a"
val y: String? = null

// 안전한 호출 연산자(?)
fun toCaps(s: String?): String? {
    // null이 아닐 경우 함수 호출
    // null일 경우 null 반환
//    return if (s != null) s.uppercase() else null
    return s?.uppercase()
}

// 엘비스 연산자(?:) - null 대신 사용할 디폴트 값 지정
// 우항에 return 이나 throw 등의 연산도 사용 가능
fun foo(s: String?) {
    // null일 경우 "" 저장
    val t = s ?: ""
}

fun safeStrLen(s: String?): Int = s?.length ?: 0

// 안전한 캐스트(as?)
// 대상 타입으로 변환할 수 없으면 null 반환
class Person(val name: String, val age: Int) {
    // Any = Object
    override fun equals(other: Any?): Boolean {
        // Person 타입으로 변환할 수 없으면 false 리턴
        val otherPerson = other as? Person ?: return false
        // 코틀린에서 ==는 참조 타입을 비교할 때는 내부적으로 equals 호출
        // 참조 값 비교 -> ===
        return name == otherPerson.name &&
                age == otherPerson.age
    }
}

// null이 아님을 단언(!!)
// null이 될 수 없는 타입으로 변경(유지)

// let
// null이 될 수 있는 값을 null이 아닌 값을 받는 인자로 넘길 수 있음
// 수신 객체가 null이 아닌 경우 람다를 실행
fun sendEmail(email: String) {
    println("Send email to $email")
}

fun main() {
    var email: String? = "asdf@asdf.com"
//    sendEmail(email)
    email?.let { sendEmail(it) } // 실행

    email = null
    email?.let { sendEmail(it) } // 실행되지 않음

    nullableParam(null)
//    notNullableParam(null) // 컴파일 에러
}

// 나중에 초기화할 프로퍼티(lateinit)
class Test {
    // 코틀린에서 프로퍼티는 반드시 초기화해줘야 함(생성자, init, get..)
    // 초기화를 미룰 수 있음 -> var이어야 함
    private lateinit var name: String
}

// 타입 파라미터는 기본적으로 nullable
fun <T> nullableParam(t: T) {
    println(t?.toString())
}

// 타입 파라미터에 널이 될 수 없는 상한 적용
fun <T: Any> notNullableParam(t: T) {
    println(t.toString())
}

// 대부분 코틀린의 타입은 자바의 원시 타입으로 컴파일
// nullable 타입과 제네릭 클래스로 사용되는 타입은 참조 타입으로 컴파일
// 코틀린은 자동으로 업캐스팅 하지 않음
val a = 1
val b: Long = a.toLong() // 명시적으로 변환 함수 사용

// 코틀린에서는 읽기 전용 컬렉션과 변경 가능한 컬렉션을 분리 -> 프로그램에서 데이터에 어떤 일이 벌어지는지 더 쉽게 이해 가능
// 가능하면 읽기 전용 컬렉션을 사용하고, 필요한 경우만 변경 가능 컬렉션을 사용
// Collection - List, Set..
// MutableCollection - MutableList, MutableSet, ArrayList, HashSet..
val source: Collection<Int> = listOf(1, 2, 3)
val target: MutableCollection<Int> = arrayListOf()

// 배열 - 보통 가변 인자로 넘기기 위해 스프레드 연산자와 함께 사용
// 배열 생성 방법
val arr1 = arrayOf(1, 2, 3)
val arr2 = arrayOfNulls<Int>(3) // 크기가 3이고 모든 원소가 null
val arr3 = Array(3) { i -> i + 1 } // 람다는 배열 원소의 인덱스를 인자로 해당 위치에 들어갈 원소 반환
val arr4 = IntArray(3) // 원시 타입 배열 -> 크기만 인자로 받고 각 원소는 해당 원시 타입의 디폴트 값으로 초기화
