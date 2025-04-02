package problem

fun isIPV4Valid(ip: String): Boolean {
    val segmentList = ip.split('.')
    if (segmentList.size != 4) return false

    for (segment in segmentList) {
        if (segment.startsWith("0") && segment != "0") return false
        val segmentAsInt = segment.toIntOrNull() ?: return false
        if (segmentAsInt !in 0..255) return false
    }
    return true
}