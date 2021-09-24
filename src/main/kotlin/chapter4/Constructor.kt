package chapter4

// 주 생성자 축약 -> 본문 바깥에서 정의
class User1(
    val name: String, // get만 가능
    var age: Int // get, set 가능
)

// 위와 동일
class User2(name: String) {
    val name: String
    init {
        this.name = name
    }
    // 또는 커스텀 getter 사용해서 인스턴스 생성 시 초기화하지 않고 프로퍼티를 가져올 때 초기화
//        get() {
//
//        }
}

// 가시성 변경자를 붙이려면 constructor를 써줘야 함
class User3 private constructor(val name: String)

// 디폴트 파라미터
class User4(val id: Long, val name: String = "noName")

// 부 생성자 -> 본문 안에서 정의
class User5 {
    val name: String
    constructor(name: String) {
        this.name = name
    }
}

// toString, equals, hashCode, copy 메서드 구현
data class User6(val name: String)
