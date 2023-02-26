package team.guin.example

import org.springframework.stereotype.Repository
import team.guin.domain.baseentity.BaseRepository
import team.guin.domain.example.Example

@Repository
interface ExampleRepository : BaseRepository<Example, Long>
