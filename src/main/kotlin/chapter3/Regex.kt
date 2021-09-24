package chapter3

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = path.substringBeforeLast(".")
    val extension = path.substringAfterLast(".")

    print("directory = ${directory} fileName = ${fileName} extension = ${extension}")
}

fun parsePathRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex() // 삼중따옴표 - \를 따로 이스케이프 하지 않아도 됨, 줄바꿈 가능
    val matchResult = regex.matchEntire(path)
    val (directory, fileName, extension) = matchResult!!.destructured
    print("directory = ${directory} fileName = ${fileName} extension = ${extension}")
}
