package team.guin.example.repository

import org.springframework.stereotype.Repository
import team.guin.BaseRepository
import team.guin.example.Example

@Repository
interface ExampleRepository : BaseRepository<Example, Long>
