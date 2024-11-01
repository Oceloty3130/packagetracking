package packagetracking.pkg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import packagetracking.Courier.Courier;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Integer> {
    List<Package> findPackageByCourier(Courier courier);
    List<Package> findPackagesByStatus(Status status);

    @Query("SELECT c FROM Courier c WHERE c.id NOT IN (SELECT p.courier.id FROM Package p WHERE p.status = 1)")
    List<Courier> findAllCouriersWithoutPendingPackages();

}
