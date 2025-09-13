package movieticketbookingservice.paymentservice.repositories;

import movieticketbookingservice.paymentservice.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}
