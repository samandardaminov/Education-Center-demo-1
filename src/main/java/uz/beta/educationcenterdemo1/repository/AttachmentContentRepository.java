package uz.beta.educationcenterdemo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.beta.educationcenterdemo1.entity.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {

    AttachmentContent findByAttachmentId(UUID attachment_id);
}
