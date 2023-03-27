package team.guin.domain.team.enumeration

enum class TagType(name: String, type: String) {
    PYTHON("Python", "Common"), SPRING_FRAMEWORK("Spring Framework", "Common"), AWS("AWS", "Common"), IOS("iOS", "Common"),
    JAVASCRIPT("JavaScript", "Common"), HTML("Html", "Common"), JAVA("Java", "Common"), C_SHARP("C#", "Common"), C_PLUS("C++", "Common"),
    REACT("React.js", "Common"), NODE("Node.js", "Common"), VUE("Vue.js", "Common"), MYSQL("MySQL", "Common"),
    KOTLIN("Kotlin", "Common"), ANDROID("Android", "Common"), SQL("SQL", "Common"), ALGORITHM("알고리즘", SubjectType.STUDY.name),
}
