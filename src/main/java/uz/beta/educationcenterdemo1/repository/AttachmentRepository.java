package uz.beta.educationcenterdemo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.beta.educationcenterdemo1.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
