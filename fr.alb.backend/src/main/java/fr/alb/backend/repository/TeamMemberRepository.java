package fr.alb.backend.repository;

import fr.alb.backend.model.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    List<TeamMember> findAllByOrderByDisplayOrderAsc();
}
