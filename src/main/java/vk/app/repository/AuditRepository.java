package vk.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vk.app.user.AuditEntity;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Integer> {
}