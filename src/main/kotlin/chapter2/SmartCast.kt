package chapter2

interface Expr

class Num(val value: Int): Expr

class Sum(val left: Num, val right: Num): Expr

// return 필요
fun evalV1(e: Expr): Int {
//    // is: 타입 검사
//    if (e is Num) {
////        val n = e as Num -> 타입 검사 후 스마트 캐스트에 의해 자동으로 캐스팅
//        return e.value
//    } else if (e is Sum) {
//        return eval(e.left) + eval(e.right)
//    } else {
//        throw IllegalArgumentException()
//    }

    return when (e) {
        is Num -> e.value
        is Sum -> evalV1(e.left) + evalV1(e.right)
        else -> throw IllegalArgumentException()
    }
}

fun evalV2(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> evalV2(e.left) + evalV2(e.right)
        else -> throw IllegalArgumentException()
    }

fun main() {
    val num1 = Num(1)
    val num2 = Num(2)

    val sum = Sum(num1, num2)
    println(evalV1(sum))
    println(evalV2(sum))
}