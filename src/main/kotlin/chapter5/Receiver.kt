package chapter5

// 수신 객체 지정 -> 객체의 이름을 반복하지 않아도 됨

fun alphabetV1(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nfinish")
    return result.toString()
}

// with - this를 사용해 수신 객체에 접근 가능
fun alphabetV2(): String {
    val result = StringBuilder()
    return with(result) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        this.append("\nfinish")
        this.toString()
    }
}

// this 생략 가능
fun alphabetV3() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nfinish")
    toString()
}

// apply - 수신 객체를 반환
fun alphabetV4() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nfinish")
}.toString()
