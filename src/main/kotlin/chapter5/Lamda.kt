package chapter5

// 함수형 프로그래밍: 함수를 값처럼 다루는 방식 -> 인스턴스 대신 함수를 인자로 전달 가능
// 람다: 함수를 선언할 필요 없이 코드 블록을 함수의 인자로 전달 가능

data class Person(val name: String, val age: Int)

fun main() {
    // 기본 람다식
    val sum = {x: Int, y: Int -> x + y}

    val people = listOf(Person("Kim", 25), Person("Lee", 22))
    // maxBy는 deprecated됨
    // 마지막 인자가 람다 식이면 괄호 밖으로 뺄 수 있음
    // 람다가 함수의 유일한 인자이면 괄호 밖으로 빼고 괄호 생략 가능
    println(people.maxByOrNull { p: Person -> p.age })
    // maxByOrNull의 파라미터 타입은 항상 컬렉션 원소 타입이므로 컴파일러가 타입을 추론하여 타입 생략 가능
    println(people.maxByOrNull { p -> p.age })
    // it: 디폴트 파라미터 이름 -> 람다의 파라미터가 하나이고 타입을 추론할 수 있는 경우 사용 가능
    println(people.maxByOrNull { it.age })
    // 멤버 참조
    println(people.maxByOrNull(Person::age))

}
