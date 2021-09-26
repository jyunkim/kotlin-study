package chapter8

// 고차 함수: 다른 함수를 인자로 받거나 함수를 반환하는 함수
// 코틀린에서는 람다나 함수 참조를 이용하여 함수를 값으로 표현

// 함수 타입 - (T1) -> T2
// val sum = { x, y -> x + y }
val sum: (Int, Int) -> Int = { x, y -> x + y }

// 함수를 인자로 받는 고차 함수
fun oneAndTwo(operation: (Int, Int) -> Int): Int {
    return operation(1, 2)
}

// 디폴트 값을 지정한 함수 타입 파라미터 -> 디폴트 값으로 람다 식을 사용
fun twoAndThree(operation: (Int, Int) -> Int = { _, _ -> 0 }): Int {
    return operation(2, 3)
}

enum class Delivery {
    STANDARD, EXPEDITED
}

class Order(val itemCount: Int)

// 함수를 반환하는 고차 함수
fun getDeliveryCost(delivery: Delivery): (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 2.1 * order.itemCount }
    }
    return { order -> 1.2 * order.itemCount }
}

// 람다를 활용한 중복 제거
enum class OS {
    WINDOWS, LINUX, MAC, IOS, ANDROID
}

data class SiteVisit(
    val path: String,
    val duration: Int,
    val os: OS
)

val log = listOf(
    SiteVisit("/", 34, OS.WINDOWS),
    SiteVisit("/", 22, OS.MAC),
    SiteVisit("/login", 12, OS.WINDOWS),
    SiteVisit("/signup", 8, OS.IOS),
    SiteVisit("/", 16, OS.ANDROID)
)

fun List<SiteVisit>.averageDuration(os: OS) =
    this.filter { it.os == os }.map(SiteVisit::duration).average()

// 만약 모바일 사용자의 평균 방문 시간을 구하려면 어떻게 해야 할까?
fun List<SiteVisit>.averageMobileDuration(os: OS) =
    this.filter { it.os in setOf(OS.IOS, OS.ANDROID) }.map(SiteVisit::duration).average()

// 여기서 조건이 추가된다면 처리하기 어려워짐

// 고차 함수를 사용해 중복 제거
fun List<SiteVisit>.averageMobileDurationV2(predicate: (SiteVisit) -> Boolean) =
    this.filter(predicate).map(SiteVisit::duration).average()

fun main() {
    println(log.averageMobileDurationV2 { it.os in setOf(OS.IOS, OS.ANDROID) })
    println(log.averageMobileDurationV2 { it.os == OS.IOS && it.path == "/signup" })
}
