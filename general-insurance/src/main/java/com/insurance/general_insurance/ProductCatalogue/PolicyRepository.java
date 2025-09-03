package com.insurance.general_insurance.ProductCatalogue;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;
@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long>{
	
	@Query("select p from Policy p where p.user.id=:userId")
	public List<Policy> getAllPolciesByUserId(Long userId);
	@Query("select p from Policy p where (:policyType is null or lower(p.policyType) like lower(concat('%', :policyType, '%'))) " +
		       "and (:maxCoverage is null or p.coverageAmount <= :maxCoverage)")
	public List<Policy> filterPolicies(@Param("policyType") String policyType, @Param("maxCoverage") Double maxCoverage);
	
}
