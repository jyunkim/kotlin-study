package chapter5

// 컬렉션 함수형 API
fun main() {
    val people = listOf(Person("Kim", 25), Person("Lee", 22))

    println(people.filter { it.age > 23 }.map { it.name })

    val numbers = mapOf(0 to "zero", 1 to "one")
    // 결과로 map을 반환
    println(numbers.mapValues { it.value.uppercase() })

    println(people.find { it.name == "Kim" })

    val strings = listOf("abc", "def")
    // 람다를 적용한 결과 얻어지는 리스트를 한 리스트로 모음
    println(strings.flatMap { it.toList() })


    // 각 연산을 즉시 실행하여 임시 컬렉션을 만들어 중간 계산 결과를 담음
    people.filter { it.age > 23 }.map { it.name }

    // 시퀀스
    // 중간 연산을 최종 연산이 수행될 때까지 지연 처리 -> 임시 컬렉션을 생성하지 않음
    // 모든 중간 연산을 저장한 뒤 각 원소에 순차적으로 적용
    people.asSequence()
        .filter { print("filter($it) "); it.age > 23 }
        .map { print("map($it) "); it.name }
        .toList()
}