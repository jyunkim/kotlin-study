package chapter3

fun main() {
    val set = hashSetOf(1, 2, 3)
    // to: 중위 호출이라는 방식으로 호출되는 함수. 1 to 2 = 1.to(2)
    val map = hashMapOf(1 to "one", 2 to "two")

    val list = ArrayList<Pair<Int, Int>>()
    list.add(Pair(1, 2))
    list.add(2 to 3)
    println("list = ${list}")

    println(set.javaClass) // getClass()
    println(set.maxOrNull())
}
