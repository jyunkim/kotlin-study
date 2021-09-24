package chapter4

// 코틀린에서는 static이 없으므로 동반 객체를 이용하여 팩토리 메서드 구현
class Companion private constructor(val name: String) {
    // 동반 객체
    companion object {
        // 팩토리 메서드
        fun newName(nickName: String) {
            Companion(nickName)
        }
    }
}