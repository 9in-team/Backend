package team.guin.common

import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table

@Component
class DatabaseCleaner : InitializingBean {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    private var tableNames: MutableSet<String> = mutableSetOf()

    override fun afterPropertiesSet() {
        tableNames = entityManager.metamodel.entities
            .filter { it.javaType.isAnnotationPresent(Entity::class.java) }
            .map { it.javaType.getAnnotation(Table::class.java).name }
            .toMutableSet()
    }

    @Transactional
    fun cleanUp() {
        println("Cleaning up database...")
        entityManager.flush()

        // Assuming H2 database
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate()

        tableNames.forEach { tableName ->
            entityManager.createNativeQuery("TRUNCATE TABLE $tableName RESTART IDENTITY").executeUpdate()
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate()
    }
}
