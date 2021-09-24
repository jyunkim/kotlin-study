package chapter2

// enum에 프로퍼티나 메서드를 정의할 수 있음
enum class Color(
    val r: Int, val g: Int, val b:Int
) {
    RED(255, 0, 0), YELLOW(0, 255, 0), BLUE(0, 0, 255); // 세미콜론 필요

    fun rgb() = (r * 256 + g) * 256 + b
}