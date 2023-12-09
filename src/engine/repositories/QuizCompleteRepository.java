package engine.repositories;

import engine.dto.QuizComplete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompleteRepository extends PagingAndSortingRepository<QuizComplete, Long> {
    Page<QuizComplete> findByUsername(String userName, Pageable of);
}
