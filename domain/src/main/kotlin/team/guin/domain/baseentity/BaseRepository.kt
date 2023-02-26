package team.guin.domain.baseentity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.query.Param
import java.io.Serializable

@NoRepositoryBean
interface BaseRepository<T : BaseEntity, ID : Serializable> : JpaRepository<T, ID> {
    @Query("UPDATE #{#entityName} e SET e.deletedAt = CURRENT_TIMESTAMP WHERE e.id = :id")
    @Modifying
    override fun deleteById(@Param("id") id: ID)

    @Query("UPDATE #{#entityName} e SET e.deletedAt = CURRENT_TIMESTAMP WHERE e IN :entities")
    @Modifying
    override fun deleteAll(@Param("entities") entities: MutableIterable<T>)

    override fun deleteAllById(ids: MutableIterable<ID>)
}
