package packagetracking.pkg;

import org.springframework.data.jpa.repository.JpaRepository;
import packagetracking.Courier.Courier;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Integer> {

    List<Package> findPackageByCourier(Courier courier);
}
