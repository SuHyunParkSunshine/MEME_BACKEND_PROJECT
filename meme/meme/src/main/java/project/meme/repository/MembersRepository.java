package project.meme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.meme.domain.Members;

public interface MembersRepository extends JpaRepository<Members, Integer> {
    Members findByEmail(String email);
}
