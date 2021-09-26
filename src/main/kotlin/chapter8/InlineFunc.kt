package chapter8

// 람다를 함수에 넘겨주면 컴파일러가 익명 클래스를 생성해서 람다를 인스턴스로 변환함 -> 부가 비용 발생
// 어떤 함수를 inline으로 선언하면 그 함수의 본문이 인라인됨
// -> 함수를 호출하는 코드가 컴파일 시점에 함수를 호출하는 바이트 코드 대신 호출한 함수 본문을 번역한 바이트 코드로 컴파일 됨
// => 익명 클래스를 생성하지 않음

// 함수를 인라인으로 사용해야 하는 경우
// 1. 람다를 인자로 받는 함수
// 2. 본문의 코드 크기가 작은 함수
// 3. 인자로 받아온 람다를 본문에서 바로 사용하는 함수

// 파라미터로 받은 람다를 다른 변수에 저장하고 나중에 그 변수를 사용한다면 람다를 표현하는 객체가 어딘가는 존재해야하므로 인라이닝 불가
// 또는 파라미터로 받은 람다를 다른 생성자나 함수의 인자로 넘기는 경우 인라이닝 불가
class TransformingSequence<T, R>(val sequence: Sequence<T>, val transform: (T) -> R)

fun <T, R> Sequence<T>.map(transform: (T) -> R) =
    // 파라미터로 받은 람다를 객체의 프로퍼티에 저장해야 하기 때문에
    // transform 인자를 인라인하지 않는 일반적인 함수 표현으로 만들 수 밖에 없음
    TransformingSequence(this, transform)

// filter와 map 모두 인라인 함수이지만, 시퀀스를 사용하면 인라인되지 않음
// 위의 예제처럼 시퀀스는 람다를 객체 형태로 중간 시퀀스의 필드로 저장해야 하기 때문에 인라인 할 수 없음
// => 크기가 작은 컬렉션에 대해서는 오히려 성능이 안 좋을 수 있음

// non-local return: 자신을 둘러싸고 있는 블록보다 더 바깥에 있는 다른 블록을 return
// return 식은 가장 안쪽에 정의된 fun 키워드로 정의된 함수를 반환
// -> 람다를 인자로 받는 함수가 인라인 함수인 경우
fun lookForAlice(people: List<String>) {
    people.forEach {
        if (it == "Alice") {
            println("found")
            return
        }
    }
    println("not found")
}

// local return 변환 -> 레이블(@) 사용
// 람다의 실행을 끝내고 람다를 호출한 코드의 실행을 계속 이어감
// return으로 실행을 끝내고 싶은 람다 식 앞에 레이블을 붙이고, return 키워드 뒤에 레이블을 추가
fun lookForAliceV2(people: List<String>) {
    people.forEach label@{
        if (it == "Alice") {
            println("found")
            return@label
        }
    }
    // 함수 이름을 레이블로 사용 가능
//    people.forEach {
//        if (it == "Alice") {
//            println("found")
//            return@forEach
//        }
//    }
    println("not found") // 항상 출력됨
}

fun main() {
    val people = listOf("Tom", "Alice", "Mark")
    lookForAlice(people)
    println("---")
    lookForAliceV2(people)

    people.filter(fun (name): Boolean {
        return name == "Alice"
    })
}

// 무명 함수 - 코드 블록을 함수에 넘길 때 사용할 수 있는 다른 방법. 함수 이름과 파라미터 생략
// return은 가장 가까운 함수인 무명 함수를 가리킴. 기본적으로 local return
fun lookForAliceV3(people: List<String>) {
    people.forEach(fun (name) { // 람다 식 대신 무명 함수를 사용
        if (name == "Alice") return
        println("$name is not Alice")
    })
}

