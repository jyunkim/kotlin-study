package chapter4

// 인터페이스는 기본이 open
interface Clickable {
    // 인터페이스도 멤버 변수 보유 가능
    val name: String
    // 인터페이스의 경우 기본이 abstract -> 반드시 override 해야함
    fun click()
    // 인터페이스 메서드도 디폴트 구현 가능
    fun showOff() = println("Clickable")
}

// 기본이 final이므로 상속 가능하게 하려면 open 명시
// 생성자를 따로 안쓰면 디폴트 생성자 자동 생성
open class Focusable {
    // override 불가
    fun focus() {}
    // override 가능
    open fun disable() {}
}

abstract class Animated {
    abstract fun animate()
}

// 인터페이스는 디폴트 생성자가 없으므로 () 사용 x
class Button(override val name: String) : Clickable, Focusable() {
    override fun click() {}
}

// 인터페이스는 여러 개 상속 가능, 클래스는 오직 하나만 상속 가능