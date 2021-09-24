package chapter4

// 객체 선언
// 생성자 사용 불가, 객체 선언 시 인스턴스 생성 -> 싱글턴
object Singleton {
    val name: String = "hi"
}

fun main() {
    println(Singleton.name)
}
