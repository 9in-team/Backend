package team.guin.domain.team.enumeration

import team.guin.domain.team.dto.AttributeDetail

enum class TagType(val value: String, val subjectType: List<SubjectType>) {
    PYTHON("Python", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    SPRING_FRAMEWORK("Spring Framework", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    AWS("AWS", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    IOS("iOS", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    JAVASCRIPT("JavaScript", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    HTML("HTML", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    JAVA("Java", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    C_SHARP("C#", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    C_PLUS_PLUS("C++", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    REACT("React.js", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    NODE("Node.js", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    VUE("Vue.js", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    MYSQL("MySQL", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    KOTLIN("Kotlin", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    ANDROID("Android", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    SQL("SQL", listOf(SubjectType.PROJECT, SubjectType.STUDY)),
    ALGORITHM("알고리즘", listOf(SubjectType.STUDY)),
    ;

    companion object {
        fun studyTag(): List<AttributeDetail> {
            return values()
                .filter { it.subjectType.contains(SubjectType.STUDY) }
                .map { AttributeDetail(it.name, it.value) }
        }

        fun projectTag(): List<AttributeDetail> {
            return values()
                .filter { it.subjectType.contains(SubjectType.PROJECT) }
                .map { AttributeDetail(it.name, it.value) }
        }
    }
}
