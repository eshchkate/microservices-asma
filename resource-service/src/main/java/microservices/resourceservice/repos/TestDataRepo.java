package microservices.resourceservice.repos;

import microservices.resourceservice.model.TestData;
import org.springframework.data.repository.CrudRepository;

public interface TestDataRepo extends CrudRepository <TestData, Long> {
}
