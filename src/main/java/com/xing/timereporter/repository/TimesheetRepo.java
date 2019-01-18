package com.xing.timereporter.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.xing.timereporter.model.Timesheet;

public interface TimesheetRepo  extends JpaRepository<Timesheet, Long>{

}
