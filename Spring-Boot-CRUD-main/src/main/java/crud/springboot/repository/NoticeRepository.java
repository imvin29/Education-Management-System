

package crud.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crud.springboot.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
