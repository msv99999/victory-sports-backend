package org.victoryfoundation.sportsapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.victoryfoundation.sportsapp.entity.Attendance;
import org.victoryfoundation.sportsapp.entity.AttendanceKey;

public interface AttendanceRepostiory extends JpaRepository<Attendance, AttendanceKey> {
}
