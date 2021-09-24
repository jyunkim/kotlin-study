package chapter2

import java.io.BufferedReader

// throws를 써주지 않아도 됨
fun readNumberV1(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}
