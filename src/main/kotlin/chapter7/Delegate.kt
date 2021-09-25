package chapter7

import kotlin.reflect.KProperty

// 위임 객체: 특정 객체가 직접 작업을 수행하지 않고 그 작업을 대신 처리하는 도우미 객체
// 위임 프로퍼티: 프로퍼티의 접근자 기능을 위임 객체가 수행하게 함
// by 뒤에 있는 식으로 위임에 쓰일 객체를 얻음
// 프로퍼티 위임 관례를 따르는 위임 객체는 getValue와 setValue 메서드를 제공해야 함
class Delegate {
    operator fun getValue(foo: Foo, property: KProperty<*>): Int {
        return 1
    }

    operator fun setValue(foo: Foo, property: KProperty<*>, i: Int) {

    }
}

class Foo {
    var p: Int by Delegate()
}

// 실제 컴파일러 동작
//class Foo {
//    private val delegate = Delegate()
//    var p: Int
//    get() = delegate.getValue()
//    set(value: Int) = delegate.setValue()
//}

// 지연 초기화: 객체의 일부분을 초기화하지 않고 남겨뒀다가 그 부분의 값이 필요한 경우 초기화하는 패턴
// 프로퍼티 지연 초기화
class Person2(val name: String) {
    // 뒷받침하는 프로퍼티로 구현
//    private var _emails: List<String>? = null
//    val emails: List<String>
//        get() {
//            if (_emails == null) {
//                _emails = loadEmails(this)
//            }
//            return _emails!!
//        }
    // 위임 프로퍼티로 구현
    // lazy: 관례에 맞는 getValue 메서드가 들어있는 객체 반환
    // 인자는 값을 초기화할 때 호출할 람다
    val emails by lazy { loadEmails(this) }
}

fun loadEmails(person: Person2): List<String> {
    return listOf()
}

class Person3 {
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }
    val name: String by _attributes // 위임 프로퍼티로 맵을 사용
    // get() = _attributes["name"]!!
}
