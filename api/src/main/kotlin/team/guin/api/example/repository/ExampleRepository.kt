package team.guin.api.example.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.guin.domain.example.ExampleEntity

@Repository
interface ExampleRepository : JpaRepository<ExampleEntity, Long>
