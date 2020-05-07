package eu.uftpapi.repository;

import eu.uftpapi.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageStoreRepository extends JpaRepository<Message, Long> {
}
