package chapter4

// 코틀린의 기본 가시성 변경자는 public

class Outer {
    // 중첩(static) 클래스 - 바깥 클래스에 대한 참조를 저장하지 않음
    class Nested {

    }

    // 내부 클래스 - 바깥 클래스에 대한 참조를 저장
    inner class Inner {

    }
}